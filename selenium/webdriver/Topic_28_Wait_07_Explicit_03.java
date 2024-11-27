package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class Topic_28_Wait_07_Explicit_03 {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentWait;

    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    String sgName = "saigon.jpg";
    String hueName = "hue.jpg";
    String hnName = "hanoi.jpg";

    String uploadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;

    String sgFilePath =   uploadFilePath + sgName;
    String hueFilePath =  uploadFilePath + hueName;
    String hnFilePath =   uploadFilePath + hnName;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1600, 900));
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        fluentWait = new FluentWait<>(driver);

    }


    @Test
    public void TC_01_Ajax_Loading() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        By selectedDate = By.cssSelector("span#ctl00_ContentPlaceholder1_Label1");

        Assert.assertEquals(driver.findElement(By.cssSelector
                ("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()= '22']")));
        driver.findElement(By.xpath("//a[text()= '22']")).click();

        // Wait icon Loading Bien Mat
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1'] div.raDiv")));

        Assert.assertEquals(driver.findElement(By.cssSelector
                ("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Friday, November 22, 2024");

    }

    @Test
    public void TC_02_Upload_Files() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://gofile.io/welcome");

        // Wait icon spiner load bien mat + Verify
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));

        // Wait + Click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ajaxLink>button"))).click();

        // Wait + Click [ SO NHIEU LIST : driver.findElements ]
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        By upLoadFileBy =By.cssSelector("input[type='file']");
        driver.findElement(upLoadFileBy).sendKeys(sgFilePath + "\n" + hueFilePath + "\n" + hnFilePath);

        // Wait icon spiner load bien mat + Verify
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        // Wait progress bar load bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar")))));

        // click vao link download sau khi up thanh cong
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.mainUploadSuccessLink a"))).click();

        // Wait + Verify button play co tai tung hinh
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//span[text()='"+ sgName +"']/ancestor::div[contains(@class, 'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//span[text()='"+ hnName +"']/ancestor::div[contains(@class, 'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//span[text()='"+ hueName +"']/ancestor::div[contains(@class, 'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());


    }

    @Test
    public void TC_03_Greater_Than_5s() {
    }


    @AfterClass
    public void afterClass() {
        // driver.quit();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getEmailAddress() {
        return "AutoTest" + new Random().nextInt(999) + "@@email.com";
    }

    public String getDateTimeNow() {
        Date date = new Date();
        return date.toString();
    }
}