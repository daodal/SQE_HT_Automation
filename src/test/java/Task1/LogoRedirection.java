package Task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogoRedirection {
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
    public void testSearch() {
        driver.get("https://www.epam.com/about");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //Locate and click on company logo
        WebElement logo = driver.findElement(By.cssSelector("a.header__logo-container:nth-child(2) > img:nth-child(3)"));
        logo.click();

        // Verify new URL
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.epam.com/"));
    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }
}
