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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PoliciesList {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
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
    public void testPoliciesList() {
        driver.get("https://www.epam.com/");

        // Get Policies
        List<WebElement> policiesList = driver.findElements(By.cssSelector("div.policies-links-wrapper li.links-item a.fat-links"));
        List<String> actualPolicies = policiesList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        Collections.sort(actualPolicies);

        // Expected policies
        List<String> expectedPolicies = Arrays.asList("INVESTORS", "COOKIE POLICY", "OPEN SOURCE", "APPLICANT PRIVACY NOTICE", "PRIVACY POLICY", "WEB ACCESSIBILITY");
        Collections.sort(expectedPolicies);

        //Comparing lists of policies
        Assert.assertEquals(actualPolicies, expectedPolicies, "Policies list does not match expected list");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
