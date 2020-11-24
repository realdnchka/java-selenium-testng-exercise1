package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {
    WebDriver driver;
    String baseUrl;

    public HomePage(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
    }

    public void checkTitle(String expectTitle) {
        String actualTitle = driver.findElement(By.className("dashboard-title")).getText();

        Assert.assertEquals(actualTitle, expectTitle);
    }

    public void goToPage() {
        driver.get(baseUrl + "admin/");
    }

    public void addEntryBtnClick() {
        driver.findElement(By.xpath("//a[@href='/admin/blog/entry/add/']")).click();
    }

    public void entryListClick() {
        driver.findElement(By.xpath("//*[@id=\"module_2\"]/div/ul[1]/li[1]/a")).click();
    }
}
