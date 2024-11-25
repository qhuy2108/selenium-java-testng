package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_15_Popup_02 {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;
    Alert alert;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //version 4
        driver.manage().window().setSize(new Dimension(1600,900));

        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Random_In_DOM() {

        driver.get("https://www.kmplayer.com/home"); sleepInSecond(3);

        WebElement layerPopup = driver.findElement(By.cssSelector("div.pop-container"));

        if (layerPopup.isDisplayed()) {
            driver.findElement(By.cssSelector("div.pop-container div.close")).click();
            sleepInSecond(1);
        }
        Assert.assertEquals(driver.getCurrentUrl() , "https://www.kmplayer.com/home");
    }

    @Test
    public void TC_02_Random_NOT_In_DOM() {
        driver.get("https://www.javacodegeeks.com/"); sleepInSecond(10);

        //div[@class='lepopup-popup-container']/div[not(contains(@style, 'display:none'))]
        // css  div.lepopup-popup-container>div:not([style*='display:none'])

        By newsPopup = By.cssSelector("div.lepopup-popup-container>div:not([style*='display:none'])");

        // div.lepopup-popup-container>div:not([style*='display:none']) div.lepopup-element-html-content>a:not([class *=lepopup-inherited])

        if (driver.findElements(newsPopup).size()>0 && driver.findElements(newsPopup).get(0).isDisplayed()) { // > 0 nghia la render ra roi nhung chua biet hien thi chua
            driver.findElement(By.cssSelector
                    ("div.lepopup-popup-container>div:not([style*='display:none']) div.lepopup-element-html-content>a:not([class *=lepopup-inherited])")).click();
            sleepInSecond(2);
        } else {
            System.out.println("Popup Not Displayed");
        }

        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
        driver.findElement(By.cssSelector("button#search-submit")).click();
        sleepInSecond(4);

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).isDisplayed());

    }

    @Test
    public void TC_03_Random_In_DOM_P2() {

        driver.get("https://vnk.edu.vn/"); sleepInSecond(1);

        WebElement layerPopup = driver.findElement(By.cssSelector("div#popmake-39268"));

        if (layerPopup.isDisplayed()) {
            driver.findElement(By.cssSelector("button.pum-close.popmake-close")).click();
            System.out.println("popUp Hien Thi");
            sleepInSecond(1);
        } else {
            System.out.println("popUp Not Displayed");
        }

        driver.findElement(By.cssSelector("button.btn.btn-danger")).click();
        Assert.assertEquals(driver.getCurrentUrl() , "https://vnk.edu.vn/lich-khai-giang/");
    }

    @Test
    public void TC_04_Random_NOT_In_DOM_P2() {
        driver.get("https://dehieu.vn/"); sleepInSecond(2);

        By newsPopup = By.cssSelector("div.modal-content.css-modal-bt");

        if (driver.findElements(newsPopup).size()>0 && driver.findElements(newsPopup).get(0).isDisplayed()) { // > 0 nghia la render ra roi nhung chua biet hien thi chua

            System.out.println("popUp Co Hien Thi");
            int heightBrowser = driver.manage().window().getSize().getHeight();
            System.out.println(heightBrowser);
            if (heightBrowser < 1920) {
                ((JavascriptExecutor)driver).executeScript("arguments[0].click;", driver.findElement(By.cssSelector("button.close")));
            } else {
                driver.findElement(By.cssSelector("button.close")).click();
            }
            sleepInSecond(2);

        } else {
            System.out.println("Popup Not Displayed");
        }

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
