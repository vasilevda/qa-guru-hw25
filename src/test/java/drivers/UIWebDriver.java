package drivers;

import com.codeborne.selenide.Configuration;
import config.Configure;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;

public class UIWebDriver {
    static final Configure CFG = ConfigFactory.create(Configure.class);

    UIWebDriver() {
        Assertions.assertNotNull(CFG.browser());
//        Assertions.assertNotNull(CFG.curl());
    }

    @Nonnull
    public static String createDriver() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://www.ozon.ru/";
        Configuration.browser = CFG.browser();
        Configuration.remote = CFG.curl();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        return Configuration.browser;
    }
}
