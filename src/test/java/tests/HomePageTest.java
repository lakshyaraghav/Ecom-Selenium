package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class HomePageTest extends BaseTest {

    @BeforeClass
    public void setup(){
        loadPropertiesFile();
        driver=initalizeBrowserAndOpenApp("safari");
        LoginTest login= new LoginTest();
        login.driver=this.driver;
        login.loginTest();
    }


    @Test
    public void openAndVerifyHomepage(){
        driver.findElement(By.xpath("//div[@id=\"logo\"]/a")).click();

        String Eurl="https://awesomeqa.com/ui/index.php?route=common/home";
        String Curl=driver.getCurrentUrl();
        Assert.assertEquals(Curl,Eurl);
    }

    @Test
    public void CheckNavElements(){
        String phone=driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[1]/span")).getText();
        Assert.assertEquals(phone,"123456789");

        String account= driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")).getText();
        Assert.assertEquals(account,"My Account");

        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")).click();
        String myaccount=driver.findElement(By.xpath("//a[text()='My Account']")).getText();
        Assert.assertEquals(myaccount,"My Account");

        String history=driver.findElement(By.xpath("//a[text()='Order History']")).getText();
        Assert.assertEquals(history,"Order History");

        String transaction=driver.findElement(By.xpath("//a[text()='Transactions']")).getText();
        Assert.assertEquals(transaction,"Transactions");

        String downloads=driver.findElement(By.xpath("//a[text()='Downloads']")).getText();
        Assert.assertEquals(downloads,"Downloads");

        String logout=driver.findElement(By.xpath("//a[text()='Logout']")).getText();
        Assert.assertEquals(logout,"Logout");

        String wishList= driver.findElement(By.xpath("//*[@id=\"wishlist-total\"]/span")).getText();
        Assert.assertTrue(wishList.contains("Wish List"));

        String cart= driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[4]/a/span")).getText();
        Assert.assertEquals(cart,"Shopping Cart");

        String checkout=driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[5]/a/span")).getText();
        Assert.assertEquals(checkout,"Checkout");

        String currency=driver.findElement(By.xpath("//*[@id=\"form-currency\"]/div/button/span")).getText();
        Assert.assertEquals(currency,"Currency");

        Actions actions= new Actions(driver);
        String desktop=driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/a")).getText();
        Assert.assertEquals(desktop,"Desktops");
        WebElement deskt=driver.findElement(By.xpath("//*[@id=\"menu\"]/div[2]/ul/li[1]/a"));
        actions.moveToElement(deskt).perform();
//        String pc0=driver.findElement(By.xpath("//li/a[text()='PC (0)']")).getText();
//        Assert.assertEquals(pc0,"PC (0)");

        String mac=driver.findElement(By.xpath("//li/a[text()='Mac (1)']")).getText();
        Assert.assertTrue(mac.contains("Mac"));
        driver.findElement(By.xpath("//*[@id=\"logo\"]/a")).click();
    }

    @Test
    public void sliderCheck() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"logo\"]/a")).click();
        WebElement slider=driver.findElement(By.xpath("//div[@id=\"slideshow0\"]/div[@class=\"swiper-wrapper\"]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String initialTransform = slider.getAttribute("style");

        System.out.println("Initial Transform: " + initialTransform);

        Thread.sleep(Duration.ofSeconds(5));

        String newTransform = slider.getAttribute("style");

        System.out.println("New Transform: " + newTransform);
        Assert.assertNotEquals(initialTransform, newTransform, "Slider is not moving automatically");
    }

    @Test
    public void OpenProductPageCheck(){
        driver.findElement(By.xpath("//div[@id=\"logo\"]/a")).click();
        WebElement product1=driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[1]/a"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", product1);
//        String productPriceIni=driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[2]/p[2]/text()")).getText();
        String ExTax=driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[2]/p[2]/span")).getText();
        String valueExTax=(ExTax.replaceAll("[^0-9.]", ""));

        product1.click();

        String productInner= driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/ul[2]/li[2]")).getText();
        String valueProductInner=(productInner.replaceAll("[^0-9.]", ""));
        System.out.println(valueProductInner);
        Assert.assertEquals(valueExTax,valueProductInner);
        System.out.println("Pull request change");




    }

    @Test
    public void searchCheck(){
        driver.findElement(By.xpath("//div[@id=\"search\"]/input")).sendKeys("Macbook");
        driver.findElement(By.xpath("//div[@id=\"search\"]/span/button")).click();
        List<WebElement> productNames=driver.findElements(By.cssSelector(".product-thumb h4 a"));

        for (WebElement product:productNames){
            String productName= product.getText();
            Assert.assertTrue(productName.toLowerCase().contains("macbook"),"Product name does not contain 'MacBook': " + productName);
        }

        System.out.println("✅ Test Passed: All products contain 'MacBook' in the name.");
    }

    @Test
    public void compare() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"logo\"]/a")).click();
        driver.findElement(By.xpath("// button[@onclick=\"compare.add('43');\"]")).click();
        driver.findElement(By.xpath("// button[@onclick=\"compare.add('40');\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()=\"product comparison\"]")).click();
        String getFirstPrice=driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody[1]/tr[3]/td[2]")).getText();
        String getSecondPrice=driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody[1]/tr[3]/td[3]")).getText();
        int firstPrice=Integer.parseInt(getFirstPrice.replace("$","").split("\\.")[0]);
        int secondPrice=Integer.parseInt(getSecondPrice.replace("$", "").split("\\.")[0]);
//        System.out.println("First Price: " + firstPrice);
//        System.out.println("Second Price: " + secondPrice);

        if (firstPrice>secondPrice){
            System.out.println(firstPrice);
            driver.findElement(By.xpath("//input[@onclick=\"cart.add('43', '1');\"]")).click();
        }else {
            driver.findElement(By.xpath("//input[@onclick=\"cart.add('40', '1');\"]")).click();
        }
    }

    @Test
    public  void wishlistCheck() throws InterruptedException {
        driver.findElement(By.xpath("//div[@id=\"logo\"]/a")).click();

        driver.findElement(By.xpath("//button[@onclick=\"wishlist.add('43');\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"wishlist-total\"]/span")).click();
        Thread.sleep(Duration.ofSeconds(2));
        WebElement productName = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[2]/a"));
        String actualName = productName.getText();
        System.out.println("Product Name: " + actualName);
        Assert.assertEquals(actualName, "MacBook", "Product name does not match!");

        By removeButton = By.xpath("//a[@data-original-title='Remove']");

// Click on the "Remove" icon
        driver.findElement(removeButton).click();

// Wait for a short time to let the DOM update
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(removeButton));

// Verify that the element is removed
        List<WebElement> elements = driver.findElements(removeButton);

        if (elements.size() == 0) {
            System.out.println("Element removed successfully ✅");
        } else {
            System.out.println("Element still present ❌");
        }


    }



}
