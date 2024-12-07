package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;


@Listeners(listeners.ExtentReport.class)

public class Topic_12_Dependencies {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {

    }

    @Test
    public void TC_01_Create_New_User() {

    }

    @Test(dependsOnMethods = "TC_01_Create_New_User")
    public void TC_02_View_And_Search_User() {

    }

    @Test(dependsOnMethods = "TC_01_Create_New_User")
    public void TC_03_Update_ExistingUser() {
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "TC_03_Update_ExistingUser")
    public void TC_04_Move_ExistingUser_To_OtherRole() {

    }

    @Test(dependsOnMethods = "TC_04_Move_ExistingUser_To_OtherRole")
    public void TC_05_Delete_ExistingUser() {

    }


    @AfterClass
    public void afterClass () {

    }

}
