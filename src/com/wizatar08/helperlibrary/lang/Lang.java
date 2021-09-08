package com.wizatar08.helperlibrary.lang;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Lang {
    public static Locale CURRENT_LANG;
    private static String bLoc;

    /**
     * Initialize the language using a hardcoded language and country
     * @param lang - Language
     * @param country - Country
     * @param bundleLocation - Where the bundle files for all languages are stored
     */
    public static void init(String lang, String country, String bundleLocation) {
        init(new Locale(lang, country), bundleLocation);
    }

    /**
     * Initialize the language using a hardcoded locale
     * @param locale - Language locale
     * @param bundleLocation - Where the bundle files for all languages are stored
     */
    public static void init(Locale locale, String bundleLocation) {
        bLoc = bundleLocation;
        CURRENT_LANG = locale;
    }

    /**
     * Initialize the language using a settings .properties file
     * @param settingsFileInput - File
     * @param bundleLocation - Where the bundle files for all languages are stored
     */
    public static void init(FileInputStream settingsFileInput, String bundleLocation) {
        bLoc = bundleLocation;
        Properties prop = new Properties();
        try {
            InputStream file = settingsFileInput;
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String lang = prop.getProperty("lang");
        CURRENT_LANG = new Locale(lang.substring(0, 2), lang.substring(3, 5));
    }

    /**
     * Get a string of text from a lang file based on the translation key
     * @param translationKey
     * @return
     */
    public static String get(String translationKey) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bLoc, CURRENT_LANG);
        try {
            return (resourceBundle.getString(translationKey));
        } catch (MissingResourceException e) {
            return translationKey;
        }
    }

    /**
     * Change the current language using a hardcoded language and country
     * @param lang
     * @param country
     */
    public static void changeLang(String lang, String country) {
        changeLang(new Locale(lang, country));
    }

    /**
     * Change the current language using a locale
     * @param lang
     */
    public static void changeLang(Locale lang) {
        CURRENT_LANG = lang;
    }
}