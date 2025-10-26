package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class SLBank1Str {
    SelenideElement
        SlBanl = $x("//a[contains(.,'SL-банк')]"),
        textZagStr1 = $x("//h1"),
        SsilkaVhoda = $x("//a[contains(.,'войти в личный кабинет')]");

    public void zagg(String zz) {
        textZagStr1.shouldBe(text(zz));
        SlBanl.shouldBe(exist);
    }

    public void clickssilka() {
        SsilkaVhoda.click();
    }

}
