package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmPage {
    WebDriver driver;

    public ConfirmPage(WebDriver driver) {
        this.driver = driver;
    }

    public void confirmBtnClick() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }
}
