package AmanEnterprise.TestComponent;

import AmanEnterprise.resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent = ExtentReporterNG.getReportObject();
    ExtentTest test;

    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>(); //making the test thread-safe so that it does not get overridden

    @Override
    public void onTestStart(ITestResult result) {
        // Method implementation
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); //unique thread id
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Method implementation
        extentTest.get().log(Status.PASS, "Test Pass");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Method implementation
        extentTest.get().fail(result.getThrowable()); //pulls the unique thread id and checks what the matching test object

        try {
            //getting the driver from the instance which is running so that it can be sent to getScreenshot() method
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        //take screenshot
        String filePath;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        //attach screenshot
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Method implementation
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Method implementation
    }

    @Override
    public void onStart(ITestContext context) {
        // Method implementation
    }

    @Override
    public void onFinish(ITestContext context) {
        // Method implementation
        extent.flush();
    }

}









