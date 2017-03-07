package com.pragmabits.utils;

import com.beust.jcommander.JCommander;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The type Settings test.
 */
public class SettingsTest {

    private String testConfigPath = System.getProperty("user.home") + File.separator + "BabelJTest.json";
    private Settings settings;
    private CliArgs args;
    private String[] fakeCliArgs = {
            "-c", testConfigPath,
            "-b", "yandex",
            "-a", "trnsl.1.1.20170203T202734Z.5093fb80fddda46a.b66dd1bf1599f71e29ca9f3afe38d9a5ac9fc216",
            "-i", "selection",
            "-o", "stdout",
            "-m", "Hello world",
            "-s", "en",
            "-t", "es"
    };

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        args = CliArgs.getInstance();
        new JCommander(args, fakeCliArgs);
    }

    /**
     * Overwrite with cli args.
     *
     * @throws Exception the exception
     */
    @Test
    public void overwriteWithCliArgs() throws Exception {
        settings = Settings.fromCliArgs(args);
        assertEquals("Target language", "es", settings.language);
        assertEquals("Input", "selection", settings.input);
        assertEquals("Output", "stdout", settings.output);
    }

    /**
     * To json file.
     *
     * @throws Exception the exception
     */
    @Test
    public void toJsonFile() throws Exception {
        settings = Settings.fromCliArgs(args);
        FileWriter fileWriter = new FileWriter(testConfigPath);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        settings.toJsonFile(bufferedWriter);
        bufferedWriter.close();
        fileWriter.close();
        File f = new File(testConfigPath);
        assertTrue("Write config file", f.exists() && !f.isDirectory());
        Files.deleteIfExists(Paths.get(testConfigPath));
    }

    /**
     * From json file.
     *
     * @throws Exception the exception
     */
    @Test
    public void fromJsonFile() throws Exception {
        settings = Settings.fromCliArgs(args);
        FileWriter fileWriter = new FileWriter(testConfigPath);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        settings.toJsonFile(bufferedWriter);
        bufferedWriter.close();
        fileWriter.close();
        FileReader fileReader = new FileReader(testConfigPath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Settings loadedSettings = Settings.fromJsonFile(bufferedReader);
        loadedSettings.addCliArgs(args);
        assertEquals("Load settings file", settings.toString(), loadedSettings.toString());
        Files.delete(Paths.get(testConfigPath));
    }

    /**
     * From cli args.
     *
     * @throws Exception the exception
     */
    @Test
    public void fromCliArgs() throws Exception {
        settings = Settings.fromCliArgs(args);
        assertEquals("Target language", "es", settings.language);
        assertEquals("Input", "selection", settings.input);
        assertEquals("Output", "stdout", settings.output);
    }

}