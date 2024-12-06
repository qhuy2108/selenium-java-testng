package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Assert {

    WebDriver driver;

    @Test
    public void TC_01() {
        Assert.assertTrue(isElementDisplayed(By.cssSelector("")));
        Assert.assertTrue(isElementDisplayed(By.cssSelector("")),"Element is not displayed");

        String fullName = "Automation FC";
        Assert.assertEquals(fullName,"Automation FCC","Actual is not the same");

    }

    private boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

}
