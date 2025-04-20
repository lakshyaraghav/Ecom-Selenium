package tests;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class HomePageTest extends BaseTest {

    @BeforeClass
    @Parameters("browser")
    public void setup(@Optional("chrome")String browserName)throws InterruptedException{
        loadPropertiesFile();
        driver=initalizeBrowserAndOpenApp(browserName);
        LoginTest login= new LoginTest();
        login.driver=this.driver;
        login.loginTest();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='logo']/a")));
    }


    @Test
    public void openAndVerifyHomepage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement home = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='logo']/a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", home); // <-- This solves the issue

        String expectedUrl = "https://awesomeqa.com/ui/index.php?route=common/home";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Homepage URL verification failed.");
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement home = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='logo']/a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", home);
    }

    @Test
    public void sliderCheck() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement home = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='logo']/a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", home);

        WebElement slider=driver.findElement(By.xpath("//div[@id=\"slideshow0\"]/div[@class=\"swiper-wrapper\"]"));
        String initialTransform = slider.getAttribute("style");

        System.out.println("Initial Transform: " + initialTransform);

        Thread.sleep(Duration.ofSeconds(5));

        WebElement sliderAfter = driver.findElement(By.xpath("//div[@id='slideshow0']/div[@class='swiper-wrapper']"));
        String newTransform = sliderAfter.getAttribute("style");

        System.out.println("New Transform: " + newTransform);
        Assert.assertNotEquals(initialTransform, newTransform, "Slider is not moving automatically");
    }

    @Test
    public void OpenProductPageCheck() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Go to home page
        WebElement home = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#logo a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", home);

        // Wait for product section to load
        WebElement firstProduct = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div.product-thumb")
        ));

        // Scroll to first product
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);

        // Get Ex Tax text
        String exTax = driver.findElement(By.cssSelector("div.product-thumb .price-tax")).getText();  // e.g., "Ex Tax: $500.00"
        String valueExTax = exTax.replaceAll("[^0-9.]", "");

        // Click product
        WebElement productLink = driver.findElement(By.cssSelector("div.product-thumb a"));
        productLink.click();

        // Wait for product detail page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("content")));

        // Wait for tax section inside product page
        WebElement productInner = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#content > div > div.col-sm-4 > ul:nth-child(4) > li:nth-child(2)")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productInner);
        Thread.sleep(2000); // for Safari rendering

        String productInnerText = productInner.getText();  // e.g., "Ex Tax: $500.00"
        String valueProductInner = productInnerText.replaceAll("[^0-9.]", "");

        System.out.println("Ex Tax: " + valueExTax);
        System.out.println("Inner Product Text: " + valueProductInner);

        Assert.assertEquals(valueExTax, valueProductInner);
        System.out.println("Assertion Passed: Pull request change");

    }

    @Test
    public void searchCheck(){
        driver.findElement(By.xpath("//div[@id=\"search\"]/input")).sendKeys("Macbook");
        driver.findElement(By.xpath("//div[@id=\"search\"]/span/button")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-thumb h4 a")));

        List<WebElement> productNames = driver.findElements(By.cssSelector(".product-thumb h4 a"));

        for (WebElement product:productNames){
            String productName= product.getText();
            Assert.assertTrue(productName.toLowerCase().contains("macbook"),"Product name does not contain 'MacBook': " + productName);
        }

        System.out.println("✅ Test Passed: All products contain 'MacBook' in the name.");
    }

    @Test
    public void compare() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='logo']/a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logo);
        WebElement compare1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick=\"compare.add('43');\"]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", compare1);

        Thread.sleep(1000);

        WebElement compare2 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick=\"compare.add('40');\"]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", compare2);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()=\"product comparison\"]")).click();

        WebElement firstPriceEl = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='content']/table/tbody[1]/tr[3]/td[2]")));
        WebElement secondPriceEl = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='content']/table/tbody[1]/tr[3]/td[3]")));

        String getFirstPrice = firstPriceEl.getText();
        String getSecondPrice = secondPriceEl.getText();

        int firstPrice = Integer.parseInt(getFirstPrice.replace("$", "").split("\\.")[0].trim());
        int secondPrice = Integer.parseInt(getSecondPrice.replace("$", "").split("\\.")[0].trim());

        System.out.println("First Price: " + firstPrice);
        System.out.println("Second Price: " + secondPrice);

        if (firstPrice>secondPrice){
            System.out.println(firstPrice);
            driver.findElement(By.xpath("//input[@onclick=\"cart.add('43', '1');\"]")).click();
        }else {
            driver.findElement(By.xpath("//input[@onclick=\"cart.add('40', '1');\"]")).click();
        }
    }

    @Test
    public  void wishlistCheck() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='logo']/a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logo);

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
