package selenium.practice;

import manager.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import practice.pom.HomePage;
import practice.pom.LoginPage;

public class DataProviderTest {

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        driver = new ChromeDriver();
        WebDriverManager.setDriver(driver);
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "loginTestData")
    public void loginTest(String email, String password){
        WebDriver driver = WebDriverManager.getDriver();
        LoginPage login = new LoginPage(driver);
        login.enterURL()
                .fillLoginForm(email,password)
                .clickSignInBtn();
    }

    @Test
    public void addToCart(){
        WebDriver driver = WebDriverManager.getDriver();
        LoginPage login = new LoginPage(driver);
        HomePage home = new HomePage(driver);
        login.enterURL()
                .fillLoginForm("test.account1@test.com","Welcome@123")
                .clickSignInBtn();
        home.selectItemName();
    }


    @DataProvider(name = "loginTestData")
    public Object[][] getLoginTestData(){
        return new Object[][] {
            {"test.account1@test.com","Welcome@123"},
            {"test.account2@test.com","Welcome"},
            {"test.account3@test.com","Welcome"},
            {"test.account4@test.com","Welcome"}
        };
    }



    @AfterMethod(alwaysRun = true)
    public void tearDown(){
       WebDriver driver = WebDriverManager.getDriver();
        if(driver != null){
            driver.quit();
            WebDriverManager.setDriver(null);
        }
    }
}
