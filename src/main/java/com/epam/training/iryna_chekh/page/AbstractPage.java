package com.epam.training.iryna_chekh.page;

import com.epam.training.iryna_chekh.driver.Driver;

public abstract class AbstractPage {
    protected Driver driver;

    public AbstractPage(Driver driver) {
        this.driver = driver;
    }
}
