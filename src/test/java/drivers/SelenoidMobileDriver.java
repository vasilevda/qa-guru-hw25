package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.Configure;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class SelenoidMobileDriver implements WebDriverProvider {
    static final Configure CFG = ConfigFactory.create(Configure.class);

    SelenoidMobileDriver () {
        Assertions.assertNotNull(CFG.remoteDriver(), "Сurl not fount");
    }

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName("Android");
        options.setDeviceName("android");
        options.setPlatformName("8.1");
        options.setCapability("enableVNC", true);
        options.setCapability("enableVideo", false);
        options.setApp(uploadAPK());
        options.setLocale("en");
        options.setLanguage("en");
        options.setAppPackage("org.wikipedia.alpha");
        options.setAppActivity("org.wikipedia.main.MainActivity");

        try {
            return new AndroidDriver(new URL(CFG.remoteDriver()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private URL uploadAPK() {
        try {
            return new URL("https://github.com/wikimedia/apps-android-wikipedia/" +
                    "releases/download/latest/app-alpha-universal-release.apk?raw=true");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}