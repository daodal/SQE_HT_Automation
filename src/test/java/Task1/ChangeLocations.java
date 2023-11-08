package Task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ChangeLocations {
    private WebDriver driver;

    @BeforeClass
    public void setDriver() {
        String browser = System.getProperty("browser");
        /*
        if (browser != null && browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        */
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void requiredCheck() {
        driver.get("https://www.epam.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //Accept Cookies
        WebElement acceptCookieBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
        acceptCookieBtn.click();

        //Clicking EMEA
        WebElement emeaBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='EMEA']")));
        emeaBtn.click();

        // Verifying EMEA list of countries is active
        WebElement emeaContries = driver.findElement(By.cssSelector("div.tabs-23__item:nth-child(3)"));
        Assert.assertTrue(emeaContries.getAttribute("class").contains("active"), "EMEA list of countries is NOT active");

        //Clicking APAC
        WebElement apacBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='APAC']")));
        apacBtn.click();

        // Verifying APAC list of countries is active
        WebElement apacContries = driver.findElement(By.cssSelector("div.tabs-23__item:nth-child(4)"));
        Assert.assertTrue(apacContries.getAttribute("class").contains("active"), "APAC list of countries is NOT active");

        //Clicking AMERICAS
        WebElement americasBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='AMERICAS']")));
        americasBtn.click();

        // Verifying AMERICAS list of countries is active
        WebElement americasContries = driver.findElement(By.cssSelector("div.tabs-23__item:nth-child(2)"));
        Assert.assertTrue(americasContries.getAttribute("class").contains("active"), "AMERICAS list of countries is NOT active");
    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }
}
