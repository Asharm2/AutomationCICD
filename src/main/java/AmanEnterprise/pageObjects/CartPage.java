package AmanEnterprise.pageObjects;

import AmanEnterprise.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    public CartPage(WebDriver driver){
        //initialisation
        super(driver); //sending the driver from child class to parent class
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //PageFactory
    @FindBy(css = ".cartSection h3")
    List<WebElement> productTitles;

    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;



    //ACTION METHOD
    public Boolean VerifyProductDisplay(String proName){
        return productTitles.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(proName));
    }

    public CheckoutPage goToCheckout(){
        checkoutEle.click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }



}
