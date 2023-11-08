package Task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class ComputersSubGroup {

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
    public void computersCategoryTest() {
        driver.get("https://demowebshop.tricentis.com/computers");

        //Checking number of sub-categories
        List<WebElement> subCategoriesNum = driver.findElements(By.className("sub-category-item"));
        int expectedCount = 3;
        int actualCount = subCategoriesNum.size();
        Assert.assertEquals(actualCount, expectedCount);


        WebElement subCategories = driver.findElement(By.className("sub-category-grid"));

        // Checking Desktops subcategory
        Assert.assertEquals(subCategories.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div[2]/div[2]/div[1]/div[1]/div/h2/a")).getText(), "Desktops");
        // Checking Notebooks subcategory
        Assert.assertEquals(subCategories.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div[2]/div[2]/div[1]/div[2]/div/h2/a")).getText(), "Notebooks");
        // Checking Accessories subcategory
        Assert.assertEquals(subCategories.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div[2]/div[2]/div[1]/div[3]/div/h2/a")).getText(), "Accessories");
    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }
}
