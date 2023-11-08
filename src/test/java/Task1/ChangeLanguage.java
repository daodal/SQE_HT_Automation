package Task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChangeLanguage {
    private static WebDriver driver;

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
    public void languageCheck() {
        driver.get("https://www.epam.com/");

        // Selecting ukrainian language in dropdown
        WebElement languageDropdown = driver.findElement(By.cssSelector(".location-selector__button > .location-selector__button-language"));
        languageDropdown.click();
        WebElement ukrLang = driver.findElement(By.cssSelector("li.location-selector__item:nth-child(6) > a:nth-child(1)"));
        ukrLang.click();

        // Current language verification
        WebElement head = driver.findElement(By.cssSelector("html"));
        Assert.assertTrue(head.getAttribute("lang").contains("uk-UA"));
    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }
}
