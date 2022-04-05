package tests.mobile;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.TestBase;
import tests.mobile.steps.StepsMobile;

@Tag("Mobile")
@Epic("Android")
@Owner("vasilevda")
public class AndroidOzonTest extends TestBase {
    private StepsMobile stepsMobile = new StepsMobile();

    @Test
    @DisplayName("Тестирование поиска")
    void searchAppTest() {
        stepsMobile
                .openCatalog()
                .changeRegionIfExpressPos()
                .skipBanner()
                .checkSearchLine()
                .search("Рыбалка")
                .selectFirstPoint()
                .checkMatches("Рыбалка");
    }

    @Test
    @DisplayName("Добавления товара в корзину")
    void addToCartAppTest() {
        stepsMobile
                .changeRegionIfExpressPos()
                .openCatalog()
                .skipBanner()
                .checkSearchLine()
                .search("PS4 Игры")
                .selectFirstPoint()
                .addFirstProduct()
                .openCart()
                .checkCart("Товары (1)");
    }

    @ValueSource(strings = {"Воронеж", "Санкт-Петербург", "Новосибирск"})
    @ParameterizedTest(name = "Тестирование смены региона <{0}>")
    void changeRegionAppTest(String region) {
        stepsMobile
                .openProfile()
                .clickRegionLocation()
                .searchRegionName(region)
                .selectRegionFromList()
                .refresh()
                .checkRegion(region);
    }

    @CsvSource(value = {
            "Книги, Психология",
            "Аптека, Витамины",
            "Автотовары, Шины и диски"})
    @ParameterizedTest(name = "Тестирование выбора категорий <{0} - {1}>")
    void selectCategoryAppTest(String category, String subcategory) {
        stepsMobile
                .openCatalog()
                .changeRegionIfExpressPos()
                .selectCategory(category)
                .skipBanner()
                .checkCategory(category)
                .checkSubcategory(subcategory);
    }
}
