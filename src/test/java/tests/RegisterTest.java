package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test
    public void registerTest(){
        driver.findElement(By.xpath("//a[@title=\"My Account\"]")).click();
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        driver.findElement(By.id("input-firstname")).sendKeys("Lakshya");
        driver.findElement(By.id("input-lastname")).sendKeys("Raghav");
        driver.findElement(By.id("input-email")).sendKeys("testlux1@gmail.com");
        driver.findElement(By.id("input-telephone")).sendKeys("9810101927");
        driver.findElement(By.id("input-password")).sendKeys("12345678");
        driver.findElement(By.id("input-confirm")).sendKeys("12345678");
        driver.findElement(By.xpath("//input[@type='radio' and @name='newsletter' and @value='1']")).click();
        driver.findElement(By.xpath("//input[@type=\"checkbox\" and @name=\"agree\"]")).click();
        driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
        String getMessage=driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
        Assert.assertEquals(getMessage,"Your Account Has Been Created!");
    }
}
