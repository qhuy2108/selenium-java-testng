package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_15_Popup_01 {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    WebDriverWait explicitWait;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //version 4
        driver.manage().window().setSize(new Dimension(1600,900));
    }

    @Test
    public void TC_01_Fixed_Popup_In_DOM_01() {
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.xpath("//button[contains(@class, 'MuiButtonBase-root') and text()= 'Đăng nhập']")).click();
        sleepInSecond(1);
        By loginPopup = By.xpath("//div[@role='dialog']");

        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        //input[@placeholder ='Tài khoản đăng nhập']
        //input[@placeholder ='Mật khẩu']
        //button[text()='Đăng nhập']//parent::div[contains(@style,'display:')]

        driver.findElement(By.xpath("//input[@placeholder ='Tài khoản đăng nhập']")).sendKeys("autocf");
        driver.findElement(By.xpath("//input[@placeholder ='Mật khẩu']")).sendKeys("autocf");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']//parent::div[contains(@style,'display:')]")).click();
        sleepInSecond(1);

        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'SnackbarContent-root')]")).isDisplayed());

        driver.findElement(By.xpath("//button[contains(@class, 'close-btn')]")).click();
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
    }

    @Test
    public void TC_02_Fixed_In_DOM_Kyna() {
    }

    @Test
    public void TC_03_Fixed_NOT_In_DOM_01() {

        driver.get("https://tiki.vn/");

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();

        sleepInSecond(10);

        WebElement closePopup = driver.findElement(By.cssSelector("div#VIP_BUNDLE"));
        if (closePopup.isDisplayed()) {
            closePopup.click();
        }
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("div[class='styles__Root-sc-2hr4xa-0 jyAQAr']")).isDisplayed());

        driver.findElement(By.cssSelector("p[class='login-with-email']")).click();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='email']/parent::div/following-sibling::span[1]")).getText(), "Email không được để trống" );
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span")).getText(), "Mật khẩu không được để trống" );

        driver.findElement(By.cssSelector("button[class='btn-close']")).click();

        //Assert.assertFalse(driver.findElement(By.cssSelector("div[class='styles__Root-sc-2hr4xa-0 jyAQAr']")).isDisplayed());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.cssSelector("div[class='styles__Root-sc-2hr4xa-0 jyAQAr']")).size(),0);

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
        return "AutoTest"+ new Random().nextInt(999) + "@vnn.uss";
    }
}
