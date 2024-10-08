package demo_pom_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import practice.pom.HomePage;
import practice.pom.LoginPage;

public class HomePageTest {

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test
    public void addToCartTest(){
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
