package AmanEnterprise.pageObjects;

import AmanEnterprise.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractComponent {

    WebDriver driver;

    public ProductCatalog(WebDriver driver) {
        super(driver);
        //initialisation
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//     WebElement userEmail = driver.findElement(By.id("userEmail"));
//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

    //PageFactory - this is only for WebElement
    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement animation;


    //these are not page factory code
    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");


    //ACTION METHOD
    public List<WebElement> getProductList() {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String proName) {
        return getProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(proName)).findFirst().orElse(null);
    }

    public void addProductToCart(String proName) {
        WebElement prod = getProductByName(proName);
        prod.findElement(addToCart).click();

        //waiting for confirmation message of Add to cart to display
        waitForElementToAppear(toastMessage);

        //waiting for animation to disappear
        waitForElementToDisappear(animation);
    }


}
