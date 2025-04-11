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

import java.time.Duration;

public class LoginTest extends BaseTest{
//    WebDriver driver;
    @Parameters("browser")
    @BeforeClass
    public void setup(String browserName){
        loadPropertiesFile();
        driver=initalizeBrowserAndOpenApp(browserName);
    }

    @Test
    public void loginTest(){

        driver.findElement(By.xpath("//a[@title=\"My Account\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Login']")));
        loginLink.click();
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));
        emailInput.sendKeys("testlux1@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("12345678");
        driver.findElement(By.xpath("//input[@type=\"submit\" and @value=\"Login\"]")).click();
        WebElement home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"logo\"]/a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", home);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", home);

    }

}
