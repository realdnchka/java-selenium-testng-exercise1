package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestCase {
    public WebDriver driver;
    public String baseUrl = "https://igorakintev.ru/";

    LoginPage loginPage;
    HomePage homePage;
    AddEntryPage addEntryPage;
    BlogPage blogPage;
    EntryListPage entryListPage;
    ConfirmPage confirmPage;

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
    public void logIn() {
        loginPage = new LoginPage(driver);
        loginPage.putUserName("selenium");
        loginPage.putPassword("super_password");
        loginPage.submitBtn();
    }

    @Test(priority = 2)
    public void checkTitle() {
        homePage = new HomePage(driver, baseUrl);
        homePage.checkTitle("Панель управления");
    }

    @Test(priority = 3)
    public void addEntry() {
        homePage = new HomePage(driver, baseUrl);

        homePage.addEntryBtnClick();

        addEntryPage = new AddEntryPage(driver);

        addEntryPage.checkTitle("Добавить entry");
        addEntryPage.inputText("Title" + randomNumber, By.id("id_title"));
        addEntryPage.inputText("Slug" + randomNumber, By.id("id_slug"));
        addEntryPage.inputText("Slug" + randomNumber, By.id("id_text_markdown"));
        addEntryPage.inputText("Slug" + randomNumber, By.id("id_text"));

        addEntryPage.submitBtn();
    }

    @Test(priority = 4)
    public void checkPost() {
        blogPage = new BlogPage(driver, baseUrl);
        blogPage.goToPage();
        blogPage.checkTitle("Title" + randomNumber);
    }

    @Test(priority = 5)
    public void deletePost() {
        homePage = new HomePage(driver, baseUrl);
        entryListPage = new EntryListPage(driver);
        confirmPage = new ConfirmPage(driver);

        homePage.goToPage();
        homePage.entryListClick();

        entryListPage.setCheckbox();
        entryListPage.selectDeleteAction();
        entryListPage.submitBtnClick();

        confirmPage.confirmBtnClick();
    }

    @AfterTest
    public void browserClose() {
        driver.quit();
    }
}
