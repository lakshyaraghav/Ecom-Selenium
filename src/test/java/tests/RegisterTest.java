package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class RegisterTest extends BaseTest {


    @BeforeClass
    @Parameters("browser")
    public void setup(@Optional("chrome")String browserName) throws InterruptedException{
        loadPropertiesFile();
        driver=initalizeBrowserAndOpenApp(browserName);
        Thread.sleep(Duration.ofSeconds(1));
    }


    @Test
    public void registerTest(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.findElement(By.xpath("//a[@title=\"My Account\"]")).click();
        driver.findElement(By.xpath("//a[text()='Register']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname"))).sendKeys("Lakshya");
//        driver.findElement(By.id("input-firstname")).sendKeys("Lakshya");
        driver.findElement(By.id("input-lastname")).sendKeys("Raghav");
        driver.findElement(By.id("input-email")).sendKeys("testlux1@gmail.com");
        driver.findElement(By.id("input-telephone")).sendKeys("9810101927");
        driver.findElement(By.id("input-password")).sendKeys("12345678");
        driver.findElement(By.id("input-confirm")).sendKeys("12345678");
        driver.findElement(By.xpath("//input[@type='radio' and @name='newsletter' and @value='1']")).click();
        driver.findElement(By.xpath("//input[@type=\"checkbox\" and @name=\"agree\"]")).click();
        driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();


        WebElement msg= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/h1")));
        String getMessage=msg.getText();
        Assert.assertEquals(getMessage,"Register Account");
    }
}
