package Task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ItemCheckout {
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
    public void checkoutTest() {
        driver.get("https://demowebshop.tricentis.com/blue-and-green-sneaker");


        //Click Add to Cart
        WebElement addToCart = driver.findElement(By.className("add-to-cart-button"));
        addToCart.click();

        //Clicking Shopping cart in header
        WebElement ShoppingCartButton = driver.findElement(By.cssSelector(".header-links > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1) > span:nth-child(1)"));
        ShoppingCartButton.click();

        //Accepting terms of service
        WebElement acceptTOS = driver.findElement(By.id("termsofservice"));
        acceptTOS.click();

        // Clicking checkout button
        WebElement checkoutBtn = driver.findElement(By.className("checkout-button"));
        checkoutBtn.click();

        // Clicking checkout as guest button
        WebElement checkoutAsGuestBtn = driver.findElement(By.className("checkout-as-guest-button"));
        checkoutAsGuestBtn.click();

        //Provide data to the required field in the billing address part
        driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("FirstName");
        driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("LastName");
        driver.findElement(By.id("BillingNewAddress_Email")).sendKeys("email@test.com");
        Select selectCountry = new Select(driver.findElement(By.id("BillingNewAddress_CountryId")));
        selectCountry.selectByVisibleText("United States");
        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Los Angeles");
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("St. Martin st. 123");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("00000");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("0123456789");

        //Clicking continue button
        WebElement newAdressNextStep = driver.findElement(By.cssSelector("input.new-address-next-step-button:nth-child(1)"));
        newAdressNextStep.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Clicking continue button
        WebElement secondContinueBtn = driver.findElement(By.cssSelector("html body div.master-wrapper-page div.master-wrapper-content div.master-wrapper-main div.center-1 div.page.checkout-page div.page-body.checkout-data ol#checkout-steps.opc li#opc-shipping.tab-section.allow.active div#checkout-step-shipping.step.a-item div#shipping-buttons-container.buttons input.button-1.new-address-next-step-button"));
        secondContinueBtn.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Clicking continue button
        WebElement shippingNextBtn = driver.findElement(By.className("shipping-method-next-step-button"));
        shippingNextBtn.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Clicking continue button
        WebElement paymentNextBtn = driver.findElement(By.className("payment-method-next-step-button"));
        paymentNextBtn.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Clicking continue button
        WebElement paymentInfoNextBtn = driver.findElement(By.className("payment-info-next-step-button"));
        paymentInfoNextBtn.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Clicking confirm button
        WebElement confirmBtn = driver.findElement(By.className("confirm-order-next-step-button"));
        confirmBtn.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Validating if the checkout was successful
        String expectedMsg = "Your order has been successfully processed!";
        WebElement checkoutMsg = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div/div/div[2]/div/div[1]/strong"));
        String actualMsg = checkoutMsg.getText();
        Assert.assertEquals(actualMsg, expectedMsg);

        // Validating navigation to successful checkout page
        String expectedURL = "https://demowebshop.tricentis.com/checkout/completed/";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);

    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }
}
