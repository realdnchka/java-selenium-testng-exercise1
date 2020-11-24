package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class EntryListPage {
    WebDriver driver;

    public EntryListPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setCheckbox() {
        driver.findElement(By.xpath("//*[@id=\"result_list\"]/tbody/tr[1]/td[1]/input")).click();
    }

    public void selectDeleteAction() {
        new Select(driver.findElement(By.xpath("//select[@name='action']"))).selectByIndex(1);
    }

    public void submitBtnClick() {
        driver.findElement(By.xpath("//button[@name='index']")).click();
    }
}
