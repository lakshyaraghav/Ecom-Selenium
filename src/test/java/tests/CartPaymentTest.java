package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CartPaymentTest extends BaseTest {
    @BeforeClass
    public void setup() throws InterruptedException{
        loadPropertiesFile();
        driver=initalizeBrowserAndOpenApp("chrome");
        LoginTest login= new LoginTest();
        login.driver=this.driver;
        login.loginTest();
        Thread.sleep(Duration.ofSeconds(2));
    }


    @Test
    public void addToCartProduct() throws InterruptedException {
        driver.findElement(By.xpath("//div[@id=\"logo\"]/a")).click();

        WebElement product1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[1]/a"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", product1);

        // Click on Add to Cart
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/button[1]")).click();

        // Wait for success message to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'alert-success')]")));

        // Scroll to message (optional)
        js.executeScript("arguments[0].scrollIntoView(true);", msg);

        // Extract and print success message
        String successMsg = msg.getText().trim();
        System.out.println("Success Message: " + successMsg);

        // Assert message contains expected text
        Assert.assertTrue(successMsg.contains("Success: You have added"), "Expected success message not found!");
        Thread.sleep(Duration.ofSeconds(2));
        driver.findElement(By.xpath("//a[@title='Shopping Cart']")).click();
        driver.findElement(By.xpath("//a[text()=\"Checkout\"]")).click();
    }

    @Test
    public void fillDetails(){

        List<WebElement> existingAddress = driver.findElements(By.xpath("//input[@name='payment_address' and @value='existing']"));

        if (!existingAddress.isEmpty()) {
            // Existing address option is present
            existingAddress.get(0).click(); // select it (if needed)
            driver.findElement(By.id("button-payment-address")).click();

        } else {
            // No existing address, so fill in the new address details
            System.out.println(prop.getProperty("firstName"));

            driver.findElement(By.id("input-payment-firstname")).sendKeys(prop.getProperty("firstName"));
            driver.findElement(By.id("input-payment-lastname")).sendKeys(prop.getProperty("lastName"));
            driver.findElement(By.id("input-payment-address-1")).sendKeys(prop.getProperty("address"));
            driver.findElement(By.id("input-payment-city")).sendKeys(prop.getProperty("city"));
            driver.findElement(By.id("input-payment-postcode")).sendKeys(prop.getProperty("postcode"));

            WebElement dropdown1 = driver.findElement(By.id("input-payment-country"));
            Select select1 = new Select(dropdown1);
            select1.selectByVisibleText("India");

            WebElement dropdown2 = driver.findElement(By.id("input-payment-zone"));
            Select select2 = new Select(dropdown2);
            select2.selectByVisibleText("Uttar Pradesh");

            driver.findElement(By.id("button-payment-address")).click();
        }


    }

    @Test
    public void paymentMethod(){
        driver.findElement(By.xpath("//textarea[@name=\"comment\"]")).sendKeys("Test Order");
        driver.findElement(By.xpath("//input[@type=\"checkbox\" and @name=\"agree\"]")).click();
        driver.findElement(By.id("button-payment-method")).click();
    }
}