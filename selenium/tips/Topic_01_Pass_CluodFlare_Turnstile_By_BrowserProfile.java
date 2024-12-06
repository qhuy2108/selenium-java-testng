package tips;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_01_Pass_CluodFlare_Turnstile_By_BrowserProfile {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

        /*// Brave
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe");
        options.addArguments("--user-data-dir=C:/Users/Qhuy/AppData/Local/BraveSoftware/Brave-Browser/User Data/");
        options.addArguments("--profile-directory=Profile 1");
        driver = new ChromeDriver(options);*/

        /*ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:/Users/Qhuy/AppData/Local/Google/Chrome/User Data/");
        options.addArguments("--profile-directory=Profile 1");
        driver = new ChromeDriver(options);*/

        /*EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=C:/Users/Qhuy/AppData/Local/Microsoft/Edge/User Data/");
        edgeOptions.addArguments("--profile-directory=Profile 1");
        driver = new EdgeDriver(edgeOptions);*/

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--user-data-dir=C:/Users/Qhuy/AppData/Local/Mozilla/Firefox/Profiles/");
        firefoxOptions.addArguments("--profile-directory=f4ly21u7.HuyTest");
        driver = new FirefoxDriver(firefoxOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_NopCommerce() {

        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.className("ico-login")).click();
    }


    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
