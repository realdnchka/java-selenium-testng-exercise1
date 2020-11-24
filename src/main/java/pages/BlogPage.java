package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BlogPage {
    WebDriver driver;
    String baseUrl;

    public BlogPage(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl + "blog/";
    }

    public void goToPage() {
        driver.get(baseUrl);
    }

    public void checkTitle(String expectedTitle) {
        String actualTitle = driver.findElement(By.cssSelector("#entries > h2:nth-child(1) > a")).getText();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
}
