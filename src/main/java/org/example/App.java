package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        String loginPageTitle = driver.getTitle();
        Assert.assertEquals(loginPageTitle, "Swag Labs");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();


        //explicit wait
        WebElement productPageText = driver.findElement(By.xpath("//span[@class='titlee']"));
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.visibilityOf(productPageText));


        //fluent wait

        //Declare and initialise a fluent wait
        FluentWait wait = new FluentWait(driver);
        //Specify the timout of the wait
        wait.withTimeout(Duration.ofSeconds(20));
        //Sepcify polling time
        wait.pollingEvery(Duration.ofMillis(250));
        //Specify what exceptions to ignore
        wait.ignoring(NoSuchElementException.class);

        //This is how we specify the condition to wait on.
        //This is what we will explore more in this chapter
        wait.until(ExpectedConditions.visibilityOf(productPageText));


        //thread.sleep
        Thread.sleep(2000);



        //htmltag[@attribute='value'] -> element


        //input[@id='value']  -> valid
        //*[@id="dummy"] -> valid
        //  //div[@class='inventory_item_name ']
        //  (//div[@class='inventory_item_name '])[1] -> first element from list using index based.
        //   //div[text()='Sauce Labs Backpack'] -> identify using visible text
        //   (//div[@class='inventory_item_name '])[last()]  -> get the last element from list.
        //    //div[contains(text(),'T-Shirt')]
        //    //*[contains(@id,'img_link')]
        //    //input[@name='login-button' and @id='login-button']
        //    //input[@name='login-button' or @id='login-button']
        //    //div[@id='root']//input  -> decendent concept
        // //input[@name='login-button']/..   -> child to parent
        //  //input[@name='login-button']/parent::form  - child to parent
        //  //input[@name='login-button']/preceding::div[1]   - to find the preceding element
        //  //input[@name='login-button']/../div[1]/following::div[1]


        driver.quit();

    }
}
