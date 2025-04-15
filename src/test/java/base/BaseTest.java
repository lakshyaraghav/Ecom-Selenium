package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public static Properties prop= new Properties();

    public WebDriver initalizeBrowserAndOpenApp(String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new"); // ✅ new headless mode (since Chrome 109+)
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");

            driver = new ChromeDriver(options);
        }
//        or
//        if (browserName.equals("chrome")) {
//            driver = new ChromeDriver();
//        }
        else if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equals("edge")) {
            driver= new EdgeDriver();
        } else if (browserName.equals("safari")) {
            driver= new SafariDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get(prop.getProperty("appUrl"));
        return driver;
    }

    public void loadPropertiesFile(){


        File propFile= new File(System.getProperty("user.dir")+"/src/test/resources/configfiles/personalData.properties");
//        File locFile= new File(System.getProperty("user.dir")+"/src/test/resources/configfiles/locators.properties");
        try {
            FileInputStream fis= new FileInputStream(propFile);
//            FileInputStream loc= new FileInputStream(locFile);
            prop.load(fis);

        }
        catch (Throwable e){
            e.printStackTrace();
        }

    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
