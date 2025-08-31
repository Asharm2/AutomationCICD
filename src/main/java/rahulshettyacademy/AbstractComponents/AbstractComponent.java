package rahulshettyacademy.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css = "button[routerlink*='myorders']")
    WebElement orderHeader;

    public CartPage goToCartPage(){
        cartHeader.click();
        return new CartPage(driver);
    }

    public OrderPage goToOrdersPage(){
            orderHeader.click();
            return new OrderPage(driver);
    }

    public void waitForElementToAppear(By findBy ) throws InterruptedException {
        Thread.sleep(1000L);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToAppear(WebElement ele ){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }


    public void waitForElementToDisappear(WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public void actionToSendKeys(WebElement ele, String keysToEnter){
        Actions a = new Actions(driver);
        a.sendKeys(ele, keysToEnter).build().perform();
    }




}
