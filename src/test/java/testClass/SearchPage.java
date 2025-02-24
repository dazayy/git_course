package testClass;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helper.GetCatalog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThan;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage {

    /*
     * 1. get <p> tag with amount of books and compare with got amount
     * 2. go through pagination
     *
     *
     *
     *
     *
     *
     *
     * */

    @ParameterizedTest
    @ValueSource(strings = {"Стивен Кинг", "Герман Гессе", "Говард Лавкрафт"})
    void searchSuccessByAuthorNameTest(String authorName) {

        ElementsCollection booksByAuthorName = GetCatalog.getCatalogItems(authorName, ".kr-catalog-item_author");
        booksByAuthorName.forEach(
                book ->  assertEquals(
                        authorName.trim().toLowerCase() ,
                        book.$(".kr-catalog-item_author")
                                .getText()
                                .trim()
                                .toLowerCase()
                )
        );

    }


    @ParameterizedTest
    @ValueSource(strings = {"Оно", "Сияние", "Стрелок"})
    void searchSuccessByBookNameTest(String bookName) {

        String cssSelector = ".kr-catalog-item_title";
        ElementsCollection booksByBookName = GetCatalog.getCatalogItems(bookName, cssSelector);
        booksByBookName.forEach(
                book ->  assertEquals(
                        bookName.trim().toLowerCase() ,
                        book.$( cssSelector)
                                .getText()
                                .trim()
                                .toLowerCase()
                )
        );

    }


    @ParameterizedTest
    @ValueSource(strings = {"Идион", "Война и Мир", "Гарри Поттер"})
    void searchFailedByBookNameTest(String bookName) {

//        ElementsCollection booksByBookName = GetCatalog.getCatalogItems(bookName, cssSelector);


        String catalogTitle = "Каталог";
        String URL = "http://188.120.241.222/";
        String ERROR_MESSAGE = "По вашему запросу ничего не нашлось";

        open(URL);
        $("input.nav-element.button.nav-button").click();
        $(".books-header").shouldHave(text(catalogTitle));

        $(".input.search-input").clear();
        $(".input.search-input").setValue(bookName);
        $(".search-btn")
                .shouldBe(visible, Duration.ofSeconds(2))
                .shouldBe(clickable, Duration.ofSeconds(2))
                .click();

        String errorText = $(".empty_text_big").shouldBe(visible, Duration.ofSeconds(3)).getText();
        assertEquals(ERROR_MESSAGE, errorText);


    }


    @ParameterizedTest
    @ValueSource(strings = {"Пушкин", "Роулинг", "Замятин"})
    void searchFailedByAuthorNameTest(String authorName) {


        String catalogTitle = "Каталог";
        String URL = "http://188.120.241.222/";
        String ERROR_MESSAGE = "По вашему запросу ничего не нашлось";

        open(URL);
        $("input.nav-element.button.nav-button").click();
        $(".books-header").shouldHave(text(catalogTitle));

        $(".input.search-input").clear();
        $(".input.search-input").setValue(authorName);
        $(".search-btn")
                .shouldBe(visible, Duration.ofSeconds(2))
                .shouldBe(clickable, Duration.ofSeconds(2))
                .click();

        String errorText = $(".empty_text_big").shouldBe(visible, Duration.ofSeconds(3)).getText();
        assertEquals(ERROR_MESSAGE, errorText);


    }


    @ParameterizedTest
    @ValueSource(strings = {"qwertyuiopa", "qwertyuiopaqwertyuiopa", "qwertyuiopaqwertyuiopaqwertyuiopaqwertyuiopa"})
    void searchFailedByLengthTest(String stringLenght) {


        /*
        *
        *
        *

            - выдача товаров в каталоге не меняется
            - появляется красная подсказка "Максимальное количество символов 10"
            *
            * i need to check an alert or something like this:
            *
            *   * check the document to understand how alert looks like
            *   * get text from alert and compare it
            *
            *
            * (i think that it's a bug and i wont find anything about alert in doc)
        *
        *
        * */

        String catalogTitle = "Каталог";
        String URL = "http://188.120.241.222/";
        String ERROR_MESSAGE = "По вашему запросу ничего не нашлось";

        open(URL);
        $("input.nav-element.button.nav-button").click();
        $(".books-header").shouldHave(text(catalogTitle));

        $(".input.search-input").clear();
        $(".input.search-input").setValue(stringLenght);
        $(".search-btn")
                .shouldBe(visible, Duration.ofSeconds(2))
                .shouldBe(clickable, Duration.ofSeconds(2))
                .click();

        String errorText = $(".empty_text_big").shouldBe(visible, Duration.ofSeconds(3)).getText();
        assertEquals(ERROR_MESSAGE, errorText);


    }



}
