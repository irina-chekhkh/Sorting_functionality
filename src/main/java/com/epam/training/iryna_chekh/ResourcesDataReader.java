package com.epam.training.iryna_chekh;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResourcesDataReader {
    private static final Logger LOGGER = Logger.getLogger(ResourcesDataReader.class.getName());
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("config");

    public static String getData(String key) {
        try {
            LOGGER.log(Level.CONFIG, "Read key {0} from config.properties", key);
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.log(Level.SEVERE, "Key {0} not found in config.properties", key);
            throw e;
        }
    }
}
