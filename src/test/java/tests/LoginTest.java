package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest{
//    WebDriver driver;


    public void loginTest(){
        driver.findElement(By.xpath("//a[@title=\"My Account\"]")).click();
        driver.findElement(By.xpath("//a[text()='Login']")).click();
        driver.findElement(By.id("input-email")).sendKeys("testlux1@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("12345678");
        driver.findElement(By.xpath("//input[@type=\"submit\" and @value=\"Login\"]")).click();
    }


}
