package AmanEnterprise.pageObjects;

import AmanEnterprise.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {

    WebDriver driver;

    public OrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PAGE FACTORY
    @FindBy(xpath = "//tbody //td[2]")
    List<WebElement> getProductNames;


    //    ACTION METHODS
    public Boolean verifyOrderDisplay(String proName){
        return getProductNames.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(proName));
    }
}
