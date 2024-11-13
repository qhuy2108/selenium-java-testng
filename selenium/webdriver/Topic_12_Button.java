package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_12_Button {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //version 4
    }

    @Test
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");

        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));
        Assert.assertFalse(registerButton.isEnabled());

        driver.findElement(By.cssSelector("input#chinhSach")).click();
        sleepInSecond(1);

        Assert.assertTrue(registerButton.isEnabled());

        String registerButtonBackgroundColorRGB = registerButton.getCssValue("background-color");
        System.out.println("Button Background Color RGB : " + registerButtonBackgroundColorRGB);

        String registerButtonBackgroundColorHexa = Color.fromString(registerButtonBackgroundColorRGB).asHex();
        System.out.println("Button Background Color Hexa : " + registerButtonBackgroundColorHexa);

        Assert.assertEquals(registerButtonBackgroundColorHexa, "#ef5a00");
    }

    @Test
    public void TC_02_Fahasa() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-item.popup-login-tab-login")).click();

        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));

        Assert.assertFalse(loginButton.isEnabled());

        String loginButtonBgColorRGB = loginButton.getCssValue("background-color");
        System.out.println("RGB : " + loginButtonBgColorRGB);

        String loginButtonBgColorHexa = Color.fromString(loginButtonBgColorRGB).asHex();
        System.out.println("HEXA : " + loginButtonBgColorHexa);

        Assert.assertEquals(loginButtonBgColorHexa, "#000000");

        driver.findElement(By.id("login_username")).sendKeys(getEmailAddress());
        driver.findElement(By.id("login_password")).sendKeys("123456");
        sleepInSecond(2);
        Assert.assertTrue(loginButton.isEnabled());

        loginButtonBgColorRGB = loginButton.getCssValue("background-color");
        loginButtonBgColorHexa = Color.fromString(loginButtonBgColorRGB).asHex();

        System.out.println(loginButtonBgColorHexa);

        Assert.assertEquals(loginButtonBgColorHexa.toUpperCase(), "#C92127");

    }

    @Test
    public void TC_03_() {
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

    public String getEmailAddress () {
        return "AutoTest"+ new Random().nextInt(999) + "@vnn.uss";
    }
}