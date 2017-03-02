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

    /**
     * The Settings file.
     */
    @Parameter(names = {"--config-file", "-c"})
    public String configPath;
    /**
     * The Api key.
     */
    @Parameter(names = {"--api-key", "-a"})
    String apiKey;
    /**
     * The Api id.
     */
    @Parameter(names = {"--api-id", "-id"})
    String apiId;
    /**
     * The Backend.
     */
    @Parameter(names = {"--backend", "-b"})
    String backend;
    /**
     * The Input.
     */
    @Parameter(names = {"--input", "-i"})
    String input;
    /**
     * The Message.
     */
    @Parameter(names = {"--message", "-m"})
    public String message;
    /**
     * The Output.
     */
    @Parameter(names = {"--output", "-o"})
    String output;
    /**
     * The Source lang.
     */
    @Parameter(names = {"--source-lang", "-s"})
    public String sourceLang;
    /**
     * The Target lang.
     */
    @Parameter(names = {"--target-lang", "-t"})
    String targetLang;
    /**
     * The Exchange.
     */
    @Parameter(names = {"--exchange", "-x"})
    public boolean exchange = false;
    /**
     * The Save config.
     */
    @Parameter(names = {"--save-config"})
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

    private void parseCliArgs() throws CliArgsError {
        checkBackend();
        checkInput();
        checkOutput();
    }

    private static synchronized CliArgs getInstance() {
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
