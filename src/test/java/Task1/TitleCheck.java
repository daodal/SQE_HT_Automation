package Task1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TitleCheck {
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
    public void TitleCheck() {
        driver.get("https://www.epam.com/");

        // Getting title
        String epamTitle = driver.getTitle();
        String epamExpectedTitle = "EPAM | Software Engineering & Product Development Services";

        // Title verification
        Assert.assertEquals(epamTitle, epamExpectedTitle);
    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }

}
