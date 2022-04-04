package tests.ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.TestBase;
import tests.ui.page.MainPage;

@Tag("WEB")
@Epic("UI")
@Owner("vasilevda")
public class OzonTest extends TestBase {
    private final MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Тестирование поиска")
    void searchTest() {
        mainPage
                .openPage("")
                .search("PS4 Игры")
                .checkResultsHeader("PS4 Игры");
    }

    @ValueSource(strings = {"Таганрог", "Санкт-Петербург", "Новосибирск"})
    @ParameterizedTest(name = "Тестирование смены региона <{0}>")
    void changeRegionTest(String region) {
        mainPage
                .openPage("")
                .selectRegion(region)
                .refreshPage()
                .checkRegion(region);
    }

    @ValueSource(strings = {"TOP Fashion", "Электроника", "Ozon Travel"})
    @ParameterizedTest(name = "Проверяем горизантальное меню <{0}>")
    void checkCategoryMenuTest(String category) {
        mainPage
                .openPage("")
                .selectHeaderMenu(category)
                .checkTitleCategoryMenu(category);
    }

    @CsvSource(value = {
            "Книги, Психология, Книги по психологии",
            "Аптека, Витамины, Витамины, БАДы и пищевые добавки",
            "Автотовары, Аккумуляторы, Аккумуляторы и аксессуары"})
    @ParameterizedTest(name = "Тестирование выбора категорий <{0} - {1}>")
    void selectCategoryAppTest(String category, String subcategory, String title) {
        mainPage
                .openPage("category/")
                .selectCategory(category)
                .checkCategory(category)
                .selectCategory(subcategory)
                .checkCategory(title);
    }
}
