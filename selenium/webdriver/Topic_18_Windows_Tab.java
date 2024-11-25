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

public class Topic_18_Windows_Tab {
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
    public void TC_01_TAB() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        String parentID = driver.getWindowHandle();
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
        System.out.println("Parent Tab ID: " + parentID);

        driver.findElement(By.xpath("//a[text() = 'GOOGLE']")).click();
        sleepInSecond(3);

        // Luc nay da co 2 tab roi , get ID tat ca
        Set<String> allWindowsIDs = driver.getWindowHandles();

        for (String id : allWindowsIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                break;
            }
            sleepInSecond(1);
        }

        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
        System.out.println("Tab ID: " + driver.getWindowHandle());
        driver.findElement(By.cssSelector("textarea[name= 'q']")).sendKeys("Selenium TestNG");
        String googleTabID = driver.getWindowHandle();

        // Quay ve trang A
        for (String id : allWindowsIDs) {
            if (!id.equals(googleTabID)) {
                driver.switchTo().window(id);
                break;
            }
            sleepInSecond(1);
        }

        driver.findElement(By.xpath("//a[text() = 'FACEBOOK']")).click();
        sleepInSecond(3);

        switchToWindowByTitle("Facebook – log in or sign up");
        driver.findElement(By.cssSelector("input#email")).sendKeys("Selenium TestNG");
        sleepInSecond(3);

        switchToWindowByTitle("Selenium WebDriver");

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


    @Test
    public void TC_02_Switch_To_Windows() {
        driver.get("http://live.techpanda.org/index.php/mobile.html");
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']" +
                "//a[@class='link-compare']")).click();

        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']" +
                "//a[@class='link-compare']")).click();

        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        sleepInSecond(1);

        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
        sleepInSecond(5);
        driver.findElement(By.cssSelector("button[title='Close Window']")).click();
        sleepInSecond(1);
        // driver van dang o trang compare
        switchToWindowByTitle("Mobile");
        driver.findElement(By.id("search")).sendKeys("Sony");

    }


    @Test
    public void TC_03_TAB() {
        driver.get("https://kynaenglish.vn/");
        String parentID = driver.getWindowHandle();

        driver.findElement(By.cssSelector("img[alt='Facebook']")).click();
        sleepInSecond(3);

        switchToWindowByTitle("Facebook");
        driver.findElement(By.cssSelector("#login_popup_cta_form input[name='email']")).sendKeys("AuTOTEST_OK");
        sleepInSecond(2);

        switchToWindowByTitle("KYNA ENGLISH - MANG LẠI NHIỀU TRẢI NGHIỆM HỌC TẬP HIỆU QUẢ HƠN");
        driver.findElement(By.cssSelector("img[alt='Youtube']")).click();
        sleepInSecond(2);

        switchToWindowByTitle("Kyna For Kids - YouTube");
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement
                (By.cssSelector("div.page-header-view-model-wiz__page-header-headline-info h1 span")).getText(),"Kyna For Kids");
        closeOtherWindows(parentID);
    }

    public void closeOtherWindows(String keepID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if(!id.equals(keepID)){
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(keepID);
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
