package drivers;

import com.codeborne.selenide.Configuration;
import config.Configure;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class UIWebDriver {
    static final Configure CFG = ConfigFactory.create(Configure.class);
    private static final ChromeOptions OPTIONS = new ChromeOptions();

    UIWebDriver() {
        Assertions.assertNotNull(CFG.browser());
        Assertions.assertNotNull(CFG.remoteDriver());

        OPTIONS.addArguments("--no-sandbox");
        OPTIONS.addArguments("--disable-infobars");
        OPTIONS.addArguments("--disable-popup-blocking");
        OPTIONS.addArguments("--disable-notifications");
        OPTIONS.addArguments("--lang=ru-ru");
    }

    public static void createDriver() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://www.ozon.ru/";
        Configuration.browser = CFG.browser();
        Configuration.remote = CFG.remoteDriver();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, OPTIONS);
        Configuration.browserCapabilities = capabilities;
        setWebDriver(new ChromeDriver(OPTIONS));
    }
}
