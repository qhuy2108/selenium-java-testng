package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_15_Action_P1 {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //version 4
        driver.manage().window().setSize(new Dimension(1600,900));


        action = new Actions(driver);
    }

    @Test
    public void TC_01_Mouse_Hover() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));

        action.moveToElement(ageTextbox).perform();
        sleepInSecond(1);

        Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_P2() throws InterruptedException {
        driver.get("https://www.myntra.com/");
        WebElement kidsMenu = driver.findElement(By.xpath("//a[@class= 'desktop-main' and text() ='Kids']"));

        action.moveToElement(kidsMenu).perform();
        Thread.sleep(3000);

        // action click
        action.click(driver.findElement(By.xpath("//a[@class= 'desktop-categoryName' and text() ='Home & Bath']"))).perform();

        // WebElement click
        // driver.findElement(By.xpath("//a[@class= 'desktop-categoryName' and text() ='Home & Bath']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[@class= 'breadcrumbs-crumb']")).getText(), "Kids Home Bath");

    }

    @Test
    public void TC_03_() throws InterruptedException {
        driver.get("https://www.fahasa.com/");

        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        Thread.sleep(1000);
        action.moveToElement(driver.findElement(By.xpath("//a[@title= 'Sách Trong Nước']"))).perform();
        Thread.sleep(1000);

        // action click
        action.click(driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Kỹ Năng Sống']"))).perform();

        // WebElement click
        // driver.findElement(By.xpath("//a[@class= 'desktop-categoryName' and text() ='Home & Bath']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class= 'breadcrumbs hidden-xs']//li[@class= 'category213']")).getText(), "KỸ NĂNG SỐNG");
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