package AmanEnterprise.pageObjects;

import AmanEnterprise.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        //initialisation
        super(driver); //sending the driver from child class to parent class
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//     WebElement userEmail = driver.findElement(By.id("userEmail"));

    //PageFactory
    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement PasswordEle;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "[class *= 'flyInOut']")
    WebElement errorMessage;


    //ACTION METHOD
    public ProductCatalog loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        PasswordEle.sendKeys(password);
        submit.click();
        ProductCatalog productCatalog = new ProductCatalog(driver);
        return productCatalog;
    }

    public void goToLandingPage() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage() {
        waitForElementToBeVisible(errorMessage);
        return errorMessage.getText();
    }


}
