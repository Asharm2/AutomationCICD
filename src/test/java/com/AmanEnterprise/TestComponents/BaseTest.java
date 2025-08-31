package com.AmanEnterprise.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import rahulshettyacademy.pageobjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    
    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {

        //setting path for properties file
        String userDirPath = System.getProperty("user.dir");
        String globalPropertiesFilePath = userDirPath + "\\src\\main\\java\\rahulshettyacademy\\resources\\GLobalData.properties";

        //Properties class
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(globalPropertiesFilePath);
        props.load(fis);

        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : props.getProperty("browser");

//        String browser = props.getProperty("browser");

        if (browser.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if(browser.contains("headless")){
                options.addArguments("headless"); //setting headless mode for chrome
            }
            driver = new ChromeDriver(options);
//            driver.manage().window().setSize(new Dimension(1440, 900));  use this if we see any flakiness while running because of screen size issue
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "C:\\Users\\AMAN\\IdeaProjects\\edgedriver_win64\\msedgedriver.exe");
            driver = new EdgeDriver();
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        System.setProperty("SE_MANAGER_TELEMETRY", "false");

        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        String jsonFilePath = System.getProperty("user.dir") + filePath;

        //read json to String
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

        //convert the above String into HashMap using Jackson DataBind
        ObjectMapper mapper = new ObjectMapper();

        //since the json that we are using is an array and have 2 indexes, it will create 2 HashMaps
        // and after creating these 2 HashMaps, put it in a List
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        //so now, this "data" is a list of 2 HashMaps
        return data;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, destination);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }


}
