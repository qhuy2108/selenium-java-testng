package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_05_Assert {
    WebDriver driver;

    @Test
    public void VerifyTestNG() {
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");

        Assert.assertTrue(driver.getPageSource().contains("Facebook helps you connect and share with the people in your life."));

    }
}
