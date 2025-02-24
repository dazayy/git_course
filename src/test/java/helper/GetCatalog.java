package helper;

import com.codeborne.selenide.ElementsCollection;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCatalog {

    public static ElementsCollection getCatalogItems(String valueToInput, String cssSelector) {

        String URL = "http://188.120.241.222/";
        String catalogTitle = "Каталог";

        open(URL);
        $("input.nav-element.button.nav-button").click();
        $(".books-header").shouldHave(text(catalogTitle));

        $(".input.search-input").clear();
        $(".input.search-input").setValue(valueToInput);
        $(".search-btn")
                .shouldBe(visible, Duration.ofSeconds(2))
                .shouldBe(clickable, Duration.ofSeconds(2))
                .click();

        $(cssSelector).shouldHave(text(valueToInput), Duration.ofSeconds(5));


        return ($$(".books-card.kr-catalog-item")
                .shouldHave(sizeGreaterThan(0), Duration.ofSeconds(3)));






    }
}
