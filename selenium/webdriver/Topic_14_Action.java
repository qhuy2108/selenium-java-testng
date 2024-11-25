package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Topic_14_Action {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //version 4
        driver.manage().window().setSize(new Dimension(1600,900));


        action = new Actions(driver);
    }

    @Test
    public void TC_01_Mouse_Hover() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));

        action.moveToElement(ageTextbox).perform();
        sleepInSecond(1);

        Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_P2() throws InterruptedException {
        driver.get("https://www.myntra.com/");
        WebElement kidsMenu = driver.findElement(By.xpath("//a[@class= 'desktop-main' and text() ='Kids']"));

        action.moveToElement(kidsMenu).perform();
        Thread.sleep(3000);

        // action click
        action.click(driver.findElement(By.xpath("//a[@class= 'desktop-categoryName' and text() ='Home & Bath']"))).perform();

        // WebElement click
        // driver.findElement(By.xpath("//a[@class= 'desktop-categoryName' and text() ='Home & Bath']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[@class= 'breadcrumbs-crumb']")).getText(), "Kids Home Bath");

    }

    @Test
    public void TC_03_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");

        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        Thread.sleep(1000);
        action.moveToElement(driver.findElement(By.xpath("//a[@title= 'Sách Trong Nước']"))).perform();
        Thread.sleep(1000);

        // action click
        action.click(driver.findElement(By.xpath
                ("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Kỹ Năng Sống']"))).perform();

        // WebElement click
        // driver.findElement(By.xpath("//a[@class= 'desktop-categoryName' and text() ='Home & Bath']")).click();

        Assert.assertEquals(driver.findElement(By.xpath
                ("//div[@class= 'breadcrumbs hidden-xs']//li[@class= 'category213']")).getText(), "KỸ NĂNG SỐNG");
    }

    @Test
    public void TC_04_Click_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(),20);


        action.clickAndHold(allNumbers.get(0))      // Click va giu chuot tai so thu 1

                .pause(2000)

                .moveToElement(allNumbers.get(14))  // Hover chuot den so 11

                .release()                          // Nha chuot

                .perform();                         // Thuc thi cac action tren


        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allNumbersSelected.size(), 12);

        String[] allNumbersTextExpectArray = {"1","2", "3", "5", "6", "7", "9", "10", "11", "13", "14", "15"};


        List<String> allNumbersTextActual = new ArrayList<String>();

        for (WebElement element : allNumbersSelected){
            allNumbersTextActual.add(element.getText());
        }

        // CONVERT TU ARRAYS QUA LIST
        List<String> allNumbersTextExpect = Arrays.asList(allNumbersTextExpectArray);

        Assert.assertEquals(allNumbersTextExpect, allNumbersTextActual);

    }
    @Test
    public void TC_05_Click_Hold_Control() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        String osName = System.getProperty("os.Name");
        Keys keys;
        if (osName.startsWith("Windows")) {
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }

        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(), 20);
        action.clickAndHold(allNumbers.get(0))
                .pause(2000)
                .moveToElement(allNumbers.get(11))
                .release()
                .perform();

        action.keyDown(keys).perform();
        action.click(allNumbers.get(12))
                .click(allNumbers.get(13))
                .click(allNumbers.get(14)).perform();
        // Nha Phim control ra
        action.keyUp(keys).perform();

        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allNumbersSelected.size(), 15);
    }

    @Test
    public void TC_06_Double_Click() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement doubleClickButton = driver.findElement(By.xpath("//button[@ondblclick= 'doubleClickMe()']"));

        if(driver.toString().contains("firefox")){
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true)", doubleClickButton);
            Thread.sleep(2000);
            // scrollIntoView(true) : KEO ELEMENT LEN TREN CUNG
            // scrollIntoView(false) : KEO ELEMENT XUONG DUOI CUNG
        }

        action.doubleClick(doubleClickButton).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_07_Right_Click() {
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");

        action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one.btn.btn-neutral"))).perform();
        sleepInSecond(1);

        WebElement deleteBefore = driver.findElement(By.cssSelector("li.context-menu-icon-paste"));
        action.moveToElement(deleteBefore).perform();
        sleepInSecond(1);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-hover.context-menu-visible")).isDisplayed());
        action.click(deleteBefore).perform(); sleepInSecond(1);

        driver.switchTo().alert().accept();
        sleepInSecond(1);
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
    }

    @Test
    public void TC_08_DragDrop_HTLM4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement dragg = driver.findElement(By.cssSelector("div#draggable"));
        WebElement dropp = driver.findElement(By.cssSelector("div#droptarget"));

        action.dragAndDrop(dragg, dropp).perform(); sleepInSecond(1);

        Assert.assertEquals(dropp.getText(), "You did great!");

        String BgColor = Color.fromString(dropp.getCssValue("background-color")).asHex().toUpperCase();
        Assert.assertEquals(BgColor, "#03A9F4");


    }

    @Test
    public void TC_09_DragDrop_HTLM5_JQuery_Css() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        String columnA = "div#column-a";
        String columnB = "div#column-b";

        String projectPath = System.getProperty("user.dir");
        String dragAndDropFilePath = projectPath + "/dragAndDrop/drag_and_drop_helper.js";
        String jsContentFile = getContentFile(dragAndDropFilePath); // Lay File

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsContentFile = jsContentFile + "$('" + columnA + "').simulateDragDrop({ dropTarget: '" + columnB + "'});";

        jsExecutor.executeScript(jsContentFile);
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.cssSelector(columnA)).getText(), "B");
        sleepInSecond(1);

        jsExecutor.executeScript(jsContentFile);
        Assert.assertEquals(driver.findElement(By.cssSelector(columnA)).getText(), "A");
    }

    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    @Test
    public void TC_10_DragDrop_HTLM5_Xpath() throws AWTException {
        //  CHAY TREN FIREFOX BI LOI (DUNG CHROME)
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']"); sleepInSecond(1);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a")).getText(), "B");

        dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']"); sleepInSecond(1);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a")).getText(), "A");
    }



    @AfterClass
    public void afterClass() {
        // driver.quit();
    }




    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
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