package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    public RegisterPage(WebDriver driver){

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


}
