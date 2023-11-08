package Task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToWitishlist {
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
    public void wishlistTest() {
        driver.get("https://demowebshop.tricentis.com/blue-and-green-sneaker");

        //Click Add to wishlist
        WebElement addToWishlist = driver.findElement(By.className("add-to-wishlist-button"));
        addToWishlist.click();

        //Clicking wishlist button
        WebElement wishlistButton = driver.findElement(By.cssSelector(".header-links > ul:nth-child(1) > li:nth-child(4) > a:nth-child(1) > span:nth-child(1)"));
        wishlistButton.click();

        //Comparing URL of wishlist
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String expectedURL = "https://demowebshop.tricentis.com/wishlist";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);

        //Check the number that shows in header near wishlist button
        String itemsInWishlistExp = "(1)";
        String itemsInWishlistAct = driver.findElement(By.className("wishlist-qty")).getText();
        Assert.assertEquals(itemsInWishlistAct, itemsInWishlistExp);

        //Checking that product was added to wishlist
        String expectedName = "Blue and green Sneaker";
        String actualName = driver.findElement(By.cssSelector(".product > a:nth-child(1)")).getText();
        Assert.assertEquals(actualName, expectedName);

    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }
}
