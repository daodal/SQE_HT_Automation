package Task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddToCart { //This test sometimes catches bug in Chrome
    private WebDriver driver;

    @BeforeClass
    public void setDriver() {
        String browser = System.getProperty("browser");

        if (browser != null && browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
    }

    @Test
    public void addToCartTest() {
        driver.get("https://demowebshop.tricentis.com/blue-and-green-sneaker");

        //Click Add to Cart
        WebElement addToCart = driver.findElement(By.className("add-to-cart-button"));
        addToCart.click();

        //Clicking Shopping cart in header
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement ShoppingCartButton = driver.findElement(By.cssSelector(".header-links > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1) > span:nth-child(1)"));
        ShoppingCartButton.click();

        //Comparing URL of Shopping cart
        String expectedURL = "https://demowebshop.tricentis.com/cart";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);

        //Checking that product was added to wishlist
        String expectedName = "Blue and green Sneaker";
        String actualName = driver.findElement(By.cssSelector(".product > a:nth-child(1)")).getText();
        Assert.assertEquals(actualName, expectedName);

        //Check the number that shows in header near wishlist button
        String itemsInCartExp = "(1)";
        String itemsInCartAct = driver.findElement(By.className("cart-qty")).getText();
        Assert.assertEquals(itemsInCartAct, itemsInCartExp);

    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }

}
