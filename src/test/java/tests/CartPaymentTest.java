package tests;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.CartPaymentPage;
import pageObject.LoginPage;

import java.time.Duration;
import java.util.List;

public class CartPaymentTest extends BaseTest {

    CartPaymentPage cartPaymentPage;
    LoginPage loginPage;

    @BeforeClass
    @Parameters("browser")
    public void setup(@Optional("chrome")String browserName) throws InterruptedException{
        loadPropertiesFile();
        driver=initalizeBrowserAndOpenApp(browserName);
        loginPage = new LoginPage(driver);
        loginPage.performLogin("testlux1@gmail.com", "12345678");
        Thread.sleep(Duration.ofSeconds(1));
        cartPaymentPage= new CartPaymentPage(driver);
    }


    @Test
    public void addToCartProduct() throws InterruptedException {
        cartPaymentPage.performAddToCart();

    }

    @Test
    public void fillDetails() throws InterruptedException {
        cartPaymentPage.performFillDetails();
    }

}