import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static io.qameta.allure.Allure.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
public class CinemaParamsTests {
    @BeforeAll
    static void before_all() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browser = System.getenv("browser") != null ?
        System.getenv("browser") : "chrome";
    }

    @BeforeEach
    void before_each() {
        String page = "http://92.51.36.108:7777/sl.qa/cinema/index.php";
        step("Open " + Configuration.browser, () -> open(page));
    }

    //Тест для проверки цены билета
    //Тест для проверки цены билета
    @ParameterizedTest(name = "# {index} (age={0}, дата={1}, сеанс={2}, фильм={3})")
    @CsvFileSource(resources = "param.csv", numLinesToSkip = 1)
    @DisplayName("ЦенаБилета")
    void test_all(String age, String date, String session, String film, String price) {
        Allure.parameter("1. age", age);
        Allure.parameter("2. date", date);
        Allure.parameter("3. session", session);
        Allure.parameter("4. film", film);
        Allure.parameter("5. price", price);
        step("Заполняем поля", () -> {
            $("[name=age]").setValue(age);
            $("[name=date]").setValue(date);
            $("[name=session][value='" + session + "']").click();
            $("[name=film][value='" + film + "']").click();
        });
        step("Нажимаем кнопку", () -> $("input[type='submit']").click());
        step("Проверяем цену", () -> $x("//div").shouldHave(text(price)));
    }
}