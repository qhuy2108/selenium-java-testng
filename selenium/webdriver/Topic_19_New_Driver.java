package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

public class Topic_19_New_Driver {
    WebDriver userDriver;
    WebDriver adminDriver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");


    @BeforeClass
    public void beforeClass() {
        userDriver = new FirefoxDriver();
        userDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //version 4
        userDriver.manage().window().setSize(new Dimension(1600, 900));

        adminDriver = new ChromeDriver();
        adminDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //version 4
        adminDriver.manage().window().setSize(new Dimension(1600, 900));

    }

    @Test
    public void TC_01() {
        userDriver.get("https://demo.nopcommerce.com/");

        System.out.println(userDriver.getCurrentUrl());
        System.out.println(userDriver.getTitle());

        userDriver.findElement(By.cssSelector("input.search-box-text")).sendKeys("OK");
        sleepInSecond(3);


        adminDriver.get("https://admin-demo.nopcommerce.com/");

        System.out.println(adminDriver.getCurrentUrl());
        System.out.println(adminDriver.getTitle());

        adminDriver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSecond(3);


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
