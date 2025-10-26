import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertionsTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.timeout = 4000;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("https://slqamsk.github.io/cases/pages-for-assertions");
    }

    // 1. JAVA ASSERT ТЕСТ - проверка атрибута title элемента #element-with-attributes
    @Test
    void javaAssertTest() {
        String title = $("#element-with-attributes").getAttribute("title");
        assert title.equals("Всплывающая подсказка");
     //   assert title.equals("Неверная подсказка");
    }

    // 2. JUNIT ASSERT ТЕСТ - проверка атрибута title элемента #element-with-attributes
    @Test
    void junitAssertTest() {
        String title = $("#element-with-attributes").getAttribute("title");
        assertEquals("Всплывающая подсказка", title);
       // assertEquals("Неверная подсказка", title);
    }

    // 3. SELENIDE CONDITIONS ТЕСТ - проверка атрибута title элемента #element-with-attributes
    @Test
    void selenideConditionsTest() {
        SelenideElement el = $("#element-with-attributes");
        el.shouldHave(attribute("title", "Всплывающая подсказка"));
      //  el.shouldHave(attribute("title", "Неверная подсказка"));
    }

    @Test
    void SelenButt() {
        String cls = $("#enabled-button").getAttribute("class");
        assert Objects.equals(cls, "active-button");


        String btn = $("#enabled-button").getAttribute("class");
        assertEquals("active-button", btn);

        SelenideElement bb = $("#enabled-button");
           bb.shouldHave(attribute("class","active-button"));
            }
}
