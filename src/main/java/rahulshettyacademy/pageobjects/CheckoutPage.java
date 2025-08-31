package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.util.List;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement country;

    @FindBy(css = ".ta-results")
    WebElement suggestedCountryDropDown;

    @FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]")  //
    WebElement selectCountry;

    @FindBy(css = ".action__submit")
    WebElement submit;

    public void selectCountry(String countryName){
        actionToSendKeys(country, countryName);
        waitForElementToAppear(suggestedCountryDropDown);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }

}
