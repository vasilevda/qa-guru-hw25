package tests.mobile.steps;

import com.codeborne.selenide.Selenide;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.back;

public class StepsMobile {

    private static final String
            TITLE_TV = "ru.ozon.app.android:id/titleTv",
            SEARCH_TV = "ru.ozon.app.android:id/searchTv",
            EXPRESS_MAIN = "ru.ozon.app.android:id/menu_express_main";


    @Step("Пропустить вспылвающую рекламу")
    public StepsMobile skipBanner() {
        Selenide.sleep(3000);
        if ($(AppiumBy.accessibilityId("widget common.pageHeader")).isDisplayed())
            back();
        if ($(AppiumBy.accessibilityId("widget cms.banner")).isDisplayed())
            back();
        return this;
    }

    @Step("Сменить регион если каталог -> express")
    public StepsMobile changeRegionIfExpressPos() {
        if ($(AppiumBy.id(EXPRESS_MAIN)).isDisplayed()) {
            skipBanner();
            openProfile();
            clickRegionLocation();
            searchRegionName("Воронеж");
            selectRegionFromList();
            back();
            openCatalog();
        }
        return this;
    }

    @Step("Обновить приложение")
    public StepsMobile refresh() {
        back();
        $(AppiumBy.id("ru.ozon.app.android:id/menu_profile")).click();
        return this;
    }

    @Step("Нажать на кнопку <Каталог>")
    public StepsMobile openCatalog() {
        $(AppiumBy.id("ru.ozon.app.android:id/menu_catalog")).click();
        return this;
    }

    @Step("Выбрать категорию <{0}}>")
    public StepsMobile selectCategory(String category) {
        $(AppiumBy.xpath(String.format("//android.widget.TextView[@text=\"%s\"]", category))).click();
        return this;
    }

    @Step("Проверить выбранную категорию <{0}}>")
    public StepsMobile checkCategory(String category) {
        $(AppiumBy.xpath(String.format("//android.widget.TextView[@text=\"%s\"]", category))).shouldHave(text(category));
        return this;
    }

    @Step("Проверить подкатегорию <{0}}>")
    public StepsMobile checkSubcategory(String subcategory) {
        $(AppiumBy.xpath(String.format("//android.widget.TextView[@text=\"%s\"]", subcategory))).shouldHave(text(subcategory));
        return this;
    }

    @Step("Нажать на кнопку <Карзину>")
    public StepsMobile openCart() {
        $(AppiumBy.id("ru.ozon.app.android:id/menu_cart")).click();
        return this;
    }

    @Step("Нажать на кнопку <Moй OZON>")
    public StepsMobile openProfile() {
        $(AppiumBy.id("ru.ozon.app.android:id/menu_profile")).click();
        return this;
    }

    @Step("Нажать на кнопку локации")
    public StepsMobile clickRegionLocation() {
        $(AppiumBy.id("ru.ozon.app.android:id/sectionIv")).click();
        return this;
    }

    @Step("Вставить название города <{0}>")
    public StepsMobile searchRegionName(String region) {
        $(AppiumBy.id("ru.ozon.app.android:id/search_src_text"))
                .setValue(region);
        return this;
    }

    @Step("Выбрать город <{0}> из списка")
    public StepsMobile selectRegionFromList() {
        $(AppiumBy.id("ru.ozon.app.android:id/listRv"))
                .$$(AppiumBy.id("ru.ozon.app.android:id/titleTv"))
                .get(1)
                .click();
        return this;
    }

    @Step("Проверить наличие строки <Поиск>")
    public StepsMobile checkSearchLine() {
            $(AppiumBy.id(SEARCH_TV)).shouldHave(text("Искать на Ozon"));
        return this;
    }

    @Step("Ввести в поле текст <{0}>")
    public StepsMobile search(String value) {
        $(AppiumBy.id("ru.ozon.app.android:id/searchBar")).click();
        $(AppiumBy.id("ru.ozon.app.android:id/search_src_text"))
                .setValue(value);
        return this;
    }

    @Step("Выбрать первую позицию в поиске")
    public StepsMobile selectFirstPoint() {
        $(AppiumBy.id(TITLE_TV))
                .click();
        return this;
    }

    @Step("Проверить совподения c <{0}>")
    public StepsMobile checkMatches(String value) {
        $(AppiumBy.id(TITLE_TV)).shouldHave(text(value));
        return this;
    }

    @Step("Добавить первый товар из списка")
    public StepsMobile addFirstProduct() {
        $(AppiumBy.id("ru.ozon.app.android:id/primaryButtonContainer")).click();
        return this;
    }

    @Step("Проверить корзину по значнию")
    public StepsMobile checkCart(String value) {
        $(AppiumBy.id(TITLE_TV)).shouldHave(text(value));
        return this;
    }

    @Step("Проверить регион <{0}>")
    public StepsMobile checkRegion(String region) {
        $(AppiumBy.id("ru.ozon.app.android:id/sectionTitleTv"))
                .shouldHave(text(region));
        return this;
    }
}
