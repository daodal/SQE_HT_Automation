package Task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DownloadReport { //This case have sometimes trouble running in FF

    WebDriver driver;
    String downloadPath = "c:\\temp1";

    @BeforeTest
    public void setup() {
        // Download settings for Firefox
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addPreference("browser.download.folderList", 2);
        firefoxOptions.addPreference("browser.download.dir", downloadPath);
        firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        firefoxOptions.addPreference("pdfjs.disabled", true);

        // Download settings for Chrome
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        chromeOptions.setExperimentalOption("prefs", prefs);

        //Define browser
        String browser = System.getProperty("browser");

        if (browser != null && browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver(chromeOptions);
        } else {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
            driver = new FirefoxDriver(firefoxOptions);
        }

        driver.manage().window().maximize();

    }

    @Test
    public void testFileDownload() {
        driver.get("https://www.epam.com/about");

        //Accept Cookies
        WebElement acceptCookieBtn = driver.findElement(By.cssSelector("#onetrust-accept-btn-handler"));
        acceptCookieBtn.click();

        //Locate Download button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement downloadBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.button:nth-child(3) > div:nth-child(1) > a:nth-child(1)")));
        downloadBtn.click(); // Have troubles in FF - sometimes it is not clickable at point (1289,1285) because another element <div id="onetrust-banner-sdk" class="otFlat bottom ot-wo-title vertical-align-content ot-buttons-fw"> obscures it

        // Wait for file to download and check
        waitForFileToDownload("EPAM_Corporate_Overview_Q3_october.pdf");

        // Verify if file exists in the system
        File file = new File(downloadPath + "\\EPAM_Corporate_Overview_Q3_october.pdf");
        Assert.assertTrue(file.exists(), "Downloaded file not found");
    }

    public void waitForFileToDownload(String fileName) {
        int i = 0;
        while (i++ < 20) {
            File file = new File(downloadPath + "\\" + fileName);
            if (file.exists() && file.isFile()) {
                break;
            }
            sleep(500);
        }
    }
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void teardown() {
        driver.quit();
        File file = new File(downloadPath + "\\EPAM_Corporate_Overview_Q3_october.pdf");
        if (file.exists()) {
            boolean isFileDeleted = file.delete();
            System.out.println("File deletion status: " + isFileDeleted);
        }
    }
}
