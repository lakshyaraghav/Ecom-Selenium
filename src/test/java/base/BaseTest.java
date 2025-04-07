package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

    @BeforeClass
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://awesomeqa.com/ui/");
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

//    @AfterMethod
//    public void teardown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

}
