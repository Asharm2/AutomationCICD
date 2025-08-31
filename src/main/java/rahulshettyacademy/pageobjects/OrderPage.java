package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;
    public OrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tbody//td[2]")
    List<WebElement> orderedProductNames;

    @FindBy(xpath = "//tbody")
    WebElement tableBody;

    public Boolean verifyOrderDisplay(String productName){
        waitForElementToAppear(tableBody);
        Boolean match =  orderedProductNames.stream().anyMatch(orderedProductName -> orderedProductName.getText().equalsIgnoreCase(productName));
        return match;
    }


}
