package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.TypeOptions.text;

public class KnopkaSLBank {
    SelenideElement
    butt = $x("//input[@type='submit'"),
    textZag = $x("//h1");

    public void zagg(String zz) {
        textZag.shouldBe(Condition.text(zz));
    }

    public void clickButt(String hh) {

    }

}
