package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Relative_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/login");

    }
    @Test
    public void TC_01_Relative() {
        By loginButtonBy =  By.cssSelector("button.login-button");
        WebElement loginButtonElement = driver.findElement(By.cssSelector("button.login-button"));

        // Remember Me Checkbox
        By rememberMeCheckboxBy = By.id("RememberMe");

        // Forgot Password Link
        WebElement forgotPasswordElement = driver.findElement(By.cssSelector("span.forgot-password"));

        // Password Textbox
        By passwordTextboxBy = By.cssSelector("input#Password");


        // GUI (location / position)
        WebElement rememberMeTextElement = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                        .above(loginButtonBy)
                        .toRightOf(rememberMeCheckboxBy)
                        .toLeftOf(forgotPasswordElement)
                        .below(passwordTextboxBy)
                        .near(forgotPasswordElement));

        System.out.println(rememberMeTextElement.getText());
    }

    @Test
    public void TC_02_() {
    }

    @Test
    public void TC_03_() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}