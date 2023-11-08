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

public class UserLogin {
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
    public void loginTest() {
        driver.get("https://demowebshop.tricentis.com/login");

        //Provide user data
        driver.findElement(By.id("Email")).sendKeys("AutoTest_1@test.com");
        driver.findElement(By.id("Password")).sendKeys("Password1");

        //Click "Log in" button
        driver.findElement(By.className("login-button")).click();

        // Locate and get account data
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String expectedMessage = "AutoTest_1@test.com";
        WebElement account = driver.findElement(By.className("account"));
        String actualMessage = account.getText();

        // Validate that login was successful
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }
}
