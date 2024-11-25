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
import java.util.Set;

public class Topic_18_Windows_Tab_Selenium_Version4 {
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
        driver.manage().window().setSize(new Dimension(1600, 900));

        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_TAB_WINDOW() {

        // V4 này khi switch sang win hay tab mới thi driver no se tu theo.
        // Neu Quay ve Trang truoc khi khong truyen URL vao Van Phai Swich Driver Thu Cong

        driver.get("https://kynaenglish.vn/");
        System.out.println("Driver ID kynangEnglish = " + driver.toString());

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://kynaenglish.vn/about");

        driver.findElement(By.cssSelector("button.Elements_btn_pink__G1Qh7")).click();
        sleepInSecond(2);

        switchToWindowByTitle("KYNA ENGLISH - MANG LẠI NHIỀU TRẢI NGHIỆM HỌC TẬP HIỆU QUẢ HƠN");
        Assert.assertEquals(driver.getCurrentUrl(), "https://kynaenglish.vn/");
        sleepInSecond(2);
        driver.findElement(By.cssSelector("div.header_wrapper_btn__kcpqE a.header_btn_login__rkwOJ")).click();
        sleepInSecond(2);

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://kynaenglish.vn/about");

        driver.findElement(By.cssSelector("button.Elements_btn_pink__G1Qh7")).click();
        sleepInSecond(2);

        driver.switchTo().newWindow(WindowType.TAB).get("https://www.facebook.com/Kynaenglish1kem1");
        driver.findElement(By.cssSelector("#login_popup_cta_form input[name='email']")).sendKeys("AuTOTEST_OK");
        sleepInSecond(2);


    }


    // Thao tac lai nhieu Lan tao ham` dung cho nhanh
    public void switchToWindowByID(String expectedID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(expectedID)) {
                driver.switchTo().window(id);
            }
        }
    }

    // Do khong the Lay ID tu Tab Parent (String googleTabID = driver.getWindowHandle();) Nen Phai tao Ra Ham nay
    public void switchToWindowByTitle(String expectedTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            String currentPageTitle = driver.getTitle();

            if (currentPageTitle.equals(expectedTitle)) {
                break;
            }
        }
        sleepInSecond(1);
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
        return "AutoTest" + new Random().nextInt(999) + "@vnn.uss";
    }
}
