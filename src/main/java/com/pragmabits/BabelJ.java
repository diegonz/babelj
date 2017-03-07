package com.pragmabits;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.pragmabits.notify.NotifyError;
import com.pragmabits.notify.NotifyHelper;
import com.pragmabits.translate.TranslateError;
import com.pragmabits.translate.Translation;
import com.pragmabits.translate.Translator;
import com.pragmabits.translate.yandex.YandexTranslator;
import com.pragmabits.utils.*;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BabelJ main class. - TODO - More UnitTesting
 */
public class BabelJ {

    private CliArgs cliArgs;
    private Settings settings;
    private ClipboardHandler clipboard;
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
        this.clipboard = new TextTransfer();
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
        String sourceLang = cliArgs.sourceLang != null ? cliArgs.sourceLang : "auto";
        String targetText = cliArgs.message != null ? cliArgs.message : clipboard.getByInputType(settings.input);
        if (targetText.isEmpty())
            targetText = "No text was found on " + settings.input;
        Translation response;
        try {
            response = translator.translate(new Translation(sourceLang, settings.language, targetText));
        } catch (TranslateError te) {
            Logger.getLogger(BabelJ.class.getName()).log(Level.SEVERE, "[❌] Translate error.", te);
            response = new Translation("ERROR", te.getErrorDescription());
        }

        if ("notify".equals(settings.output)) {
            String translationLang = String.format("%s-%s",
                    response.getSourceLang().toUpperCase(),
                    response.getTargetLang().toUpperCase());
            NotifyHelper.notify("Translated " + translationLang, response.getText());
        } else if ("stdout".equals(settings.output)) {
            System.out.println(response.getText());
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
        CliArgs cliArgs = CliArgs.getInstance();
        try {
            JCommander jCommander = new JCommander(cliArgs, args);
            if (cliArgs.help) {
                jCommander.usage();
                System.exit(0);
            }
        } catch (ParameterException e) {
            Logger.getLogger(BabelJ.class.getName()).log(Level.SEVERE, "[❌] CLI arguments error: ", e);
            System.exit(1);
        }

        String configPath = cliArgs.configPath != null
                ? cliArgs.configPath.replaceFirst("^~", System.getProperty("user.home"))
                : System.getProperty("user.home") + File.separator + ".BabelJ.json";

        Settings settings = null;
        try (FileReader fileReader = new FileReader(configPath)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            settings = Settings.fromJsonFile(bufferedReader);
            bufferedReader.close();
            settings.addCliArgs(cliArgs);
        } catch (FileNotFoundException e) {
            Logger.getLogger(BabelJ.class.getName())
                    .log(Level.WARNING, "[⚠] Settings file not found, using \"default + CLI\" settings.");
            settings = Settings.fromCliArgs(cliArgs);
        } catch (IOException e) {
            Logger.getLogger(BabelJ.class.getName()).log(Level.SEVERE, "[❌] IO read error.", e);
            System.exit(1);
        } catch (SettingsError error) {
            Logger logger = Logger.getLogger(BabelJ.class.getName());
            logger.log(Level.SEVERE, "[❌] Error found while parsing config file.", error);
            logger.log(Level.SEVERE, "[❌] Description: \n%s", error.getErrorDescription());
            if (error.getErrorCauseItem() != null)
                logger.log(Level.SEVERE, "[❌] Error causing item: \n%s", error.getErrorCauseItem());
            System.exit(1);
        }

        if (cliArgs.saveConfig) {
            Logger logger = Logger.getLogger(BabelJ.class.getName());
            try (FileWriter fileWriter = new FileWriter(configPath)) {
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                settings.toJsonFile(bufferedWriter);
                bufferedWriter.close();
                logger.log(Level.INFO, "[✓] Config file created at: %s", configPath);
                logger.log(Level.INFO, "[✓] Fill it with your settings and API Key(s).");
                System.exit(0);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "[❌] IO write error.", e);
                System.exit(1);
            }
        }

        try {
            new BabelJ(settings, cliArgs).run();
        } catch (TextTransferError transferError) {
            Logger.getLogger(BabelJ.class.getName()).log(Level.SEVERE, "[❌] Input error.", transferError);
        } catch (NotifyError notifyError) {
            Logger.getLogger(BabelJ.class.getName()).log(Level.SEVERE, "[❌] Notify error.", notifyError);
        } catch (BabelJException babelJException) {
            Logger.getLogger(BabelJ.class.getName()).log(Level.SEVERE, "[❌] App error.", babelJException);
        }
    }
}
