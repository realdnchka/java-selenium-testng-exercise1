import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class selenium {
    public WebDriver driver;
    public String baseUrl = "https://igorakintev.ru/";
    Random rand = new Random();
    long randomNumber = rand.nextLong();

    @BeforeTest
    @Parameters("browser")
    public void launchBrowser(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get(baseUrl + "admin/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(randomNumber);
    }

    @Test(priority = 1)
    public void login() {
        String userName = "selenium";
        String password = "super_password";

        WebElement emailField = driver.findElement(By.id("id_username"));
        WebElement passField = driver.findElement(By.id("id_password"));
        emailField.sendKeys(userName);
        passField.sendKeys(password);

        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        submitButton.click();
    }

    @Test(priority = 2)
    public void verifyHeader() {
        String expectedTitle = "Панель управления";
        String actualTitle = driver.findElement(By.className("dashboard-title")).getText();

        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test(priority = 3)
    public void addEntry() {
        WebElement addEntry = driver.findElement(By.xpath("//a[@href='/admin/blog/entry/add/']"));
        addEntry.click();

        String expectedTitle = "Добавить entry";
        String actualTitle = driver.findElement(By.xpath("//*[@id='content']/h1")).getText();
        Assert.assertEquals(actualTitle, expectedTitle);

        WebElement titleInput = driver.findElement(By.id("id_title"));
        WebElement slugInput = driver.findElement(By.id("id_slug"));
        WebElement textMDInput = driver.findElement(By.id("id_text_markdown"));
        WebElement textInput = driver.findElement(By.id("id_text"));

        String titleText = "Title" + randomNumber;
        String slugText = "Slug" + randomNumber;

        titleInput.sendKeys(titleText);
        slugInput.sendKeys(slugText);
        textMDInput.sendKeys(slugText);
        textInput.sendKeys(slugText);

        WebElement saveButton = driver.findElement(By.xpath("//input[@name='_save']"));
        saveButton.click();
    }

    @Test(priority = 4)
    public void checkPost() {
        driver.get(baseUrl + "blog/");

        String expectedTitle = "Title" + randomNumber;
        String actualTitle = driver.findElement(By.cssSelector("#entries > h2:nth-child(1) > a")).getText();

        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test(priority = 5)
    public void deletePost() {
        driver.get(baseUrl + "admin/");

        WebElement editEntry = driver.findElement(By.xpath("//*[@id=\"module_2\"]/div/ul[1]/li[1]/a"));
        editEntry.click();

        WebElement checkBox = driver.findElement(By.xpath("//*[@id=\"result_list\"]/tbody/tr[1]/td[1]/input"));
        checkBox.click();

        Select drpActions = new Select (driver.findElement(By.xpath("//select[@name='action']")));
        drpActions.selectByIndex(1);

        WebElement submitButton = driver.findElement(By.xpath("//button[@name='index']"));
        submitButton.click();

        WebElement confirmButton = driver.findElement(By.xpath("//input[@type='submit']"));
        confirmButton.click();
    }

    @AfterTest
    public void browserClose() {
        driver.quit();
    }
}
