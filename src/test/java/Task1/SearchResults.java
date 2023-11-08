package Task1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class SearchResults {
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

        driver.get("https://www.epam.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement searchIcon = driver.findElement(By.cssSelector(".search-icon"));
        searchIcon.click();

        // Find the search input field and type "AI"

        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("new_form_search")));
        searchInput.sendKeys("AI");
        searchInput.sendKeys(Keys.RETURN);

        // Wait for the search results to load
        WebElement results = wait.until(ExpectedConditions.elementToBeClickable(By.className("search")));

        // Check if the search results are displayed
        WebElement searchResults = driver.findElement(By.className("search-results__exception-message"));
        Assert.assertFalse(searchResults.isDisplayed());
    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }
}
