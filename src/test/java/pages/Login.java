package pages;

import com.codeborne.selenide.SelenideElement;
import net.bytebuddy.asm.Advice;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Login {
    SelenideElement Username, Password, LoginButton, Message, Zagolovok;
    String successMessage, errorMessage;
    public Login() {
        Username = $("#username");
        Password = $("#password");
        LoginButton = $("#loginButton");
        Message = $("#message");
        Zagolovok = $x("//*[@id='loginContainer']/h2");
        successMessage = "Вход в систему выполнен успешно! Загрузка...";
        errorMessage = "Неверное имя пользователя или пароль.";

    }

    public void logins(String username, String password) {
        Username.setValue(username);
        Password.setValue(password);
        LoginButton.click();
    }


    public void loginsPressEnter(String username, String password) {
        Username.setValue(username);
        Password.setValue(password);
        LoginButton.pressEnter();
    }

    public void zglvk(String mm) {
        Zagolovok.shouldHave(text(mm));
    }

    public void v_mess(String mm) {
            Message.shouldHave(text(mm));
        }

    public void verify_successful_login() {
        Message.shouldHave(text(successMessage));
    }

    public void verify_wrong_username_or_password() {
        Message.shouldHave(text(errorMessage));
    }


}