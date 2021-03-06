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
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.remote.CapabilityType.APPLICATION_NAME;

@ParametersAreNonnullByDefault
public class EmulatorMobileDriver implements WebDriverProvider {
    static final Configure CFG = ConfigFactory.create(Configure.class);

    EmulatorMobileDriver() {
        Assertions.assertNotNull(CFG.hub(), "Url not fount");
    }

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(Capabilities capabilities) {
        File app = downloadApk();

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName("Android");
        options.setDeviceName("Pixel_4_API_30");
        options.setCapability(APPLICATION_NAME, "Appium");
        options.setApp(app.getAbsolutePath());
        options.setLocale("en");
        options.setLanguage("en");
        options.setAppPackage("ru.ozon.app.android");
        options.setAppActivity("ru.ozon.app.android.ui.start.PreStartActivity");

        try {
            return new AndroidDriver(new URL(CFG.hub()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private File downloadApk() {
        return new File("src/test/resources/apk/ru.ozon.app.android.apk");
    }
}
