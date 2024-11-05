package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");
    }
    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("FirstName")).sendKeys("Myname");
    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-logo"));
    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("DateOfBirthDay"));
    }
    @Test
    public void TC_04_Tagname() {
        driver.findElement(By.tagName("input"));
    }

    @Test
    public void TC_05_LinkText() {
        // Độ chính xác cao = lấy tuyệt đối
        driver.findElement(By.linkText("Shipping & returns"));
    }

    @Test
    public void TC_06_Partial_linktext() {
        // Độ chính xác thấp = lấy tương đối
        driver.findElement(By.partialLinkText("vendor account"));
    }
    @Test
    public void TC_07_Css() {
        // Css va ID
        driver.findElement(By.cssSelector("input[id= 'FirstName']"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));

        // Css va Class
        driver.findElement(By.cssSelector("div[class= 'page-title']"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));

        // Css va Name
        driver.findElement(By.cssSelector("input[name ='FirstName']"));

        // Css va tagname
        driver.findElement(By.cssSelector("input"));

        // Css va Link
        driver.findElement(By.cssSelector("a[href ='/customer/addresses']"));

        // Css va partial link
        driver.findElement(By.cssSelector("a[href *='/customer/addresses']")); // Có chứa
        // driver.findElement(By.cssSelector("a[href ^=''/customer/addresses']")); // Đầu
        // driver.findElement(By.cssSelector("a[href $=''/customer/addresses']")); // Đuôi

    }

    @Test
    public void TC_08_Xpath() {
        // Css va ID
        driver.findElement(By.xpath("//input[@id= 'FirstName']"));

        // Css va Class
        driver.findElement(By.xpath("//div[@class= 'page-title']"));

        // Css va Name
        driver.findElement(By.xpath("//input[@name ='FirstName']"));

        // Css va tagname
        driver.findElement(By.xpath("//input"));

        // Css va Link
        driver.findElement(By.xpath("//a[@href ='/customer/addresses']"));
        driver.findElement(By.xpath("//a[text() ='Addresses']"));

        // Css va partial link
        driver.findElement(By.xpath("//a[contains(@href , 'addresses')]"));
        driver.findElement(By.xpath("//a[contains(text() , 'Addresses')]"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}