package com.epam.training.iryna_chekh.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExtentReportManager {
    private final static Logger LOGGER = Logger.getLogger(ExtentReportManager.class.getName());
    private static ExtentReports extentReport;

    public static ExtentReports getExtentReport() {
        if (extentReport == null) {
            String reportPath = "reports\\report.html";
            LOGGER.log(Level.INFO, "Initializing ExtentReport at path: {0}", reportPath);
            try {
                ExtentSparkReporter report = new ExtentSparkReporter(reportPath);
                report.config().setReportName("Automation testing");
                report.config().setDocumentTitle("Test Results");
                report.config().enableOfflineMode(true);
                report.config().setTimeStampFormat("EEEE, MMMM dd yyyy hh:mm");
                report.config().setEncoding("UTF-8");

                extentReport = new ExtentReports();
                extentReport.attachReporter(report);
                LOGGER.log(Level.INFO, "ExtentReport initialized successfully");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Failed to initialize ExtentReport: {0}", e.getMessage());
                throw e;
            }
        } else {
            LOGGER.log(Level.CONFIG, "ExtentReport already initialized, returning existing instance");
        }
        return extentReport;
    }
}
