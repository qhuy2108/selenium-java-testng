package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_08_Textbox_TextArea {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //version 4
        driver.manage().window().setSize(new Dimension(1600,1080));
    }

    @Test
    public void Login_01_Empty_Email_And_Password() {
        driver.get("http://live.techpanda.org/index.php");

        driver.findElement(By.cssSelector("div.footer a[title=\"My Account\"]")).click();
        sleepInSecond(1);

        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");

    }

    @Test
    public void Login_02_Invalid_Email() {
        driver.get("http://live.techpanda.org/index.php");
        driver.findElement(By.cssSelector("div.footer a[title=\"My Account\"]")).click();
        sleepInSecond(1);

        driver.findElement(By.cssSelector("input#email")).sendKeys("123@456.567");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");

    }

    @Test
    public void Login_03_Invalid_Password() {
        driver.get("http://live.techpanda.org/index.php");
        driver.findElement(By.cssSelector("div.footer a[title=\"My Account\"]")).click();
        sleepInSecond(1);

        driver.findElement(By.cssSelector("input#email")).sendKeys("auto@vnn.uss");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("1234");
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");

    }
    @Test
    public void Login_04_Incorrect_Email_Or_Password() {
        driver.get("http://live.techpanda.org/index.php");
        driver.findElement(By.cssSelector("div.footer a[title=\"My Account\"]")).click();
        sleepInSecond(1);

        driver.findElement(By.cssSelector("input#email")).sendKeys("auto@vnn.uss");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText(), "Invalid login or password.");
    }
    @Test
    public void Login_05_Success() {
        driver.get("http://live.techpanda.org/index.php");
        driver.findElement(By.cssSelector("div.footer a[title=\"My Account\"]")).click();
        driver.findElement(By.cssSelector("a[title= 'Create an Account']")).click();
        sleepInSecond(1);

        String firstName ="Auto", lastName ="Testing", password= "123456789", email= getEmailAddress();

        driver.findElement(By.cssSelector("#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("#email_address")).sendKeys(email);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#confirmation")).sendKeys(password);
        driver.findElement(By.cssSelector("button[title= 'Register']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),"Thank you for registering with Main Website Store.");

        String contactInfo = driver.findElement(By.xpath("//h3[text()= 'Contact Information']//parent::div//following-sibling::div")).getText();
        Assert.assertTrue(contactInfo.contains(firstName));
        Assert.assertTrue(contactInfo.contains(lastName));
        Assert.assertTrue(contactInfo.contains(email));

        driver.findElement(By.cssSelector("a.skip-account")).click();
        driver.findElement(By.cssSelector("a[title= 'Log Out']")).click();

        driver.findElement(By.cssSelector("div.footer a[title=\"My Account\"]")).click(); sleepInSecond(1);
        driver.findElement(By.cssSelector("input#email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(password);
        driver.findElement(By.cssSelector("button#send2")).click();

        contactInfo = driver.findElement(By.xpath("//h3[text()= 'Contact Information']//parent::div//following-sibling::div")).getText();
        Assert.assertTrue(contactInfo.contains(firstName));
        Assert.assertTrue(contactInfo.contains(lastName));
        Assert.assertTrue(contactInfo.contains(email));


        driver.findElement(By.xpath("//a [text()= 'Account Information']")).click();
        sleepInSecond(1);
        Assert.assertEquals(driver.findElement(By.cssSelector("#firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("#lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("#email")).getAttribute("value"), email);



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
        return "AutoTest"+ new Random().nextInt(999) + "@mail.com";
    }



}