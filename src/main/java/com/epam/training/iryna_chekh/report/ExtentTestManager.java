package com.epam.training.iryna_chekh.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ExtentTestManager {
    private static final Logger LOGGER = Logger.getLogger(ExtentTestManager.class.getName());
    private static final ExtentReports extent = ExtentReportManager.getExtentReport();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static synchronized void createTest(String testName) {
        LOGGER.log(Level.INFO, "Creating new ExtentTest with name: {0}", testName);
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        LOGGER.log(Level.CONFIG, "ExtentTest stored in ThreadLocal");
    }

    public static synchronized void log(Status status, String details) {
        LOGGER.log(Level.FINE, "Logging to ExtentTest: status={0}, details={1}", new Object[]{status, details});
        test.get().log(status, details);
    }

    public static ExtentTest getTest() {
        LOGGER.log(Level.CONFIG, "Retrieving current ExtentTest from ThreadLocal");
        return test.get();
    }

    public static void flushReport() {
        LOGGER.log(Level.INFO, "Flushing ExtentReport to file");
        extent.flush();
        LOGGER.log(Level.INFO, "ExtentReport flushed successfully");
    }

}
