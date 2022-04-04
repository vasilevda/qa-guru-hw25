package tests.ui.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement
            TOP_BAR = $("[data-widget='topBar'] button"),
            SEARCH = $("[placeholder='Искать на Ozon']"),
            RESULTS_HEADER = $("[data-widget=fulltextResultsHeader]"),
            HORIZONTAL_MENU = $("[data-widget=horizontalMenu]");
    private final ElementsCollection
            OBJECT_LINE = $$("[data-widget='objectLine']");

    @Step("Открыть страницу <{url}>")
    public MainPage openPage(String url) {
        open(url);
        return this;
    }

    @Step("Перезагрузить страницу <{url}>")
    public MainPage refreshPage() {
        refresh();
        return this;
    }

    @Step("Провести поиск по искомому слову <{value}>")
    public MainPage search(String value) {
        $(SEARCH)
                .setValue(value)
                .pressEnter();
        return this;
    }

    @Step("Проверить результат заголовка <{value}>")
    public MainPage checkResultsHeader(String value) {
        $(RESULTS_HEADER)
                .shouldBe(Condition.text(value));
        return this;
    }

    @Step("Выбрать меню в заголовке <{0}>")
    public MainPage selectHeaderMenu(String category) {
        $(HORIZONTAL_MENU)
                .$(By.partialLinkText(String.format("%s", category)))
                .click();
        return this;
    }

    @Step("Проверить заголовок выбранной категории меню <{0}>")
    public MainPage checkTitleCategoryMenu(String category) {
        $("[data-widget=catalogResultsHeader] h1")
                .shouldHave(Condition.text(category));
        return this;
    }

    @Step("Выбрать регион <{0}>")
    public MainPage selectRegion(String region) {
        String XPATH = "//div[contains(@class, 'ui')]/input[contains(@class, 'ui-h8')]";
        $(TOP_BAR).click();
        $(By.xpath(XPATH)).setValue(region);
        sleep(2000);
        $(By.xpath(XPATH)).pressEnter();
        return this;
    }

    @Step("Проверить регион <{0}>")
    public MainPage checkRegion(String region) {
        $(TOP_BAR)
                .$(".ui-f0")
                .shouldHave(Condition.text(region));
        return this;
    }

    @Step("Выбрать категорию <{0}>")
    public MainPage selectCategory(String category) {
        $$(OBJECT_LINE)
                .findBy(Condition.text(category))
                .$$(".tsBodyL")
                .findBy(Condition.text(category)).shouldHave(Condition.text(category))
                .click();
        return this;
    }

    @Step("Проверить категорию <{0}>")
    public MainPage checkCategory(String category) {
        $("[data-widget=catalogResultsHeader], [data-widget='caption']")
                .shouldBe(Condition.text(category));
        return this;
    }
}
