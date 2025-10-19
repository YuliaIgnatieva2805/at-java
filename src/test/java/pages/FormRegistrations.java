package pages;

import com.codeborne.selenide.*;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormRegistrations {
    //список всех элементов на странице
    // заголовок //*[@id="registrationForm"]/h2 = Регистрация на рейс
    // блок //*[@id="flightRegistrationInfo"]/h3  =Информация о рейсе
    //  as //*[@id="flightRegistrationInfo"]/p[1]/strong =  Маршрут:
    //dd //*[@id="flightRegistrationInfo"]/p[1]/text()   наличие что рейс в " Москва → Нью-Йорк"
    SelenideElement
        Zagolovok1 = $x("//*[@id='registrationForm']/h2"),
        Blok = $x("//*[@id='flightRegistrationInfo']/h3"),
        KudaReisText = $x("//*[@id='flightRegistrationInfo']/p[1]/text()"),
        KlientFIO = $x("//*[@id='passengerName']"),
        DocNr = $x("//*[@id='passportNumber']"),
        Maill = $x("//*[@id='email']"),
        phonne = $x("//*[@id='phone']"),
        ErrMessages = $x("//*[@id='registrationMessage']"),
        FinRegisr = $x("//button[contains(.,'Завершить регистрацию')]"),
        Back_V_Reis = $x("//button[contains(.,'Вернуться к найденным рейсам')]");

    Alert RegistrationFinishedAlert;


    public void paste_dannclient(
       String ffiioo, String nomerpass, String mmails, String telephon) {
        KlientFIO.setValue(ffiioo);
        DocNr.setValue(nomerpass);
        Maill.setValue(mmails);
        phonne.setValue(telephon);
    }

    public void control_dannclient(
            String ffiioo, String nomerpass, String mmails, String telephon) {
        KlientFIO.shouldBe(value(ffiioo));
        DocNr.shouldBe(value(nomerpass));
        Maill.shouldBe(value(mmails));
        phonne.shouldBe(value(telephon));
    }
    public void messagErr(String messerr) {
        ErrMessages.shouldBe(text(messerr));
    }


    public void FinishRegisr() {
        FinRegisr.click();
    }

    public void finish_registration_success() {
        FinRegisr.click();
        RegistrationFinishedAlert = switchTo().alert();
        assertTrue(RegistrationFinishedAlert.getText().contains("Бронирование завершено"));
        RegistrationFinishedAlert.accept();
    }

    public void finish_registration_successPressEnter() {
        FinRegisr.pressEnter();
        RegistrationFinishedAlert = switchTo().alert();
        assertTrue(RegistrationFinishedAlert.getText().contains("Бронирование завершено"));
        RegistrationFinishedAlert.accept();
    }

        public void BackFormsReisov() {
        Back_V_Reis.click();
    }
    public void BackFormsReisovPressEnter() {
        Back_V_Reis.pressEnter();
    }
}
