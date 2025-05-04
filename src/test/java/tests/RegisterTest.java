package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.RegisterPage;

import java.time.Duration;

public class RegisterTest extends BaseTest {

    RegisterPage registerPage;

    @BeforeClass
    @Parameters("browser")
    public void setup(@Optional("chrome")String browserName) throws InterruptedException{
        loadPropertiesFile();
        driver=initalizeBrowserAndOpenApp(browserName);
        Thread.sleep(Duration.ofSeconds(1));
        registerPage= new RegisterPage(driver);
    }


    @Test
    public void registerTest(){
        registerPage.performRegister();
    }
}
