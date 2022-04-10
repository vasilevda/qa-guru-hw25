package tests.ui.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class CategoryPage {

    private final ElementsCollection
            OBJECT_LINE = $$("[data-widget='objectLine']");

    @Step("Открыть страницу <{url}>")
    public CategoryPage openPage(String url) {
        open(url);
        return this;
    }

    @Step("Выбрать категорию <{0}>")
    public CategoryPage selectCategory(String category) {
        $$(OBJECT_LINE)
                .findBy(Condition.text(category))
                .$$(".tsBodyL")
                .findBy(Condition.text(category)).shouldHave(Condition.text(category))
                .click();
        return this;
    }

    @Step("Проверить категорию <{0}>")
    public CategoryPage checkCategory(String category) {
        $("[data-widget=catalogResultsHeader], [data-widget='caption']")
                .shouldBe(Condition.text(category));
        return this;
    }
}
