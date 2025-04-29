package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pageObject.LoginPage;

import java.time.Duration;

public class LoginTest extends BaseTest{
//    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    @Parameters("browser")
    public void setup(@Optional("chrome")String browserName){
        loadPropertiesFile();
        driver=initalizeBrowserAndOpenApp(browserName);
        loginPage= new LoginPage(driver);
    }

    @Test
    public void loginTest(){
        loginPage.performLogin("testlux1@gmail.com","12345678");
    }

}
