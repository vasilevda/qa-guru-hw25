package tests;

import com.codeborne.selenide.Configuration;
import config.Configure;
import drivers.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.Attachments.*;

public class TestBase {
    static final Configure CFG = ConfigFactory.create(Configure.class);

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        switch (CFG.device().toLowerCase()) {
            case "ui":
                UIWebDriver.createDriver();
                break;
            case "browserstack":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            case "selenoid":
                Configuration.browser = SelenoidMobileDriver.class.getName();
                break;
            case "emulation":
                Configuration.browser = EmulatorMobileDriver.class.getName();
                break;
            case "real":
                Configuration.browser = MobileDriver.class.getName();
                break;
            default:
                throw new IllegalArgumentException(
                        String.format("Unknown device name=%s. " +
                                "-Ddevice.name=[Browserstack/Selenoid/Emulation/Real/UI]", CFG.device()));
        }
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void beforeEach() {
        open();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = getSessionId();
        screenshotAs("Last screenshot");
        pageSource();

        switch (CFG.device().toLowerCase()) {
            case "browserstack":
                videoBrowserstack(sessionId);
                break;
            case "selenoid":
                videoSelenoid(sessionId);
                break;
            case "ui":
                browserConsoleLogs();
                videoSelenoid(sessionId);
                break;
        }
        closeWebDriver();
    }

}
