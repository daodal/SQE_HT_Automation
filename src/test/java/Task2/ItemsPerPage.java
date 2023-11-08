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

import java.util.List;

public class ItemsPerPage {
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
    public void numberOfItemsTest() {
        driver.get("https://demowebshop.tricentis.com/apparel-shoes");

        // Selecting 4 items per page
        Select prodPerPage4 = new Select(driver.findElement(By.id("products-pagesize")));
        prodPerPage4.selectByVisibleText("4");

        //Counting products on page
        List<WebElement> productsCount4 = driver.findElements(By.className("product-item"));
        int actualNumber4 = productsCount4.size();

        //Validating that number of items match
        int expected4 = 4;
        Assert.assertEquals(actualNumber4, expected4);


        // Selecting 8 items per page
        Select prodPerPage8 = new Select(driver.findElement(By.id("products-pagesize")));
        prodPerPage8.selectByVisibleText("8");

        //Counting products on page
        List<WebElement> productsCount8 = driver.findElements(By.className("product-item"));
        int actualNumber8 = productsCount8.size();

        //Validating that number of items match
        int expected8 = 8;
        Assert.assertEquals(actualNumber8, expected8);


        // Selecting 12 items per page
        Select prodPerPage12 = new Select(driver.findElement(By.id("products-pagesize")));
        prodPerPage12.selectByVisibleText("12");

        //Counting products on page
        List<WebElement> productsCount12 = driver.findElements(By.className("product-item"));
        int actualNumber12 = productsCount12.size();

        //Validating that number of items match
        int expected12 = 12;
        Assert.assertEquals(actualNumber12, expected12);
    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }

}
