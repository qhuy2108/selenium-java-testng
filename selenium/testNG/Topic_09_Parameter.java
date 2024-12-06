package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_09_Parameter {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    By emailTextbox = By.xpath("//*[@id='email']");
    By passwordTextbox = By.xpath("//*[@id='pass']");
    By loginButton = By.xpath("//*[@id='send2']");

    @Parameters({"browser", "version"})

    @BeforeClass
    public void beforeClass(String browserName, String browserVersion) {
        driver = getBrowserDriver(browserName);

        System.out.println("Browser " + browserName + " with version " + browserVersion );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    
    private WebDriver getBrowserDriver(String browserName) {
        WebDriver driver;
        if (browserName.equals("firefox")){
            driver = new FirefoxDriver();
        } else if (browserName.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equals("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Browser name is not valid");
        }
        return driver;
    }

    private String getEnviromentUrl (String enviromentName) {
        String urlValue;
        if (enviromentName.equals("live")){
            urlValue = "http://live.techpanda.org/";
        } else if (enviromentName.equals("testing")) {
            urlValue = "http://testing.techpanda.org/";
        } else if (enviromentName.equals("staging")) {
            urlValue = "http://staging.techpanda.org/";
        } else {
            throw new RuntimeException("Enviroment name is not valid");
        }
        return urlValue;
    }

    @Parameters({"enviroment"})
    @Test
    public void TC_01_Login(String enviromentName)  {
        driver.get (getEnviromentUrl(enviromentName)+"index.php/customer/account/login/");

        driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
        driver.findElement(passwordTextbox).sendKeys("111111");
        driver.findElement(loginButton).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));

        // ....

        driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }


    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

}
