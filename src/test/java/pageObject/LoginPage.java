package pageObject;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage{

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

   By myAccount= By.xpath("//a[@title='My Account']");
   By loginLink= By.xpath("//a[text()='Login']");
   By emailField= By.id("input-email");
   By passwordField= By.id("input-password");
   By loginButton= By.xpath("//input[@type=\"submit\" and @value=\"Login\"]");

   public void performLogin(String email, String password){
       driver.findElement(myAccount).click();
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       WebElement LoginLink = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
       LoginLink.click();
       WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
       emailInput.sendKeys(email);
       driver.findElement(passwordField).sendKeys(password);
       driver.findElement(loginButton).click();
   }

}
