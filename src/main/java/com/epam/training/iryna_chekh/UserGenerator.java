package com.epam.training.iryna_chekh;

import com.epam.training.iryna_chekh.user.User;

public class UserGenerator {
    public static final String USER_NAME="user.name";
    public static final String USER_PASSWORD="user.password";

    public static User createUser(){
        return new User(ResourcesDataReader.getData(USER_NAME),ResourcesDataReader.getData(USER_PASSWORD));
    }
}
