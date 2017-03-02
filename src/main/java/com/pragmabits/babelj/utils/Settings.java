package com.pragmabits.babelj.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.Reader;
import java.util.HashMap;

/**
 * The Settings class.
 */
public class Settings {

    /**
     * The Backend.
     */
    @SerializedName("backend")
    @Expose
    public String backend;
    /**
     * The Yandex key.
     */
    @SerializedName("yandex_key")
    @Expose
    public String yandexKey;
    /**
     * The Microsoft key.
     */
    @SerializedName("microsoft_key")
    @Expose
    public String microsoftKey;
    /**
     * The Microsoft id.
     */
    @SerializedName("microsoft_id")
    @Expose
    public String microsoftId;
    /**
     * The Google key.
     */
    @SerializedName("google_key")
    @Expose
    public String googleKey;
    /**
     * The Google id.
     */
    @SerializedName("google_id")
    @Expose
    public String googleId;
    /**
     * The Language.
     */
    @SerializedName("language")
    @Expose
    public String language;
    /**
     * The Input.
     */
    @SerializedName("input")
    @Expose
    public String input;
    /**
     * The Output.
     */
    @SerializedName("output")
    @Expose
    public String output;
    /**
     * The Exchange.
     */
    @SerializedName("exchange")
    @Expose
    public Boolean exchange;

    private Settings() {
        this.backend = "yandex";
        this.yandexKey = "YandexApiKey";
        this.microsoftKey = "MicrosoftApiKey";
        this.microsoftId = "MicrosoftId@outlook.com";
        this.googleKey = "GoogleApiKey";
        this.googleId = "GoogleId@gmail.com";
        this.language = "en";
        this.input = "selection";
        this.output = "notify";
        this.exchange = false;
    }

    private Settings(CliArgs cliArgs) {
        this();
        this.addCliArgs(cliArgs);
    }

    private void checkBackend() throws SettingsError {
        if (!this.backend.matches("yandex|microsoft|google")) {
            throw new SettingsError(SettingsError.Error.BACKEND);
        }
    }

    private void checkInput() throws SettingsError {
        if (!this.input.matches("clipboard|selection")) {
            throw new SettingsError(SettingsError.Error.INPUT);
        }
    }

    private void checkOutput() throws SettingsError {
        if (!this.output.matches("none|stdout|notify")) {
            throw new SettingsError(SettingsError.Error.OUTPUT);
        }
    }

    private void checkLanguage() throws SettingsError {
        if (this.language.length() != 2) {
            throw new SettingsError(SettingsError.Error.LANGUAGE);
        }
    }

    private void checkIds() throws SettingsError {
        EmailValidator emailValidator = EmailValidator.getInstance(false);
        if (!emailValidator.isValid(this.microsoftId) || !emailValidator.isValid(this.googleId)) {
            throw new SettingsError(SettingsError.Error.BACKEND_ID);
        }
    }

    private void parseSettings() throws SettingsError {
        this.checkBackend();
        this.checkInput();
        this.checkOutput();
        this.checkLanguage();
        this.checkIds();
    }

    /**
     * Overwrite with cli args.
     *
     * @param cliArgs the cli args
     */
    public void addCliArgs(CliArgs cliArgs) {
        if (cliArgs.backend != null)
            this.backend = cliArgs.backend;
        switch (this.backend) {
            case "google":
                if (cliArgs.apiKey != null)
                    this.googleKey = cliArgs.apiKey;
                if (cliArgs.apiId != null)
                    this.googleId = cliArgs.apiId;
                break;
            case "microsoft":
                if (cliArgs.apiKey != null)
                    this.microsoftKey = cliArgs.apiKey;
                if (cliArgs.apiKey != null)
                    this.microsoftId = cliArgs.apiKey;
                break;
            case "yandex":
            default:
                if (cliArgs.apiKey != null)
                    this.yandexKey = cliArgs.apiKey;
                break;
        }
        if (cliArgs.targetLang != null)
            this.language = cliArgs.targetLang;
        if (cliArgs.input != null)
            this.input = cliArgs.input;
        if (cliArgs.output != null)
            this.output = cliArgs.output;
        if (cliArgs.exchange)
            this.exchange = true;
    }

    /**
     * Write config to json file.
     *
     * @param configWriter the config file path
     */
    public void toJsonFile(Appendable configWriter) {
        new GsonBuilder().create().toJson(this, configWriter);
    }

    /**
     * Load Settings from json file config.
     *
     * @param configReader the config file reader
     * @return the config object
     * @throws SettingsError the config error
     */
    public static Settings fromJsonFile(Reader configReader) throws SettingsError {
        Settings settings = new Gson().fromJson(configReader, Settings.class);
        settings.parseSettings();
        return settings;
    }

    /**
     * Load Settings from cli args config.
     *
     * @param cliArgs the cli args
     * @return the config
     */
    public static Settings fromCliArgs(CliArgs cliArgs) {
        return new Settings(cliArgs);
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format(
                "%n%s:%nbackend: %s%nyandexApiKey: %s%n" +
                        "microsoftApiKey: %s%nmicrosoftId: %s%n" +
                        "googleApiKey: %s%ngoogleId: %s%n" +
                        "language: %s%ninput: %s%noutput: %s%n" +
                        "exchange: %s",
                getClass().getName(), backend, yandexKey, microsoftKey, microsoftId,
                googleKey, googleId, language, input, output, exchange).trim();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     * {@code x}, {@code x.equals(x)} should return
     * {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     * {@code x} and {@code y}, {@code x.equals(y)}
     * should return {@code true} if and only if
     * {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     * {@code x}, {@code y}, and {@code z}, if
     * {@code x.equals(y)} returns {@code true} and
     * {@code y.equals(z)} returns {@code true}, then
     * {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     * {@code x} and {@code y}, multiple invocations of
     * {@code x.equals(y)} consistently return {@code true}
     * or consistently return {@code false}, provided no
     * information used in {@code equals} comparisons on the
     * objects is modified.
     * <li>For any non-null reference value {@code x},
     * {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object obj) {
        return (obj != null && obj.getClass() == getClass()) && this.toString().equals(obj.toString());
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link HashMap}.
     * <p>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     * an execution of a Java application, the {@code hashCode} method
     * must consistently return the same integer, provided no information
     * used in {@code equals} comparisons on the object is modified.
     * This integer need not remain consistent from one execution of an
     * application to another execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     * method, then calling the {@code hashCode} method on each of
     * the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     * according to the {@link Object#equals(Object)}
     * method, then calling the {@code hashCode} method on each of the
     * two objects must produce distinct integer results.  However, the
     * programmer should be aware that producing distinct integer results
     * for unequal objects may improve the performance of hash tables.
     * </ul>
     * <p>
     * As much as is reasonably practical, the hashCode method defined by
     * class {@code Object} does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the
     * Java&trade; programming language.)
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public int hashCode() {
        return Integer.valueOf(this.toString());
    }
}
