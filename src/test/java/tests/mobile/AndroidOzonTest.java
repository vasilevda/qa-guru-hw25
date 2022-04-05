package tests.mobile;

import com.codeborne.selenide.Selenide;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.back;
import static io.qameta.allure.Allure.step;

@Tag("Mobile")
@Epic("Android")
@Owner("vasilevda")
public class AndroidOzonTest extends TestBase {

    @Test
    @DisplayName("Тестирование поиска")
    void searchAppTest() {
        step("Нажать на кнопку <Каталог>", () ->
                $(AppiumBy.id("ru.ozon.app.android:id/menu_catalog")).click());

        step("Проверить наличие строки <Поиск>", () -> {
            Selenide.sleep(2000);
            if ($(AppiumBy.accessibilityId("widget common.pageHeader")).isDisplayed())
                back();

            $(AppiumBy.id("ru.ozon.app.android:id/searchTv")).shouldHave(text("Искать на Ozon"));
        });

        step("Нажать на поле <Искать на озон>", () ->
                $(AppiumBy.id("ru.ozon.app.android:id/searchBar")).click());

        step("Ввести в поле текст <Рыбалка>", () -> {
            $(AppiumBy.id("ru.ozon.app.android:id/search_src_text"))
                    .setValue("Рыбалка");

            $(AppiumBy.id("ru.ozon.app.android:id/titleTv"))
                    .click();
        });

        step("Проверить совподения", () -> {
            $(AppiumBy.id("ru.ozon.app.android:id/titleTv")).shouldHave(text("Рыбалка"));
        });
    }

    @Test
    @DisplayName("Добавления товара в корзину")
    void addToCartAppTest() {
        step("Нажать на кнопку <Каталог>", () ->
                $(AppiumBy.id("ru.ozon.app.android:id/menu_catalog")).click());

        step("Нажать на поле <Искать на озон>", () ->
                $(AppiumBy.id("ru.ozon.app.android:id/searchBar")).click());

        step("Ввести в поле текст <PS4 Игры>", () -> {
            $(AppiumBy.id("ru.ozon.app.android:id/search_src_text"))
                    .setValue("PS4 Игры");

            $(AppiumBy.id("ru.ozon.app.android:id/titleTv"))
                    .click();
        });

        step("Добавить первый товар из списка", () ->
                $(AppiumBy.id("ru.ozon.app.android:id/primaryButtonContainer"))
                        .click());

        step("Перейти на вкладку <Корзина>", () ->
                $(AppiumBy.id("ru.ozon.app.android:id/menu_cart"))
                        .click());

        step("Проверить корзину", () ->
                $(AppiumBy.id("ru.ozon.app.android:id/titleTv"))
                        .shouldHave(text("Товары (1)")));
    }

    @ValueSource(strings = {"Воронеж", "Санкт-Петербург", "Новосибирск"})
    @ParameterizedTest(name = "Тестирование смены региона <{0}>")
    void changeRegionAppTest(String region) {
        step("Нажать на кнопку <Moй OZON>", () ->
                $(AppiumBy.id("ru.ozon.app.android:id/menu_profile")).click());

        step("Нажать на кнопку локации", () ->
                $(AppiumBy.id("ru.ozon.app.android:id/sectionIv")).click());

        step("Вставить название города <" + region + ">", () ->
                $(AppiumBy.id("ru.ozon.app.android:id/search_src_text"))
                        .setValue(region));

        step("Вставить название города <" + region + ">", () ->
                $(AppiumBy.id("ru.ozon.app.android:id/listRv"))
                        .$$(AppiumBy.id("ru.ozon.app.android:id/titleTv"))
                        .get(1).click());

        step("Обновить приложение", () -> {
            back();
            $(AppiumBy.id("ru.ozon.app.android:id/menu_profile")).click();
        });

        step("Проверить регион", () -> {
            $(AppiumBy.id("ru.ozon.app.android:id/sectionTitleTv"))
                    .shouldHave(text(region));
        });
    }

    @CsvSource(value = {
            "Книги, Психология",
            "Аптека, Витамины",
            "Автотовары, Шины и диски"})
    @ParameterizedTest(name = "Тестирование выбора категорий <{0} - {1}>")
    void selectCategoryAppTest(String category, String subcategory) {
        step("Нажать на кнопку <Каталог>", () ->
                $(AppiumBy.id("ru.ozon.app.android:id/menu_catalog")).click());

        step("Выбрать категорию <" + category + ">", () -> {
            Selenide.sleep(2000);
            if ($(AppiumBy.accessibilityId("widget common.pageHeader")).isDisplayed())
                back();

            $(AppiumBy.xpath(String.format("//android.widget.TextView[@text=\"%s\"]", category))).click();
        });

        step("Проверить выбранную категорию <" + category + ">", () -> {
            Selenide.sleep(2000);
            if ($(AppiumBy.accessibilityId("widget common.pageHeader")).isDisplayed())
                back();

            $(AppiumBy.xpath(String.format("//android.widget.TextView[@text=\"%s\"]", category))).shouldHave(text(category));
        });

        step("Проверить подкатегорию <" + subcategory + ">", () ->
                $(AppiumBy.xpath(String.format("//android.widget.TextView[@text=\"%s\"]", subcategory))).shouldHave(text(subcategory)));
    }
}
