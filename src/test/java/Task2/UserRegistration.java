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

public class UserRegistration {
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
    public void registrationTest() {
        driver.get("https://demowebshop.tricentis.com/register");

        //Provide data
        driver.findElement(By.id("FirstName")).sendKeys("FirstName");
        driver.findElement(By.id("LastName")).sendKeys("LastName");
        long timestamp = System.currentTimeMillis();
        driver.findElement(By.id("Email")).sendKeys("AutoTest_" + timestamp + "@test.com");
        driver.findElement(By.id("Password")).sendKeys("Password1");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("Password1");

        //Click "Register" button
        driver.findElement(By.id("register-button")).click();

        // Locate and get after-registration text
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String expectedMessage = "Your registration completed";
        WebElement message = driver.findElement(By.className("result"));
        String actualMessage = message.getText();

        // Validate that "Your registration completed" is shown after registration
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }
}
