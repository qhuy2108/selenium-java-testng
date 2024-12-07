package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_11_Parallel_Method {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_() {
        System.out.println("Run Test 01");
    }

    @Test
    public void TC_02_() {
        System.out.println("Run Test 02");
    }

    @Test
    public void TC_03_() {
        System.out.println("Run Test 03");
    }

    @Test
    public void TC_04_() {
        System.out.println("Run Test 04");
    }

    @Test
    public void TC_05_() {
        System.out.println("Run Test 05");
    }

    @AfterMethod
    public void afterMethod () {
    }

}
