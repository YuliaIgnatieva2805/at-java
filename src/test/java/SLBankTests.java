import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.SLBank1Str;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SLBankTests {

    @BeforeAll
    static void before_all() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browser = System.getenv("browser") != null ?
                System.getenv("browser") : "chrome";
    }

    @BeforeEach
    void before_each() {
        String page = "http://92.51.36.108:7777/top.academy/slbank/v02/";
        step("Open " + Configuration.browser, () -> open(page));
    }
    /*   //body[contains(.,'Платёж не выполнен. Недостаточно средств. ')]   */
    @Test
    void first_Test() {
        SLBank1Str srt1 = new SLBank1Str();
        srt1.zagg(" - пополнения и платежи");
        srt1.clickssilka();
       /* $x("//h1").shouldBe(text(" - пополнения и платежи"));
        $x("//a[contains(.,'SL-банк')]").shouldBe(exist);
        $x("//a[contains(.,'войти в личный кабинет')]").click(); */

        $x("//input[@type='submit' and  @value ='Отправить']").click();
        $x("//h1").shouldBe(text("SL-банк - личный кабинет - главная страница"));
        $x("//input[@type='submit' and  @value ='Пополнить счёт']").click();
        $x("//h1").shouldBe(text("SL-банк - личный кабинет - пополнение счёта"));
        $x("//h2").shouldBe(text("Внесение средств в 3-х валютах"));
        $x("//input[@type='submit' and  @value ='Пополнить счёт']").click();
        $x("//h1").shouldBe(text("SL-банк - личный кабинет - подтверждение пополнения счёта"));
        //$x("/html/body/text()").shouldBe(text("У вас на счёте: 0 рублей, 0 долларов, 0 евро"));
        $x("//input[@type='submit' and  @value ='Вернуться в личный кабинет']").click();
        $x("//h1").shouldBe(text("SL-банк - личный кабинет - главная страница"));
        $x("//input[@type='submit' and  @value ='Выполнить платёж']").click();
        $x("//select[@name='payment_purpose']").selectOption(3);
           //  selectOptionByValue("" , "chatGPT");
           //selectVasluOption("chatGPT");
        $x("//input[@name='payment_sum']").setValue("1");
        $x("//select[@name='payment_cur']").selectOptionByValue("eur");
        $x("//input[@type='submit' and  @value ='Оплатить']").click();

    }

}
