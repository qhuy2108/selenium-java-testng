package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_07_WebElement_Commands {
    WebDriver driver;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //version 4
        driver.manage().window().maximize();
        driver.get("");
    }
    @Test
    public void TC_01_Element() {

        // Tìm và lưu nó vào biến WebElement (chưa tương tác)
        WebElement fullNameTextbox = driver.findElement(By.id(""));

        driver.findElement(By.id("")).getAttribute("");

        driver.findElement(By.id("")).getAccessibleName();          // TAB ACCESSIBILITY
        driver.findElement(By.id("")).getDomAttribute("");
        driver.findElement(By.id("")).getDomProperty("");   // TAB PROPERTIES

        driver.findElement(By.id("")).getCssValue(""); // TAB STYLÉ


        Point nameTextboxLocation = driver.findElement(By.id("")).getLocation();        // vi tri
        Rectangle nameTextboxRect = driver.findElement(By.id("")).getRect();       // lay vi tri va kich thuoc

        Point namePoint = nameTextboxRect.getPoint();
        Dimension nameDimension = nameTextboxRect.getDimension();

        driver.findElement(By.id("")).getShadowRoot();

        driver.findElement(By.id("")).getTagName();
        




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