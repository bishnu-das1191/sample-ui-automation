package selenium.practice;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class ActionsTest {
    // @BeforeSuite @BeforeTest @BeforeClass @BeforeMethod @Test @AfterMethod @AfterClass @AfterTest @AfterSuite
    private WebDriver driver;
    @Parameters({"browser","env"})
    @BeforeMethod(alwaysRun = true)
    public void setup(String browser, String env){
        System.out.println(browser);
        System.out.println(env);
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


//@BeforeSuite -> BeforeTest -> BeforeClass - @BeforeMethod - @Test @AfterMethod ....
    @Test
    public void testHoverFeature() throws InterruptedException {
        clickMenu("Hovers");
        Thread.sleep(4000);
        WebElement profile3 = driver.findElement(By.xpath("//div[@class='figure'][3]"));
        WebElement hoverEle3 = driver.findElement(By.xpath("//div[@class='figure'][3]//h5"));
        System.out.println(hoverEle3.isDisplayed());
        Actions act = new Actions(driver);
        act.moveToElement(profile3).perform();
       Thread.sleep(4000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        //Thread.sleep(4000);
        boolean hoverEleDisplayed = driver.findElement(By.xpath("//div[@class='figure'][3]//h5")).isDisplayed();
        System.out.println(hoverEleDisplayed);


    }



    public void clickMenu(String topic){
        String optionPath = "//a[text()='%s']".replaceAll("%s",topic);
        driver.findElement(By.xpath(optionPath)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }




}
