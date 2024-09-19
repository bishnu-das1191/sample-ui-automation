package demotest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import practice.pom.HomePage;
import practice.pom.LoginPage;

public class LoginPageTest {

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void loginTest(){
        LoginPage login = new LoginPage(driver);
        login.enterURL()
                .fillLoginForm("test.account1@test.com","Welcome@123")
                .clickSignInBtn();
    }

    @Test
    public void addToCart(){
        LoginPage login = new LoginPage(driver);
        HomePage home = new HomePage(driver);
        login.enterURL()
                .fillLoginForm("test.account1@test.com","Welcome@123")
                .clickSignInBtn();
        home.selectItemName();
    }



    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
