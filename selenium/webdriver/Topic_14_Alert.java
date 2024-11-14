package webdriver;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.v130.network.model.Headers;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class Topic_14_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectLocation = System.getProperty("user.dir");
    String username = "admin";
    String pass = "admin";

    By resultText = By.cssSelector("p#result");

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //version 4
        driver.manage().window().setSize(new Dimension(1600,900));
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        sleepInSecond(1);

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        alert.accept();
        sleepInSecond(1);

        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");
    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        sleepInSecond(1);

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        // Click cancel
        alert.dismiss();
        sleepInSecond(1);
        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");
    }

    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInSecond(1);

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        String text ="Automation Testing FC OK";
        alert.sendKeys(text);
        sleepInSecond(1);
        alert.accept();
        Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + text);
    }

    @Test
    public void TC_04_Authentication_Pass_To_Url() {

        String url = "https://" + username + ":" + pass + "@" + "the-internet.herokuapp.com/basic_auth";
        driver.get(url);
        sleepInSecond(3);

        String contenttext = driver.findElement(By.xpath("//div[@id = 'content']//p")).getText();
        Assert.assertTrue(contenttext.contains("Congratulations! You must have the proper credentials."));

    }

    @Test
    public void TC_04_Authentication_Pass_To_Url_Method_2() {


        driver.get("https://the-internet.herokuapp.com/");

        String urlAuthenLink = driver.findElement(By.xpath("//a[text()= 'Basic Auth']")).getAttribute("href");
        System.out.println(urlAuthenLink);


        String[] authenArray = urlAuthenLink.split("//");
        System.out.println(authenArray[0]);
        System.out.println(authenArray[1]);

        driver.get(authenArray[0]+"//" + username + ":" + pass + "@" +authenArray[1]);


        //String contenttext = driver.findElement(By.cssSelector("div#content p")).getText();
        //Assert.assertTrue(contenttext.contains("Congratulations! You must have the proper credentials."));

    }

    // Co the viet thanh 1 ham de dung tach chuoi ra
    public String getAuthenAlertByUrl(String urlAuthenLink, String username, String password) {
        String[] authenArray = urlAuthenLink.split("//");
        return authenArray[0]+"//" + username + ":" + password + "@" +authenArray[1];
    }


    @Test
    public void TC_05_Authentication_AutoIT() throws IOException {

        driver.get("https://the-internet.herokuapp.com/basic_auth");
        sleepInSecond(5);
        Runtime.getRuntime().exec(new String[] {projectLocation+"\\AutoIT\\authen_firefox.exe","admin" ,"admin" });

        Assert.assertTrue(driver.findElement(By.cssSelector("div#content p")).getText().contains("Congratulations! You must have the proper credentials."));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]"))
                .isDisplayed()); // cach 2

    }

    @Test
    public void TC_06_Authentication_Selenium4x_Chromium() {
        // Dùng Chrome Devtool Protocol (CDP)


        /*ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
        driver = new ChromeDriver(options);*/

        // Get DevTool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode username/ password
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", username, pass).getBytes()));
        headers.put("Authorization", basicAuthen);

        // Set to Header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("https://the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]"))
                .isDisplayed());

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