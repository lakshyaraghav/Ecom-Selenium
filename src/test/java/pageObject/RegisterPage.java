package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class RegisterPage {

    WebDriver driver;
    WebDriverWait wait;

    public RegisterPage(WebDriver driver){
        this.driver=driver;
        this.wait= new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    By myAccount= By.xpath("//a[@title='My Account']");
    By register=By.xpath("//a[text()='Register']");
    By firstName= By.id("input-firstname");
    By lastName=By.id("input-lastname");
    By emailInput=By.id("input-email");
    By telephoneInput=By.id("input-telephone");
    By passwordInput=By.id("input-password");
    By passwordConfirm=By.id("input-confirm");
    By check1= By.xpath("//input[@type='radio' and @name='newsletter' and @value='1']");
    By check2= By.xpath("//input[@type=\"checkbox\" and @name=\"agree\"]");
    By submitButton= By.xpath("//input[@type=\"submit\"]");
    By registerMsg=By.xpath("//*[@id=\"content\"]/h1");

    public void performRegister(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(myAccount).click();
        driver.findElement(register).click();

        WebElement firstNam= wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        firstNam.sendKeys("Lakshya");
//        driver.findElement(By.id("input-firstname")).sendKeys("Lakshya");
        driver.findElement(lastName).sendKeys("Raghav");
        driver.findElement(emailInput).sendKeys("testlux1@gmail.com");
        driver.findElement(telephoneInput).sendKeys("9810101927");
        driver.findElement(passwordInput).sendKeys("12345678");
        driver.findElement(passwordConfirm).sendKeys("12345678");
        driver.findElement(check1).click();
        driver.findElement(check2).click();
        driver.findElement(submitButton).click();


        WebElement msg= wait.until(ExpectedConditions.visibilityOfElementLocated(registerMsg));
        String getMessage=msg.getText();
        Assert.assertEquals(getMessage,"Register Account");
    }


}
