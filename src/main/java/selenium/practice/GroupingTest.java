package selenium.practice;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class GroupingTest {
    // @BeforeSuite @BeforeTest @BeforeClass @BeforeMethod @Test @AfterMethod @AfterClass @AfterTest @AfterSuite
    private WebDriver driver;
    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(String browser){
        System.out.println(browser);
        switch (browser){
            case "chrome" :
                driver = new ChromeDriver();
                break;
            case "firefox" :
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Incorrect Browser name !!");
        }

        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(){
        if(driver != null){
            driver.quit();
        }
    }

    @Test(groups = {"smoke"})
    public void myAlertTest() throws InterruptedException {
        clickMenu("JavaScript Alerts");
        driver.findElement(By.xpath("//li[3]/button")).click();
        Alert alert = driver.switchTo().alert();

        String userInput = "welcome to todays session";
        alert.sendKeys(userInput);
        Thread.sleep(2000);
        alert.accept();
        String actualText = driver.findElement(By.xpath("//p[@id='result']")).getText();
        String expectedText = "You entered: "+userInput;
        System.out.println(actualText);
        Assert.assertEquals(actualText,expectedText);
    }

    @Test
    public void testAuthentication() throws InterruptedException {
        clickMenu("Basic Auth");
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        String text = driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credenti')]")).getText();
        Thread.sleep(2000);
        Assert.assertEquals(text,"Congratulations! You must have the proper credentials.");
    }

    @Test(groups = {"regression", "smoke"})
    public void testCheckboxes() throws InterruptedException {
       clickMenu("Checkboxes");
       WebElement checkbox1 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]"));
       boolean is1stBoxSelected = checkbox1.isSelected();
       Assert.assertFalse(is1stBoxSelected);
       Thread.sleep(500);
       checkbox1.click();
       Thread.sleep(500);

        boolean is2ndBoxSelected = driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]")).isSelected();
        Assert.assertTrue(is2ndBoxSelected);
    }

    @Test(groups = {"regression"})
    public void testDropdown() throws InterruptedException {
        clickMenu("Dropdown");
        WebElement drop = driver.findElement(By.id("dropdown"));
        Select drp = new Select(drop);
        drp.selectByVisibleText("Option 2");
        Thread.sleep(2000);
    }



    public void clickMenu(String topic){
        String optionPath = "//a[text()='%s']".replaceAll("%s",topic);
        driver.findElement(By.xpath(optionPath)).click();
    }




}
