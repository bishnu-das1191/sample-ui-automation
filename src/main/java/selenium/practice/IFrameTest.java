package selenium.practice;

import manager.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import practice.pom.HomePage;
import practice.pom.LoginPage;

import java.time.Duration;

public class IFrameTest {

    private WebDriver driver;
    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(String browser){
        System.out.println(browser);
        //System.out.println(env);
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
        driver.get("https://selectorshub.com/iframe-scenario/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(){
        if(driver != null){
            driver.quit();
        }
    }


    @Test
    public void testIframesFeature() throws InterruptedException {
        WebElement iframeEle = driver.findElement(By.id("pact1"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(iframeEle));

        driver.switchTo().frame("pact1");
        driver.findElement(By.id("inp_val")).sendKeys("Welcome to Session");
        driver.switchTo().defaultContent();
        Thread.sleep(2000);

        //switch to iframe using webelement
        driver.switchTo().frame(iframeEle);
        driver.findElement(By.id("inp_val")).clear();
        driver.findElement(By.id("inp_val")).sendKeys("Happy");
        driver.switchTo().defaultContent();

    }

}
