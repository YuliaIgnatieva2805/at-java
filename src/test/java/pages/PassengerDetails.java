package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class PassengerDetails {
    SelenideElement
         MessErr = $x("//*[@id='flash_alert']"),
        ButtonNext = $x("//input[@value='Next']"),
        ButtonPayNow = $x("//input[@value='Pay now']"),
    PFirstName = $x("//*[@name='passengerFirstName']"),
        PLastName = $x("//*[@name='passengerLastName']");

    public void VvodPDetailsNames(String FirstN, String LastN) {
        PFirstName.setValue(FirstN);
        PLastName.setValue(LastN);
        }

    public void PD_Next() {
        ButtonNext.click();
    }

    public  void  PD_MessErr(String me) {
        MessErr.shouldBe(text(me));
    }

    public  void pay(){
        ButtonPayNow.click();
    }
}
