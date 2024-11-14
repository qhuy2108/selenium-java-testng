package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_13_Checkbox_Radio {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //version 4
    }

    public void checkToElement(By byXpath) {
        if (!driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            sleepInSecond(1);
        }
    }

    public void unCheckToElement(By byXpath) {
        if (driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            sleepInSecond(1);
        }
    }

    @Test
    public void TC_01_Default_Checkbox_Telerik() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By dualZoneCheckbox = By.xpath("//label[text()= 'Dual-zone air conditioning']//preceding-sibling::span//input");
        By rearSidecCheckbox = By.xpath("//label[text()= 'Rear side airbags']//preceding-sibling::span//input");

        checkToElement(dualZoneCheckbox);
        checkToElement(rearSidecCheckbox);

        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(rearSidecCheckbox).isSelected());

        unCheckToElement(dualZoneCheckbox);
        unCheckToElement(rearSidecCheckbox);

        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(rearSidecCheckbox).isSelected());


    }

    @Test
    public void TC_02_Default_Radio_Telerik() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        
        By twoPetrolRadio = By.xpath("//label[text()= '2.0 Petrol, 147kW']//preceding-sibling::span//input");
        By twoDieselRadio = By.xpath("//label[text()= '2.0 Diesel, 103kW']//preceding-sibling::span//input");

        checkToElement(twoPetrolRadio);

        Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());

        checkToElement(twoDieselRadio);

        Assert.assertFalse(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertTrue(driver.findElement(twoDieselRadio).isSelected());

    }

    @Test
    public void TC_03_Select_All_Checbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");


        List<WebElement> allCheckbox = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkbox : allCheckbox) {
            if (!checkbox.isSelected()) {
                checkbox.click();
                //sleepInSecond(1);
            }
        }

        //verify
        for (WebElement checkbox : allCheckbox) {
            Assert.assertTrue(checkbox.isSelected());
        }

        // DEselect all
        for (WebElement checkbox : allCheckbox) {
            if (checkbox.isSelected()) {
                checkbox.click();
                //sleepInSecond(1);
            }
        }
        //verify
        for (WebElement checkbox : allCheckbox) {
            Assert.assertFalse(checkbox.isSelected());
        }

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();


        List<WebElement> allCheckboxAndRadio = driver.findElements(By.cssSelector("div.form-single-column input"));

        // Chọn 1 checkbox / radio nào đó trong tat ca checkbox / radio
        for (WebElement allCheckRadio : allCheckboxAndRadio) {
            if (allCheckRadio.getAttribute("Value").equals("Heart Attack") && !allCheckRadio.isSelected()) {
                allCheckRadio.click();
                sleepInSecond(1);
            }
        }

        // Verify all
        for (WebElement allCheckRadio : allCheckboxAndRadio) {
            if (allCheckRadio.getAttribute("value").equals("Heart Attack")) {
                Assert.assertTrue(allCheckRadio.isSelected());
            } else {
                Assert.assertFalse(allCheckRadio.isSelected());
            }
        }
    }

    @Test
    public void TC_04_Custom_Radio() {
        driver.get("");
        ((JavascriptExecutor)driver).executeScript("arguments[0].click()");
    }

    @Test
    public void TC_05_Custom_Google_Docs() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By CanThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");

        // check trước xem la chưa select
        Assert.assertEquals(driver.findElement(CanThoRadio).getAttribute("aria-checked"), "false");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked ='false']")).isDisplayed());

        driver.findElement(CanThoRadio).click();
        sleepInSecond(1);

        Assert.assertEquals(driver.findElement(CanThoRadio).getAttribute("aria-checked"), "true");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked ='true' ]")).isDisplayed());



        List<WebElement> startsWithQuangCheckbox = driver.findElements(By.xpath("//div[@role= 'checkbox' and starts-with(@aria-label,'Quảng')]"));

        for (WebElement checkbox : startsWithQuangCheckbox) {
            if (!checkbox.isSelected()) {
                checkbox.click();
                sleepInSecond(1);
            }
        }

        startsWithQuangCheckbox = driver.findElements(By.xpath
                ("//div[@role= 'checkbox' and starts-with(@aria-label,'Quảng') and @aria-checked ='true']"));

        for (WebElement allCheckbox : startsWithQuangCheckbox) {
            Assert.assertTrue(allCheckbox.isDisplayed());
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