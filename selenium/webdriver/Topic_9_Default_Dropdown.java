package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_9_Default_Dropdown {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    String firstName ="Auto", lastName ="Testing", password= "123456789", email= getEmailAddress(), date = "20";
    String month = "August", year ="1970", companyName = "WhiteHome";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //version 4
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_Rgister() {
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

        // Dropdown khi nao dùng để THAO TÁC VỚI ELEMENT THÌ MỚI KHỞI TẠO
        Select selectday = new Select(driver.findElement(By.name("DateOfBirthDay")));
        selectday.selectByVisibleText(date);

        // Verify dropdown nay la Single (Khong phai Multiple)
        Assert.assertFalse(selectday.isMultiple());

        // Verify dropdown có phải số lượng là 32 items
        Assert.assertEquals(selectday.getOptions().size(),32);


        new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
        new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);


        driver.findElement(By.cssSelector("#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("#Company")).sendKeys(companyName);
        driver.findElement(By.cssSelector("#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys(password);
        sleepInSecond(2);
        driver.findElement(By.cssSelector("button#register-button")).click(); sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed" );

        driver.findElement(By.cssSelector("a.ico-account")).click();

    }

    @Test
    public void TC_02_Login() {

        Assert.assertEquals(driver.findElement(By.cssSelector("#FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("#LastName")).getAttribute("value"), lastName);

        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), date);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), email);
        Assert.assertEquals(driver.findElement(By.cssSelector("#Company")).getAttribute("value"), companyName);

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
        return "Auto"+ new Random().nextInt(999) + "@vnn.uss";
    }
}