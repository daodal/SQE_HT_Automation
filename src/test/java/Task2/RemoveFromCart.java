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

public class RemoveFromCart { //This test sometimes catches bug in Chrome
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
    public void removeFromCartTest() {
        driver.get("https://demowebshop.tricentis.com/blue-and-green-sneaker");

        //Click Add to Cart
        WebElement addToCart = driver.findElement(By.className("add-to-cart-button"));
        addToCart.click();

        //Clicking Shopping cart in header
        WebElement shoppingCartButton = driver.findElement(By.cssSelector(".header-links > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1) > span:nth-child(1)"));
        shoppingCartButton.click();

        //Comparing URL of Shopping cart
        String expectedURL = "https://demowebshop.tricentis.com/cart";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);

        //Check the number that shows in header near wishlist button

        String itemsInCartExp = "(1)";
        String itemsInCartAct = driver.findElement(By.cssSelector(".cart-qty")).getText();
        Assert.assertEquals(itemsInCartAct, itemsInCartExp);

        //Checking that product was added to wishlist
        String expectedName = "Blue and green Sneaker";
        String actualName = driver.findElement(By.cssSelector(".product > a:nth-child(1)")).getText();
        Assert.assertEquals(actualName, expectedName);

        //Checking remove checkbox
        WebElement removeFromCart = driver.findElement(By.cssSelector(".remove-from-cart > input:nth-child(2)"));
        removeFromCart.click();

        //Updating the list
        WebElement updateCart = driver.findElement(By.className("update-cart-button"));
        updateCart.click();

        //Checking that the cart is empty
        String cartMsgExp = "Your Shopping Cart is empty!";
        String cartMsgAct = driver.findElement(By.className("order-summary-content")).getText();
        Assert.assertEquals(cartMsgAct, cartMsgExp);

        //Check the number that shows in header near wishlist button
        String itemsInCartEmptyExp = "(0)";
        String itemsInCartEmptyAct = driver.findElement(By.className("cart-qty")).getText();
        Assert.assertEquals(itemsInCartEmptyAct, itemsInCartEmptyExp);
    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }
}
