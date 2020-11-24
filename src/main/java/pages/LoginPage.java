package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void putUserName(String userName) {
        driver.findElement(By.id("id_username")).sendKeys(userName);
    }

    public void putPassword(String userPass) {
        driver.findElement(By.id("id_password")).sendKeys(userPass);
    }

    public void submitBtn() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }
}
