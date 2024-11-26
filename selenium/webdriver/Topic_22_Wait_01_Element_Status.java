package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_22_Wait_01_Element_Status {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    WebDriverWait explicitWait;

    By reconfirmEmailTextbox = By.cssSelector("input[name='reg_email__']");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //version 4
        driver.manage().window().setSize(new Dimension(1600,900));

        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Visible() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();sleepInSecond(2);
        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys(getEmailAddress());sleepInSecond(2);

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextbox));
        Assert.assertTrue(driver.findElement(reconfirmEmailTextbox).isDisplayed());
    }

    @Test
    public void TC_02_Invisibile_In_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();sleepInSecond(2);
        //  khong co trong UI
        //  co trong html

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear(); sleepInSecond(2);

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

        Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());
    }

    @Test
    public void TC_03_Invisibile_NOT_In_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();sleepInSecond(2);
        // khong co trong UI (bat buoc)
        // khong co trong html

        driver.get("https://www.facebook.com/");
        sleepInSecond(2);

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));

    }

    @Test
    public void TC_04_Presence() {
        // co trong html (bat buoc) && co trong UI
        // Khi UI tat di nhung van con trong HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

        // XEM VIDEO DO TRANG THAY DOI

    }
    @Test
    public void TC_05_Staless() {
        // phai dung webelement nen khai bao lai
        WebElement reconfirmEmail = driver.findElement(reconfirmEmailTextbox);

        // Luc dau Element co xuat hien

        // sau do TAT di lam cho element KHONG co trong html (bat buoc) && KHONG co trong UI
        explicitWait.until(ExpectedConditions.stalenessOf(reconfirmEmail));

        // XEM VIDEO DO TRANG THAY DOI


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
}