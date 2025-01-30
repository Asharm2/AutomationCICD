package AmanEnterprise.pageObjects;

import AmanEnterprise.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Page Factory
    @FindBy(css = ".hero-primary")
    WebElement confirmationMessage;


//    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".hero-primary"))));
//
//    String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

    public String getConfirmationMessage(){
        waitForElementToBeVisible(confirmationMessage);
        return confirmationMessage.getText();
    }


}
