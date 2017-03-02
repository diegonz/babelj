package com.pragmabits.babelj;

import com.pragmabits.babelj.notify.NotifyException;
import com.pragmabits.babelj.notify.NotifyHelper;
import com.pragmabits.babelj.translate.TranslateError;
import com.pragmabits.babelj.translate.Translation;
import com.pragmabits.babelj.translate.Translator;
import com.pragmabits.babelj.translate.yandex.YandexTranslator;
import com.pragmabits.babelj.utils.*;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BabelJ main class. - TODO - More UnitTesting
 */
public class BabelJ {

    private static final Logger LOG = Logger.getLogger(BabelJ.class.getName());
    private CliArgs cliArgs;
    private Settings settings;
    private Clipboard clipboard;
    private Translator translator;

    /**
     * BabelJ private constructor.
     *
     * @param settings the app settings
     * @param cliArgs  the cli input arguments
     */
    private BabelJ(Settings settings, CliArgs cliArgs) {
        this.settings = settings;
        this.cliArgs = cliArgs;
        this.clipboard = Clipboard.getInstance();
        this.translator = this.getTranslator();
    }

    private Translator getTranslator() {
        switch (settings.backend) {
            case "microsoft":
            case "google":
            case "yandex":
            default:
                return new YandexTranslator(settings.yandexKey);
        }
    }

    private void run() throws BabelJException {
        String targetText = cliArgs.message != null ? cliArgs.message : clipboard.getByInputType(settings.input);
        Translation response;
        try {
            response = translator.translate(new Translation(cliArgs.sourceLang, settings.language, targetText));
        } catch (TranslateError te) {
            LOG.log(Level.SEVERE, "[❌] Translate error.", te);
            response = new Translation("ERROR", te.getErrorDescription());
        }

        switch (settings.output) {
            case "none":
                break;
            case "stdout":
                System.out.println(response.getText());
                break;
            case "notify":
            default:
                String translationLang = String.format("%s-%s",
                        response.getSourceLang().toUpperCase(),
                        response.getTargetLang().toUpperCase());
                NotifyHelper.notify("Translated " + translationLang, response.getText());
        }

        if (cliArgs.exchange || settings.exchange)
            clipboard.setClipboard(response.getText());
    }

    /**
     * The (main) entry point of the application.
     *
     * @param args the cli input arguments
     */
    public static void main(final String[] args) {
        CliArgs cliArgs = null;
        try {
            cliArgs = CliArgs.fromArgs(args);
        } catch (CliArgsError e) {
            LOG.log(Level.SEVERE, "[❌] CLI arguments error.", e);
            LOG.log(Level.SEVERE, "[❌] ErrorCode found while parsing CLI arguments.");
            System.exit(1);
        }

        String configPath;
        if (cliArgs.configPath != null) {
            configPath = cliArgs.configPath.replaceFirst("^~", System.getProperty("user.home"));
        } else {
            configPath = System.getProperty("user.home") + File.separator + ".BabelJ.json";
        }

        Settings settings = null;
        try (FileReader fileReader = new FileReader(configPath)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            settings = Settings.fromJsonFile(bufferedReader);
            bufferedReader.close();
            settings.addCliArgs(cliArgs);
        } catch (FileNotFoundException e) {
            LOG.log(Level.WARNING, "[⚠] Settings file not found.", e);
            LOG.log(Level.WARNING, "[⚠] Launching with default+cli settings.");
            settings = Settings.fromCliArgs(cliArgs);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "[❌] IO ErrorCode.", e);
            LOG.log(Level.SEVERE, "[❌] ErrorCode found while trying to read from config file.");
            System.exit(1);
        } catch (SettingsError settingsError) {
            LOG.log(Level.SEVERE, "[❌] Settings error: ", settingsError);
            LOG.log(Level.SEVERE, "[❌] Settings error: \n%s", settingsError.getErrorDescription());
            if (settingsError.getErrorCausingItem() != null)
                LOG.log(Level.SEVERE, "[❌] ErrorCode causing item: \n%s", settingsError.getErrorCausingItem());
            LOG.log(Level.SEVERE, "[❌] ErrorCode found while parsing config file.");
            System.exit(1);
        }

        if (cliArgs.saveConfig) {
            int writeOk = 1; // ErrorCode by default
            try (FileWriter fileWriter = new FileWriter(configPath)) {
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                settings.toJsonFile(bufferedWriter);
                bufferedWriter.close();
                LOG.log(Level.INFO, "[✓] Blank config file created with path: %s", configPath);
                LOG.log(Level.INFO, "[✓] Fill it with your preferred settings and API Key(s).");
                writeOk = 0; // Exit status 0 (OK)
            } catch (IOException e) {
                LOG.log(Level.SEVERE, "[❌] IO ErrorCode.", e);
                LOG.log(Level.SEVERE, "[❌] ErrorCode found while trying to write to config file.");
            }
            System.exit(writeOk);
        }

        try {
            new BabelJ(settings, cliArgs).run();
        } catch (ClipboardError clipboardError) {
            LOG.log(Level.SEVERE, "[❌] Input error.", clipboardError);
            LOG.log(Level.SEVERE, "[❌] ErrorCode found while trying to get user input.");
        } catch (NotifyException e) {
            LOG.log(Level.SEVERE, "[❌] Notify error.", e);
            LOG.log(Level.SEVERE, "[❌] ErrorCode found while trying notify user.");
        } catch (BabelJException e) {
            LOG.log(Level.SEVERE, "[❌] Notify error.", e);
            System.exit(1);
        }
    }
}
