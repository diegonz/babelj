package com.pragmabits.babelj.utils;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.text.ParseException;

/**
 * CliArgs class parses/handles cli arguments.
 *
 * @author Diego González
 */
public class CliArgs {

    private static CliArgs instance;

    @Parameter(names = {"--help", "-h"}, description = "Show help and usage options", help = true)
    public boolean help;
    /**
     * The Settings file.
     */
    @Parameter(names = {"--config-file", "-c"}, description = "Custom path to config file. [/path/to/config.json]")
    public String configPath;
    /**
     * The Api key.
     */
    @Parameter(names = {"--api-key", "-a"}, description = "Translate backend API key. [yourApiKey]")
    String apiKey;
    /**
     * The Api id.
     */
    @Parameter(names = {"--api-id", "-id"}, description = "Translate backend ID. [yourId@server.com]")
    String apiId;
    /**
     * The Backend.
     */
    @Parameter(names = {"--backend", "-b"}, description = "Target translate backend. [yandex|microsoft|google]")
    String backend;
    /**
     * The Input.
     */
    @Parameter(names = {"--input", "-i"}, description = "Desired input method. [clipboard|selection]")
    String input;
    /**
     * The Message.
     */
    @Parameter(names = {"--message", "-m"}, description = "The text to translate itself given as a String argument.")
    public String message;
    /**
     * The Output.
     */
    @Parameter(names = {"--output", "-o"}, description = "Desired output method. [notify|stdout|none]")
    String output;
    /**
     * The Source lang.
     */
    @Parameter(names = {"--source-lang", "-s"}, description = "Source language to translate from. [en|ru|fr|es|...]")
    public String sourceLang;
    /**
     * The Target lang.
     */
    @Parameter(names = {"--target-lang", "-t"}, description = "Target language to translate. [en|ru|fr|es|...]")
    String targetLang;
    /**
     * The Exchange.
     */
    @Parameter(names = {"--exchange", "-x"}, description = "Exchange clipboard content with translation result.")
    public boolean exchange = false;
    /**
     * The Save config.
     */
    @Parameter(names = {"--save-config"},
            description = "Save (overwriting) current settings to config file (into default or given path) and exit.")
    public boolean saveConfig = false;

    private CliArgs() {}

    private void checkBackend() throws CliArgsError {
        if (backend != null && !backend.matches("yandex|microsoft|google"))
            throw new CliArgsError(new ParseException(backend, 0).getMessage());
    }

    private void checkInput() throws CliArgsError {
        if (input != null && !input.matches("clipboard|selection"))
            throw new CliArgsError(new ParseException(input, 0).getMessage());
    }

    private void checkOutput() throws CliArgsError {
        if (output != null && !output.matches("none|stdout|notify"))
            throw new CliArgsError(new ParseException(output, 0).getMessage());
    }

    public void parseCliArgs() throws CliArgsError {
        checkBackend();
        checkInput();
        checkOutput();
    }

    public static synchronized CliArgs getInstance() {
        if (instance == null)
            instance = new CliArgs();
        return instance;
    }

    /**
     * ArgParser from arg (CLI).
     *
     * @param args the CLI args
     * @return the arg parser with parsed arguments
     * @throws CliArgsError the CliArgs exception
     */
    public static CliArgs fromArgs(final String[] args) throws CliArgsError {
        CliArgs cliArgs = getInstance();
        new JCommander(cliArgs, args);
        cliArgs.parseCliArgs();
        return cliArgs;
    }
}
