package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PoiskTikets {
    SelenideElement
            greeting = $("#greeting"),
            logout = $("#logoutButton"),
            fromCity = $x("//*[@id='departureCity']"),
            toCity = $("#arrivalCity"),
            flightDate = $("#departureDate"),
            findButton = $x("//button[.='Найти']"),
            message = $("#searchMessage");

   /* String
            errorMessageEmptyDate = "Пожалуйста, укажите дату вылета.";*/

    public void findFlights(String from, String to, String date) {
        fromCity.selectOption(from);
        toCity.selectOption(to);
        flightDate.setValue(date);
        findButton.click();
    }
    public void findFlightsPressEnter(String from, String to, String date) {
        fromCity.selectOption(from);
        toCity.selectOption(to);
        flightDate.setValue(date);
        findButton.pressEnter();
    }

    public void er_mess(String mm) {
        message.shouldHave(text(mm));
    }

//    public void verifyEmptyDate(){
//        //Проверяет, что есть сообщение о том, что дата не задана
//        message.shouldHave(text(errorMessageEmptyDate));
//    }

    public void greet(String fio) {
        greeting.shouldHave(text("Добро пожаловать, "+fio+"!"));
        System.out.println("Нашли в заголовке: " + "Добро пожаловать, "+fio+"!");
    }

    public void log_out() {
        logout.click();
        System.out.println("разлогинились");
    }

}