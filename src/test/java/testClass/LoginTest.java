package testClass;

import java.util.List;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;


public class LoginTest {

    @Test
    @DisplayName("Check catalog page")
    void successfullLoginTest() {

        String URL = "http://188.120.241.222/";
        String catalogTitle = "Каталог";

        open(URL);
        $("input.nav-element.button.nav-button").click();
        $(".books-header").shouldHave(text("Каталог"));
        assertEquals(catalogTitle, $(".books-header").getText());
    }

    @Test
    @DisplayName("Get book links")
    void getAllImageLinks() {

        String URL = "http://188.120.241.222/";
        List<String> links= new java.util.ArrayList<>(Collections.emptyList());

        open(URL);
        $("input.nav-element.button.nav-button").click();
        $$(".transition-fix.kr-catalog-item_img")
                .shouldHave(sizeGreaterThan(0))
                .forEach(
                        item -> links.add(item.getAttribute("src"))
                );

        links.forEach(System.out::println);
    }



}
