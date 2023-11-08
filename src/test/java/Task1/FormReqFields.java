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

public class FormReqFields {
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
    public void requiredCheck() {
        driver.get("https://www.epam.com/about/who-we-are/contact");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("form-constructor")));

        WebElement form = driver.findElement(By.className("form-constructor"));

        //Select the Reason for Your Inquiry*
        Assert.assertTrue(form.findElement(By.xpath("/html/body/div[2]/main/div[1]/div[2]/section/div[3]/div[6]/div/form/div[2]/div/div/div/div/div[1]/div[1]")).getAttribute("data-required").equals("true"));
        //First Name*
        Assert.assertTrue(form.findElement(By.xpath("/html/body/div[2]/main/div[1]/div[2]/section/div[3]/div[6]/div/form/div[2]/div/div/div/div/div[2]/div/div[1]/div/div/div")).getAttribute("data-required").equals("true"));
        //Last Name*
        Assert.assertTrue(form.findElement(By.xpath("/html/body/div[2]/main/div[1]/div[2]/section/div[3]/div[6]/div/form/div[2]/div/div/div/div/div[2]/div/div[2]/div/div/div")).getAttribute("data-required").equals("true"));
        //Email*
        Assert.assertTrue(form.findElement(By.xpath("/html/body/div[2]/main/div[1]/div[2]/section/div[3]/div[6]/div/form/div[2]/div/div/div/div/div[3]/div/div[1]/div/div/div")).getAttribute("data-required").equals("true"));
        //Phone*
        Assert.assertTrue(form.findElement(By.xpath("/html/body/div[2]/main/div[1]/div[2]/section/div[3]/div[6]/div/form/div[2]/div/div/div/div/div[3]/div/div[2]/div/div/div")).getAttribute("data-required").equals("true"));
        //Location*
        Assert.assertTrue(form.findElement(By.xpath("/html/body/div[2]/main/div[1]/div[2]/section/div[3]/div[6]/div/form/div[2]/div/div/div/div/div[5]/div/div[1]")).getAttribute("data-required").equals("true"));
        //How did you hear about EPAM?*
        Assert.assertTrue(form.findElement(By.xpath("/html/body/div[2]/main/div[1]/div[2]/section/div[3]/div[6]/div/form/div[2]/div/div/div/div/div[8]/div[1]")).getAttribute("data-required").equals("true"));

    }

    @AfterClass
    public void shutUp() {
        driver.quit();
    }
}
