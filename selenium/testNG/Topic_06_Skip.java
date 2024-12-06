package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_Skip {

    WebDriver driver;

    @BeforeClass ()
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        System.out.println("Run Before Class");
    }

    @Test
    public void Priority_01_() {
    }


    public void Priority_02_() {
    }

    @Test
    public void Priority_03_() {
    }

    @AfterClass ()
    public void afterClass () {
        // driver.quit();
    }

}
