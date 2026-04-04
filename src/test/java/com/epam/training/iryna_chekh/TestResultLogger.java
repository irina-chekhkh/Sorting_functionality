package com.epam.training.iryna_chekh;

import com.aventstack.extentreports.Status;
import com.epam.training.iryna_chekh.report.ExtentTestManager;
import org.testng.ITestResult;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestResultLogger {
    private static final Logger LOGGER = Logger.getLogger(TestResultLogger.class.getName());

    public static void logResult(ITestResult result) {
        Status status;
        String details;

        if (result.getStatus() == ITestResult.SUCCESS) {
            status = Status.PASS;
            details = "The test was successful";
            LOGGER.log(Level.INFO, "Test {0} passed successfully", result.getName());

        } else {
            Throwable throwable = result.getThrowable();
            String message = (throwable != null) ? throwable.getMessage() : "Unknown error";

            if (result.getStatus() == ITestResult.FAILURE) {
                status = Status.FAIL;
                details = "The test failed: " + message;
                LOGGER.log(Level.SEVERE, "Test {0} failed with error: {1}",
                        new Object[]{result.getName(), message});
            } else {
                status = Status.SKIP;
                details = "The test skipped: " + message;
                LOGGER.log(Level.WARNING, "Test {0} skipped. Reason: {1}",
                        new Object[]{result.getName(), message});
            }
        }
        ExtentTestManager.log(status, details);
    }

}
