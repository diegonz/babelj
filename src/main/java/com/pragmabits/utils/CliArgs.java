package com.pragmabits.utils;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * CliArgs class parses/handles cli arguments.
 *
 * @author Diego González
 */
public class CliArgs {

    private static CliArgs instance;

    @Parameter(names = {"--help", "-h"},
            description = "Show help and usage options an exit.", help = true)
    public boolean help;
    /**
     * The Settings file.
     */
    @Parameter(names = {"--config-file", "-c"},
            description = "Custom path to config file. [~/.babelj.json].")
    public String configPath;
    /**
     * The Api key.
     */
    @Parameter(names = {"--api-key", "-a"},
            description = "Tour translate backend API key.")
    String apiKey;
    /**
     * The Api id.
     */
    @Parameter(names = {"--api-id", "-id"}, validateWith = ValidApiId.class,
            description = "Your translate backend ID [yourId@server.com].")
    String apiId;
    /**
     * The Backend.
     */
    @Parameter(names = {"--backend", "-b"}, validateWith = ValidBackend.class,
            description = "Target translate backend [yandex|microsoft|google].")
    String backend;
    /**
     * The Input.
     */
    @Parameter(names = {"--input", "-i"}, validateWith = ValidInput.class,
            description = "Desired input method. [clipboard|selection].")
    String input;
    /**
     * The Message.
     */
    @Parameter(names = {"--message", "-m"},
            description = "The text to translate itself given as a String argument.")
    public String message;
    /**
     * The Output.
     */
    @Parameter(names = {"--output", "-o"}, validateWith = ValidOutput.class,
            description = "Desired output method. [notify|stdout|none].")
    String output;
    /**
     * The Source lang.
     */
    @Parameter(names = {"--source-lang", "-s"}, validateWith = ValidLanguageCode.class,
            description = "Source language to translate from [en|ru|fr|es|...].")
    public String sourceLang;
    /**
     * The Target lang.
     */
    @Parameter(names = {"--target-lang", "-t"}, validateWith = ValidLanguageCode.class,
            description = "Target language to translate [en|ru|fr|es|...].")
    String targetLang;
    /**
     * The Exchange.
     */
    @Parameter(names = {"--exchange", "-x"},
            description = "Exchange clipboard content with translation result.")
    public boolean exchange = false;
    /**
     * The Save config.
     */
    @Parameter(names = {"--save-config"},
            description = "Save current settings to config file and exit. " +
                    "Overwrites target file at default (or given) path.")
    public boolean saveConfig = false;

    private CliArgs() {
    }

    public static synchronized CliArgs getInstance() {
        if (instance == null)
            instance = new CliArgs();
        return instance;
    }

    public static class ValidBackend implements IParameterValidator {
        @Override
        public void validate(String name, String value) {
            if (value != null && !value.matches("yandex|microsoft|google"))
                throw new ParameterException("[❌] Backend should be [yandex|microsoft|google] (found: " + value + ")");
        }
    }

    public static class ValidInput implements IParameterValidator {
        @Override
        public void validate(String name, String value) {
            if (value != null && !value.matches("clipboard|selection"))
                throw new ParameterException("[❌] Input should be [clipboard|selection] (found: " + value + ")");
        }
    }

    public static class ValidOutput implements IParameterValidator {
        @Override
        public void validate(String name, String value) {
            if (value != null && !value.matches("none|stdout|notify"))
                throw new ParameterException("[❌] Output should be [none|stdout|notify] (found: " + value + ")");
        }
    }

    public static class ValidApiId implements IParameterValidator {
        @Override
        public void validate(String name, String value) {
            if (!EmailValidator.getInstance(false).isValid(value))
                throw new ParameterException("[❌] API ID should use [id@domain.tld] format\n (found: " + value + ")");
        }
    }

    public static class ValidLanguageCode implements IParameterValidator {
        @Override
        public void validate(String name, String value) {
            if (value != null && value.length() != 2)
                throw new ParameterException("[❌] Language should use [en|ru|xx] format (found: " + value + ")");
        }
    }

}

