package webdriver;

import org.openqa.selenium.Alert;
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

import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class Topic_26_Wait_05_Explicit_01_Knowledge {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentWait;

    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1600, 900));

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        fluentWait = new FluentWait(driver);

    }

    //             ------------------------------------- XEM VIDEO -------------------------------------

    @Test
    public void TC_01() {
        // Chờ Alert xuat hien
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        // Wait for element Khong con trong DOM
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        // Wait for element xuat hien trong DOM ( khong quan tam co tren UI )
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        // Wait for LIST element xuat hien trong DOM
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));

        explicitWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector(""), By.cssSelector("")));

        // Wait for 1 -> n element hien thi tren UI
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements
                (driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector("")),
                        driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector(""))));
        // Wait for element duoc phep click
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        // Wait for page load xong Title
        explicitWait.until(ExpectedConditions.titleIs(""));
        driver.getTitle();

        explicitWait.until(ExpectedConditions.and(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

        explicitWait.until(ExpectedConditions.or(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

        // Wait for element co attribute chua gia tri mong doi
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""), "placeholder", "example"));

        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""), "placeholder", "example"));

        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("")), "placeholder"));

        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")), "", ""));

        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")), "", ""));

        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), true));
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), false));

        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(""));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt((By.cssSelector(""))));

        // Wait for element bien mat trong khong con tren UI
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        explicitWait.until(ExpectedConditions.jsReturnsValue("return arguments[0].validationMessage;"));


        // Wait for doan code JS duoc thuc thi khong bi ném ra ngoại le nào
        // ko ném ra : true
        // có ngoại lệ false
        Assert.assertTrue(explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("document.documentElement.innerText;")));

        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 10));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""), 10));
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""), 10));

        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));

        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), ""));

        // Bat buoc text nay co trong DOM / HTML
        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(""), ""));

        explicitWait.until(ExpectedConditions.urlToBe(""));
        explicitWait.until(ExpectedConditions.urlContains(""));
        explicitWait.until(ExpectedConditions.urlMatches("[^abc]"));

        // Wait for dieu kien ma Element no se bi update Trang thai - load tai HTML
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.cssSelector(""))));

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