package com.epam.training.iryna_chekh;

public class User {
    private final String userName;
    private final String userPassword;

    public User(){
        userName = "standard_user";
        userPassword="secret_sauce";
    }

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
