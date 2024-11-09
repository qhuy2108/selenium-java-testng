package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {
    WebDriver driver;
    WebElement element;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("");
    }

    @Test
    public void TC_01_Run_On_Firefox() {

        // Selenium 4 moi co
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();

        driver.manage().window().setSize(new Dimension(1600, 900));
        driver.manage().window().getSize();
        driver.manage().window().setPosition(new Point(0,0));

        driver.navigate().to("");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        driver.switchTo().alert().accept();

        String homePageWindowID = driver.getWindowHandle();
        driver.switchTo().window(homePageWindowID);

        Set<String> allWindowIDs = driver.getWindowHandles(); // co S : so nhieu

        driver.switchTo().frame(0);
        driver.switchTo().frame("0");
        driver.switchTo().frame(driver.findElement(By.id("")));


    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}