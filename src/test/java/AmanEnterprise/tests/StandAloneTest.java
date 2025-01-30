package AmanEnterprise.tests;

import AmanEnterprise.pageObjects.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args) throws InterruptedException {

        String proName = "adidas original";


        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");

        LandingPage lp = new LandingPage(driver);

        driver.findElement(By.id("userEmail")).sendKeys("testaman@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Test@123");
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector("div[class=\"container\"] div[class=\"row\"] div[class *= \"col-md-6\"]"));

        WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(proName)).findFirst().orElse(null);

        assert prod != null;
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", prod);
//        Thread.sleep(2000L);

//        wait.until(ExpectedConditions.visibilityOf(prod));
//        wait.until(ExpectedConditions.elementToBeClickable(prod));
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        //waiting for confirmation message of Add to cart to display
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        //waiting for animation to disappear
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("[routerlink=\"/dashboard/cart\"]")).click();

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(proName));
        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder *= Country]")) ,"India").build().perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));

        List<WebElement> autoSuggestions = driver.findElements(By.cssSelector(".ta-item"));

        for(WebElement sugg : autoSuggestions){
            if(sugg.getText().equals("India")){
                sugg.click();
            }
        }

        driver.findElement(By.cssSelector("[class *= \"submit\"]")).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".hero-primary"))));

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

        driver.close();


    }
}
