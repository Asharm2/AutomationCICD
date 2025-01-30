package AmanEnterprise.pageObjects;

import AmanEnterprise.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        //initialisation
        super(driver); //sending the driver from child class to parent class
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory
    @FindBy(css = "[placeholder *= 'Country']")
    WebElement Country;

    @FindBy(css = ".ta-results")
    WebElement suggestiveDropdown;

    @FindBy(css = ".ta-item")
    List<WebElement> autoSuggestions;

    @FindBy(css = "[class *= \"submit\"]")
    WebElement PlaceOrder;


    //ACTION METHOD
    public void selectCountry() {
        Actions a = ActionInitiate();
        a.sendKeys(Country, "India").build().perform();
        waitForElementToBeVisible(suggestiveDropdown);

        for (WebElement requiredCountry : autoSuggestions) {
            if (requiredCountry.getText().equals("India")) {
                requiredCountry.click();
            }
        }

    }

    public ConfirmationPage submitOrder(){
        PlaceOrder.click();
        return new ConfirmationPage(driver);
    }


}
