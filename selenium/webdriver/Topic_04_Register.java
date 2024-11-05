package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Register {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/");

    }
    @Test
    public void TC_01_Register() {

        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("autotest@gmail.com");
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        String userName = driver.findElement(By.xpath("//td[text()= 'User ID :']//following-sibling::td")).getText();
        String userPassword = driver.findElement(By.xpath("//td[text()= 'Password :']//following-sibling::td")).getText();

        System.out.println(userName);
        System.out.println(userPassword);
    }

    @Test
    public void TC_02_Login() {

    }

    @Test
    public void TC_03_() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}