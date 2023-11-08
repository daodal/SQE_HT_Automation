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

public class LightDarkMode {
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
    public void modeCheck() {
        driver.get("https://www.epam.com/");

        // Clicking on theme switcher
        WebElement themeSwitcher = driver.findElement(By.cssSelector(".header__content > .theme-switcher-ui > .theme-switcher"));
        themeSwitcher.click();

        // Website current mode check
        WebElement body = driver.findElement(By.cssSelector("body"));
        Assert.assertTrue(body.getAttribute("class").contains("light-mode"));
    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }

}
