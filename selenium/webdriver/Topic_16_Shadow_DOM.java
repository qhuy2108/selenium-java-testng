package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_16_Shadow_DOM {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //version 4
        driver.manage().window().setSize(new Dimension(1600,900));

    }

    @Test
    public void TC_01_Shadow_DOM() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        WebElement shadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));

        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();

        String someText = shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(someText);
        Assert.assertEquals(someText,"some text");

        WebElement checkboxShadow = shadowRootContext.findElement(By.cssSelector("input[type= 'checkbox']"));
        Assert.assertFalse(checkboxShadow.isSelected());

        List<WebElement> allInput = shadowRootContext.findElements(By.cssSelector("input"));
        System.out.println(allInput.size());

        // get nested shadow
        WebElement nestedShadowHostElement = shadowRootContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowRootContext = nestedShadowHostElement.getShadowRoot();

        String nestedText = nestedShadowRootContext.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        System.out.println(nestedText);
        Assert.assertEquals(nestedText,"nested text");

    }

    @Test
    public void TC_02_Shadow_DOM_Shopee() {
        driver.get("https://shopee.vn/");

        WebElement shadowHostElement = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();

        if (shadowRootContext.findElements(By.cssSelector("div.home-popup__content")).size() >0
                && shadowRootContext.findElements(By.cssSelector("div.home-popup__content")).get(0).isDisplayed()) {
            shadowRootContext.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
        }
        sleepInSecond(2);

        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("iPhone 15 Pro Max");
        driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
        sleepInSecond(2);

    }

    @Test
    public void TC_03_JS() {
        driver.get("https://automationfc.github.io/shadow-dom/");


        WebElement shadowHostElement = driver.findElement(By.xpath("//div[@id='shadow_host']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement shadowContent = (WebElement) js.executeScript(
                "return arguments[0].shadowRoot.querySelector('span#shadow_content > span')",
                shadowHostElement);
        String innerText = shadowContent.getText();
        System.out.println(innerText);
        Assert.assertEquals(innerText,"some text");

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