package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AddEntryPage {
    WebDriver driver;

    public AddEntryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkTitle(String expectTitle) {
        String actualTitle = driver.findElement(By.xpath("//*[@id='content']/h1")).getText();

        Assert.assertEquals(actualTitle, expectTitle);
    }

    public void inputText(String text, By by) {
        driver.findElement(by).sendKeys(text);
    }

    public void submitBtn() {
        driver.findElement(By.xpath("//input[@name='_save']")).click();
    }
}
