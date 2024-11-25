package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_10_Custom_Dropdown {
    WebDriver driver;


    WebDriverWait explicitWait;
    FluentWait<WebDriver> FluentWait;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30)); // chờ cho 1 điều kiện nào đó

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // chờ cho element dc tìm thấy
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_jqueryui() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Faster");

        selectItemInCustomDropdown("span#files-button", "ul#files-menu div", "ui.jQuery.js");

        selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "9");

        selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Dr.");

        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(), "ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "9");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Dr.");
    }

    @Test
    public void TC_02_ReactJS() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemInCustomDropdown("div.ui.fluid.selection.dropdown", "span.text", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");

        selectItemInCustomDropdown("div.ui.fluid.selection.dropdown", "span.text", "Elliot Fu");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
    }

    @Test
    public void TC_03_VueDropdowns() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li", "Third Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
    }

    @Test
    public void TC_04_Editable_Dropdown() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectItemInEditableDropdown("input.search", "div.item>span.text", "Bang", "Bangladesh");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Bangladesh");
        sleepInSecond(2);

        selectItemInEditableDropdown("input.search", "div.item>span.text", "A", "Aruba");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Aruba");
        sleepInSecond(2);

        selectItemInEditableDropdown("input.search", "div.item>span.text", "A", "Argentina");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Argentina");
        sleepInSecond(2);
    }

    @Test
    public void TC_05_Nopcommerce() {
        driver.get("https://demo.nopcommerce.com/register");
        selectItemInCustomDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option", "18");
        Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']/option[@value='18']")).isSelected());
        sleepInSecond(2);
        selectItemInCustomDropdown("select[name='DateOfBirthMonth']", "select[name='DateOfBirthMonth']>option", "July");
        Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']/option[@value='7']")).isSelected());
        sleepInSecond(2);
        selectItemInCustomDropdown("select[name='DateOfBirthYear']", "select[name='DateOfBirthYear']>option", "1998");
        Assert.assertTrue(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']/option[@value='1998']")).isSelected());
        sleepInSecond(2);
    }


    public void selectItemInCustomDropdown(String parentCss, String childItemCss, String itemTextExpected ) {

        driver.findElement(By.cssSelector(parentCss)).click();
        sleepInSecond(1);

        List<WebElement> allDropDownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.cssSelector(childItemCss)));

        for (WebElement item : allDropDownItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }

    public void selectItemInEditableDropdown(String parentCss, String childItemCss, String SearchitemText, String itemTextExpected ) {

        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(SearchitemText);
        sleepInSecond(1);

        List<WebElement> allDropDownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.cssSelector(childItemCss)));

        for (WebElement item : allDropDownItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
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