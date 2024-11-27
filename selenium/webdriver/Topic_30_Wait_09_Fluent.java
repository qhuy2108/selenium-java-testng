package webdriver;

import org.openqa.selenium.*;
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
import java.util.function.Function;

public class Topic_30_Wait_09_Fluent {
    WebDriver driver;
    WebDriverWait explicitWait;

    FluentWait<WebDriver> fluentDriver;
    FluentWait<WebElement> fluentElement;
    FluentWait<String> fluentString;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1600, 900));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));

        fluentDriver = new FluentWait<WebDriver>(driver);
        fluentString = new FluentWait<String>("driver");
    }


    public void TC_01_() {

        // SETTING

        fluentDriver.withTimeout(Duration.ofSeconds(15));

        fluentDriver.pollingEvery(Duration.ofMillis(300));

        fluentDriver.ignoring(NoSuchElementException.class);

        fluentDriver.ignoring(TimeoutException.class);

        // DIEU KIEN

        fluentDriver.until(new Function<WebDriver, Object>() {
            @Override
            public Object apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector("")).getText();
            }
        });

        // Viet Nhanh
        fluentDriver.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class, TimeoutException.class)
                .until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return webDriver.findElement(By.cssSelector("")).isDisplayed();
                    }
                });

    }

    @Test
    public void TC_01_Dynamic() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        fluentDriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        
        fluentDriver.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
            }
        });

        String helloText = fluentDriver.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver driver) {
                return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).getText();
            }
        });
        Assert.assertEquals(helloText,"Hello World!");

    }

    @Test
    public void TC_02_Catch_Time() {
        driver.get("https://automationfc.github.io/fluent-wait/");
        WebElement countDownTime = driver.findElement(By.cssSelector("div#javascript_countdown_time"));

        fluentElement = new FluentWait<WebElement>(countDownTime);
        fluentElement.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        
        fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {            // Kiem tra kết thúc là endwith 00 nên trả về Boolean
                System.out.println(webElement.getText());
                return webElement.getText().endsWith("00");
            }
        });
    }

    // Define Ham
    public WebElement  waitAndFindElement(By locator) {
        FluentWait<WebDriver> fluentWaitDriver = new FluentWait<WebDriver>(driver);
        fluentWaitDriver.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        return fluentWaitDriver.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }

    @Test
    public void TC_01_Dynamic_Define() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        waitAndFindElement(By.cssSelector("div#start>button")).click();

        String helloText = waitAndFindElement(By.xpath("//div[@id='finish']/h4")).getText();

        Assert.assertEquals(helloText,"Hello World!");
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