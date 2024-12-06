package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_10_Loop_Invocation {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    

    @Test(invocationCount = 3)
    public void TC_01()  {
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

        System.out.println(email+" "+password+"\n");

        driver.findElement(By.cssSelector("a.skip-account")).click();
        driver.findElement(By.cssSelector("a[title= 'Log Out']")).click();

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

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

}
