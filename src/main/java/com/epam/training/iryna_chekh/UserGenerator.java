package com.epam.training.iryna_chekh;

import com.epam.training.iryna_chekh.user.User;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UserGenerator {
    private static final Logger LOGGER = Logger.getLogger(UserGenerator.class.getName());
    public static final String USER_NAME = "user.name";
    public static final String USER_PASSWORD = "user.password";

    public static User createUser() {
        LOGGER.log(Level.CONFIG, "Start creating user from config.properties");

        LOGGER.log(Level.CONFIG, "Reading username from config");
        String userName = ResourcesDataReader.getData(USER_NAME);

        LOGGER.log(Level.CONFIG, "Reading password from config (hidden for security)");
        String password = ResourcesDataReader.getData(USER_PASSWORD);

        User user = new User(userName, password);
        LOGGER.log(Level.INFO, "User object created successfully with username: {0}", userName);

        return user;
    }
}
