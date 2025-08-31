package com.AmanEnterprise.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import rahulshettyacademy.resources.ExtentReporterNG;

import java.io.IOException;

//Ctrl + I --> shortcut key to implement methods of the interface

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent = ExtentReporterNG.getReportObject();
    ExtentTest test;

    // to run tests parallel and solve concurrency issues, we have to make each object of ExtentTest thread safe
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());  //setting method name as the test value
        extentTest.set(test); // sets unique thread id
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        test.log(Status.PASS, "Test passed");  used when we did not make it thread safe
        extentTest.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        test.fail(result.getThrowable()); //this will log the fail message
        extentTest.get().fail(result.getThrowable());

        // Step1 -> take screenshot
        String filePath = null ;
        try {
            //I cannot use Test method to get the driver because fields are associated in class level but not method level
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        try {
             filePath = getScreenshot(result.getMethod().getMethodName(), driver );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Step2 -> attach to the report
//        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); //we have to flush the object of ExtentReports to wrap the report and generate it
    }
}
