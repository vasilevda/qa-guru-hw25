package tests.api;

import helpers.CustomAllureListener;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import models.Country;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Tag("API")
@Epic("API")
@Owner("vasilevda")
public class APIOzonTest {

    private Country country = null;

    private final String COOKIE = "__Secure-access-token=3.0.GwQLN0RwQBOBbKf8b2_mFA.0.l8cMBQAAAABiR2sDF0CGFaN3ZWKgAICQoA..20220402011411.gbIggu-12joKnxg3ZzDv75OiwnQJF6Qm53oSW2nGB-M; " +
            "__Secure-refresh-token=3.0.GwQLN0RwQBOBbKf8b2_mFA.0.l8cMBQAAAABiR2sDF0CGFaN3ZWKgAICQoA..20220402011411._WTuOV6h6CbQ19iR1YUNJ_MIpRS5RuTYFzrH2Ldl4BQ;";

    @Test
    @DisplayName("Добавления товара в корзину")
    void testAddToCart() {
        step("Добавить товар в корзину", () -> {
            given()
                    .filter(CustomAllureListener.withCustomTemplates())
                    .contentType("application/json; charset=UTF-8")
                    .body("[{\"id\":146265158,\"quantity\":1}]")
                    .when()
                    .post("https://www.ozon.ru/api/composer-api.bx/_action/addToCart")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("success", is(true))
                    .body("cart.cartItems.qty[0]", is(1));
        });
    }

    @Test
    @DisplayName("Добавления товара в корзину выше доступного лимита")
    void testNegativeAddToCart() {
        step("Добавить товар в корзину", () -> {
            given()
                    .filter(CustomAllureListener.withCustomTemplates())
                    .cookie(COOKIE)
                    .contentType("application/json; charset=UTF-8")
                    .body("[{\"id\":146265158,\"quantity\":100500900}]")
                    .when()
                    .post("https://www.ozon.ru/api/composer-api.bx/_action/addToCart")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("success", is(true));
        });

        step("Проверить товар в корзине", () -> {
            given()
                    .filter(CustomAllureListener.withCustomTemplates())
                    .cookie(COOKIE)
                    .contentType("application/json; charset=UTF-8")
                    .when()
                    .get("https://www.ozon.ru/api/composer-api.bx/_action/summary")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("quantity", Matchers.not(100500900));
        });
    }

    @Test
    @DisplayName("Добавление товара в избранное")
    void testChangeRegion() {
        step("Добавить товара в избранное", () -> {
            given()
                    .filter(CustomAllureListener.withCustomTemplates())
                    .contentType("application/json; charset=UTF-8")
                    .body("{\"skus\":[146265158]}")
                    .when()
                    .post("https://www.ozon.ru/api/composer-api.bx/_action/favoriteBatchAddItems")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("trackingInfo.composerAction.actionType", is("favorite"));
        });
    }

    @CsvSource(value = {
            "2, Москва",
            "36, Заворово",
            "666, Наро-Фоминск"})
    @ParameterizedTest(name = "Тестирование смены региона <{0} - {1}>")
    void testSelectCategory(String areaId, String city) {
        step("Сменить регион", () -> {
            country  =
                    given()
                            .filter(CustomAllureListener.withCustomTemplates())
                            .contentType("application/json; charset=UTF-8")
                            .body(String.format("{\"AreaId\":%s,\"DefaultAreaId\":%s,\"UserAgent\":\"Mozilla/5.0\"}", areaId, areaId))
                            .when()
                            .post("https://www.ozon.ru/api/location/v2/user/location")
                            .then()
                            .log().all()
                            .statusCode(200)
                            .extract().body().as(Country.class);
        });

        step("Проверить город", () -> {
            Assertions.assertEquals(city, country.getCity(), "Регион не был изменен");
        });
    }
}
