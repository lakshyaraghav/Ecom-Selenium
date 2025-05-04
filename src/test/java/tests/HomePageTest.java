package tests;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.HomePage;
import pageObject.LoginPage;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class HomePageTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;

    @BeforeClass
    @Parameters("browser")
    public void setup(@Optional("chrome")String browserName)throws InterruptedException{
        loadPropertiesFile();
        driver=initalizeBrowserAndOpenApp(browserName);
        loginPage= new LoginPage(driver);
        loginPage.performLogin("testlux1@gmail.com", "12345678");
        homePage = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='logo']/a")));
    }


    @Test
    public void openAndVerifyHomepage(){
        homePage.performopenAndVerifyHomepage();
    }

    @Test
    public void CheckNavElements(){
        homePage.performCheckNavElements();
    }

    @Test
    public void sliderCheck() throws InterruptedException {
        homePage.performsliderCheck();
    }

    @Test
    public void OpenProductPageCheck() throws InterruptedException {
        homePage.performOpenProductPageCheck();
    }

    @Test
    public void searchCheck(){
        homePage.performSearchCheck();
    }

    @Test
    public void compare() throws InterruptedException {
        homePage.performCompare();
    }

    @Test
    public  void wishlistCheck() throws InterruptedException {
        homePage.performWishlistCheck();
    }

}
