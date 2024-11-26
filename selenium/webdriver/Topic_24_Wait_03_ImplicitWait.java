package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class Topic_24_Wait_03_ImplicitWait {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentWait;

    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1600,900));

        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        fluentWait = new FluentWait(driver);

    }

    //             ------------------------------------- XEM VIDEO -------------------------------------

    @Test
    public void TC_01_Equals_5s() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#star>button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>f4")).getText(), "Hello World!");
    }

    @Test
    public void TC_02_Less_Than_5s() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#star>button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>f4")).getText(), "Hello World!");
    }

    @Test
    public void TC_02_Greater_Than_5s() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#star>button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>f4")).getText(), "Hello World!");
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

    public String getEmailAddress () {
        return "AutoTest"+ new Random().nextInt(999) + "@@email.com";
    }

    public String getDateTimeNow() {
        Date date = new Date();
        return date.toString();
    }
}