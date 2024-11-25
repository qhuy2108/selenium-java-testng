package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_17_Frame_IFrame {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;
    Alert alert;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //version 4
        driver.manage().window().setSize(new Dimension(1600,900));

        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Iframe_formsite() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        sleepInSecond(5);
        WebElement formIframe = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        Assert.assertTrue(formIframe.isDisplayed());

        // driver.switchTo().frame(0);
        // driver.switchTo().frame("frame-one85593366");
        driver.switchTo().frame(formIframe); // Nen dung cach nay
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Senior");

        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();
        sleepInSecond(3);

        driver.findElement(By.cssSelector("button#login")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");


    }

    @Test
    public void TC_02_Iframe() {
        driver.get("https://kynaenglish.vn/"); // A
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb_iframe_widget iframe"))); // A->B
        String fbLikeNumber = driver.findElement(By.cssSelector("div._3-8w")).getText();
        System.out.println(fbLikeNumber);
        Assert.assertTrue(fbLikeNumber.contains("55K followers"));

        driver.switchTo().defaultContent();
    }


    @Test
    public void TC_03_Frame_SwitchTo_New_Page() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame("login_page");
        driver.findElement(By.name("fldLoginUserId")).sendKeys("daodamautofc");
        sleepInSecond(3);
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSecond(12);

        driver.switchTo().defaultContent();

        WebElement passwordTextBox = driver.findElement(By.cssSelector("input#keyboard"));
        Assert.assertTrue(passwordTextBox.isDisplayed());
        passwordTextBox.sendKeys("123123321");

    }

    @Test
    public void TC_04_Random_NOT_In_DOM_P2() {

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
