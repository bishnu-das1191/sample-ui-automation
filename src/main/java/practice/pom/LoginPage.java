package practice.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    private String loginUrl = "https://demo.evershop.io/account/login";
    private By email = By.xpath("//input[@placeholder='Email']");
    private By password = By.xpath("//input[@placeholder='Password']");
    private By signInBtn = By.xpath("//button[@type='submit']");


    public LoginPage(WebDriver driver){

        this.driver = driver;
    }

    public LoginPage enterURL(){
        driver.get(loginUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return this;
    }

    public LoginPage fillLoginForm(String inputUsername, String inputPassword){
        driver.findElement(email).sendKeys(inputUsername);
        driver.findElement(password).sendKeys(inputPassword);
        return this;
    }

    public void clickSignInBtn(){

        driver.findElement(signInBtn).click();
    }





}
