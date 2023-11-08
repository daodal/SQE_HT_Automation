package Task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingItems {
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
    public void sortingTest() {
        driver.get("https://demowebshop.tricentis.com/apparel-shoes");

        // Select "Created on" from dropdown
        Select sortByCreatedOn = new Select(driver.findElement(By.id("products-orderby")));
        sortByCreatedOn.selectByVisibleText("Created on");

        // Get the 'data-productid' attribute of each product
        List<WebElement> products = driver.findElements(By.className("product-item"));
        ArrayList<Integer> productIDs = new ArrayList<>();
        for (WebElement product : products) {
            productIDs.add(Integer.valueOf(product.getAttribute("data-productid")));
        }

        // Create a sorted copy of the product IDs list in reverse order
        ArrayList<Integer> sortedProductIDsDESC = new ArrayList<>(productIDs);
        Collections.sort(sortedProductIDsDESC, Collections.reverseOrder());

        // Check if products are sorted IDs descending
        Assert.assertEquals(productIDs, sortedProductIDsDESC);



        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));



        // Select "Price: Low to High" from dropdown
        Select sortByPriceASC = new Select(driver.findElement(By.id("products-orderby")));
        sortByPriceASC.selectByVisibleText("Price: Low to High");

        // Get the price of each product
        List<WebElement> productsPrice = driver.findElements(By.className("actual-price"));
        ArrayList<Float> productPrice = new ArrayList<>();
        for (WebElement productPrc : productsPrice) {
            productPrice.add(Float.valueOf(productPrc.getText()));
        }

        // Create a sorted copy of the price list
        ArrayList<Float> sortedProductPriceASC = new ArrayList<>(productPrice);
        Collections.sort(sortedProductPriceASC);

        //Compare two lists
        Assert.assertEquals(productPrice, sortedProductPriceASC);

    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }
}
