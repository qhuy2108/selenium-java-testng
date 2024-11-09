package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_Scope {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //version 4
        driver.manage().window().maximize();
        driver.get("");
    }
    @Test
    public void TC_01_() {
    }

    @Test
    public void TC_02_() {
    }

    @Test
    public void TC_03_() {
    }

    @Test
    public void TC_04_() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
