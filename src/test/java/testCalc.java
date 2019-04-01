import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class testCalc {

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void setup () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "192.168.74.101:5555");
        caps.setCapability("udid", "192.168.74.101:5555"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "8.0");
        caps.setCapability("appPackage", "com.android.calculator2");
        caps.setCapability("appActivity","com.android.calculator2.Calculator");

        // Optional / based on condition
        //caps.setCapability("skipUnlock","true");
        //caps.setCapability("noReset","false");

        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void basicTest () throws InterruptedException {

        //Click number 2
        driver.findElement(By.id("digit_2")).click();

        //Click plus (+)
        driver.findElement(By.id("op_add")).click();

        //Click number 4
        driver.findElement(By.id("digit_4")).click();

        //Click equal (=)
        driver.findElement(By.id("eq")).click();


        //locate the edit box of the calculator
        WebElement results = driver.findElement(By.id("result"));

        //Check the calculated value on the edit box
        assert results.getText().equals("6"):"Actual value is : "+results.getText()+" did not match with expected value: 6";
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}