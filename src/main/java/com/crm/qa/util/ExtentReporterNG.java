package com.crm.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.testng.*;
import org.testng.IReporter;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.*;

public class ExtentReporterNG implements IReporter, ITestListener {

    private ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    // ------------------ Extent Report Generation ------------------

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportDir = outputDirectory + File.separator + "Reports";
        new File(reportDir).mkdirs();

        String reportPath = reportDir + File.separator + "ExtentReport_" + timeStamp + ".html";
        extent = new ExtentReports(reportPath, true);

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }

        extent.flush();
        extent.close();
    }

    private void buildTestNodes(IResultMap tests, LogStatus status) {
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                ExtentTest test = extent.startTest(result.getMethod().getMethodName());

                test.setStartedTime(getTime(result.getStartMillis()));
                test.setEndedTime(getTime(result.getEndMillis()));

                for (String group : result.getMethod().getGroups()) {
                    test.assignCategory(group);
                }

                Object screenshotPathObj = result.getAttribute("screenshotPath");
                if (status == LogStatus.FAIL && screenshotPathObj != null) {
                    String screenshotPath = screenshotPathObj.toString();
                    test.log(status, result.getThrowable());
                    test.log(status, "Screenshot below: " + test.addScreenCapture(screenshotPath));
                } else {
                    if (result.getThrowable() != null) {
                        test.log(status, result.getThrowable());
                    } else {
                        test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                    }
                }

                extent.endTest(test);
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    // ------------------ TestNG Listener Methods ------------------

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("✅ Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getName());
        try {
            String screenshotPath = TestUtil.takeScreenshotAtEndOfTest();
            result.setAttribute("screenshotPath", screenshotPath);
            System.out.println("📸 Screenshot captured: " + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ Test Skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("📂 Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("📁 Test Suite Finished: " + context.getName());
    }
}
