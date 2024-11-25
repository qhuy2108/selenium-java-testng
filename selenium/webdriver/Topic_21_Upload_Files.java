package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_21_Upload_Files {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    String sgName = "saigon.jpg";
    String hueName = "hue.jpg";
    String hnName = "hanoi.jpg";

    String uploadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;

    String sgFilePath =   uploadFilePath + sgName;
    String hueFilePath =  uploadFilePath + hueName;
    String hnFilePath =   uploadFilePath + hnName;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().setSize(new Dimension(1600,900));

    }

    @Test
    public void TC_01_Single_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By upLoadFileBy = (By.cssSelector("input[type='file']"));

        driver.findElement(upLoadFileBy).sendKeys(sgFilePath);
        sleepInSecond(2);
        driver.findElement(upLoadFileBy).sendKeys(hueFilePath);
        sleepInSecond(2);
        driver.findElement(upLoadFileBy).sendKeys(hnFilePath);
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" +sgName+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" +hueName+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" +hnName+ "']")).isDisplayed());

        List<WebElement> starButtons = driver.findElements(By.cssSelector("td>button.start"));

        for (WebElement starbtn : starButtons) {
            starbtn.click();
            sleepInSecond(2);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text() = '" +sgName+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text() = '" +hueName+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text() = '" +hnName+ "']")).isDisplayed());

    }

    @Test
    public void TC_02_Multiple_Flies() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By upLoadFileBy = (By.cssSelector("input[type='file']"));

        driver.findElement(upLoadFileBy).sendKeys(sgFilePath + "\n" + hueFilePath + "\n" + hnFilePath);
        sleepInSecond(3);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" +sgName+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" +hueName+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '" +hnName+ "']")).isDisplayed());

        List<WebElement> starButtons = driver.findElements(By.cssSelector("td>button.start"));

        for (WebElement starbtn : starButtons) {
            starbtn.click();
            sleepInSecond(2);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text() = '" +sgName+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text() = '" +hueName+ "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text() = '" +hnName+ "']")).isDisplayed());
    }

    @Test
    public void TC_03_() {
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
        return "AutoTest"+ new Random().nextInt(999) + "@@email.com";
    }
}