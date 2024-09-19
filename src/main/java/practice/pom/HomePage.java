package practice.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By itemview = By.xpath("//span[normalize-space()='Nike court vision low']");
    private String homePageURL = "https://demo.evershop.io/";

    //....

    public void scrollToElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void selectItemName(){
        WebElement itemElement = driver.findElement(itemview);
        scrollToElement(itemElement);
        itemElement.click();
    }

    public void prfolifeTest(){
        //.....
    }

}
