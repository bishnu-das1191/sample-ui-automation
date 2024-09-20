package selenium.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WindowHandingTest {

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
        driver.get("https://selectorshub.com/xpath-practice-page/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(){
        if(driver != null){
            driver.quit();
        }
    }


    @Test
    public void testWindowHandingFeature() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


        WebElement checkoutHereBtn = driver.findElement(By.xpath("//button[normalize-space()='Checkout here']"));
        wait.until(ExpectedConditions.visibilityOf(checkoutHereBtn));
        System.out.println("Before switch, My Current window URL is : "+driver.getCurrentUrl());

        scrollToElement(checkoutHereBtn);
        checkoutHereBtn.click();
        WebElement testCaseStudioOption = driver.findElement(By.xpath("//a[text()='Try TestCase Studio']"));


        wait.until(ExpectedConditions.visibilityOf(testCaseStudioOption));

        testCaseStudioOption.click();
        //Thread.sleep(3000);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        String parentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        Iterator<String> it = windows.iterator();

        while(it.hasNext()){
            String childWindow = it.next();
            if(!parentWindow.equals(childWindow)){

                driver.switchTo().window(childWindow);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                System.out.println("After Switch, Current Window is : "+driver.getCurrentUrl());

                WebElement featureBlocks = driver.findElement(By.xpath("//a[@class='elementor-icon']/../../../following::div[1]//a"));
                wait.until(ExpectedConditions.visibilityOf(featureBlocks));
                List<WebElement> featuresList = driver.findElements(By.xpath("//a[@class='elementor-icon']/../../../following::div[1]//a"));

                for (WebElement featureName: featuresList) {
                    System.out.println(featureName.getText());
                }
                driver.close();
            }
        }
        //Thread.sleep(3000);
        driver.switchTo().window(parentWindow);
        System.out.println("After switch back, Current Window URL is : "+driver.getCurrentUrl());

    }


    public void scrollToElement(WebElement element) throws InterruptedException {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);
    }

}
