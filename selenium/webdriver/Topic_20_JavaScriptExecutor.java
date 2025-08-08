package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_20_JavaScriptExecutor {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1600, 900));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        jsExecutor = (JavascriptExecutor) driver;

    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSeconds(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    @Test
    public void TC_01_() {
        //executeForBrowser("window.location= 'http://live.techpanda.org'");
        navigateToUrlByJS("http://live.techpanda.org");
        sleepInSeconds(5);

        String techPandaDomain = (String) executeForBrowser("return document.domain;");
        Assert.assertEquals(techPandaDomain, "live.techpanda.org");

        String homePageUrl = (String) executeForBrowser("return document.URL;");
        Assert.assertEquals(homePageUrl, "http://live.techpanda.org/");

        hightlightElement("//a[text()= 'Mobile']");
        clickToElementByJS("//a[text()= 'Mobile']");

        hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        sleepInSeconds(10);
        Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");
        sleepInSeconds(5);

        String customerTitle = (String) executeForBrowser("return document.title;");
        Assert.assertEquals(customerTitle, "Customer Service");

        scrollToElementOnTop("//input[@id='newsletter']");
        hightlightElement("//input[@id='newsletter']");

        sendkeyToElementByJS("//input[@id='newsletter']", getEmailAddress());

        hightlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");
        sleepInSeconds(5);

        Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));

        navigateToUrlByJS("https://www.facebook.com/");
        sleepInSeconds(5);
        String newDomain = (String) executeForBrowser("return document.URL");
        Assert.assertEquals(newDomain, "https://www.facebook.com/");


    }

    @Test
    public void TC_02_() {
        driver.get("https://sieuthimaymocthietbi.com/account/register");
        driver.findElement(By.xpath("//button[text()= 'Đăng ký']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(getElementValidationMessage("//input[@id= 'lastName']"), "Please fill out this field.");

        driver.findElement(By.xpath("//input[@id= 'lastName']")).sendKeys("Automatic_OK");
        driver.findElement(By.xpath("//button[text()= 'Đăng ký']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(getElementValidationMessage("//input[@id= 'firstName']"), "Please fill out this field.");

        driver.findElement(By.xpath("//input[@id= 'firstName']")).sendKeys("Testing");
        driver.findElement(By.xpath("//button[text()= 'Đăng ký']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(getElementValidationMessage("//input[@id= 'email']"), "Please fill out this field.");
        driver.findElement(By.xpath("//input[@id= 'email']")).sendKeys("aa@bb@cc");
        driver.findElement(By.xpath("//button[text()= 'Đăng ký']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(getElementValidationMessage("//input[@id= 'email']"), "Please enter an email address.");


    }

    @Test
    public void TC_03_() {
    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }

    public void sleepInSeconds(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getEmailAddress() {
        return "AutoTest" + new Random().nextInt(999) + "@email.com";
    }
}