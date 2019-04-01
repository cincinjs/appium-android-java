import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class testTokopedia {

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void setup () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "192.168.74.101:5555");
        caps.setCapability("udid", "192.168.74.101:5555"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "8.0");
        caps.setCapability("appPackage", "com.tokopedia.tkpd");
        caps.setCapability("appActivity","com.tokopedia.navigation.presentation.activity.MainParentActivity");

        // Optional / based on condition
        //caps.setCapability("skipUnlock","true");
        caps.setCapability("noReset","true");

        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void basicTest () throws InterruptedException {

        //skipOnboarding1();
        //skipOnboarding2();
        doSearch();

    }

    private void doSearch() {

        WebElement btn_search = driver.findElement(By.id("et_search"));
        btn_search.click();

        WebElement txt_search =  driver.findElement(By.id("searchTextView"));
        txt_search.sendKeys("Iphone X\n");

        WebElement result = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[1]/android.view.ViewGroup/android.widget.TextView"));
        //result.wait();
        assert result.getText().equals("Iphone X");
    }

    private void skipOnboarding2() {
        driver.findElement(By.id("next")).click();
        driver.findElement(By.id("next")).click();
        driver.findElement(By.id("text_next")).click();
    }

    private void skipOnboarding1() {
        driver.findElement(By.id("next")).click();
        driver.findElement(By.id("next")).click();
        driver.findElement(By.id("next")).click();
        driver.findElement(By.id("next")).click();
        driver.findElement(By.id("button_start_now")).click();

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}