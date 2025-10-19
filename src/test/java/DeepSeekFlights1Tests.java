import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.FormRegistrations;
import pages.ListReisov;
import pages.Login;
import pages.PoiskTikets;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class DeepSeekFlights1Tests {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @BeforeEach
    void setUp() {
        open("https://slqa.ru/cases/DeepSeekFlights/");
        getWebDriver().manage().window().maximize();
    }

    //Тест-кейсы

    @Test
    @DisplayName("POM-01. Вход standard_user, " +
            "проверить пользователя и кнопку Logout BR-01-04 и BR-01-05")
    void test01() {
        //Проверить вход с корректными данными
        //(standard_user/stand_pass1),
        //проверить отображение данных пользователя после входа (ФИО в шапке)
        //Проверить кнопку выхода (Logout)
        Login login_page = new Login();
        login_page.zglvk("Аутентификация");
        login_page.logins("standard_user", "stand_pass1");
        login_page.verify_successful_login();
        PoiskTikets proverka = new PoiskTikets();
        proverka.greet("Иванов Иван Иванович");
        proverka.log_out();
        login_page.zglvk("Аутентификация");
        sleep(5_000);
    }

    //@Test
    @DisplayName("POM-02.  Негативые ТК Входа")
    @ParameterizedTest(name = "Негативые ТК Входа. Проверка #{index} ")
    @CsvFileSource(resources = "logins.csv", numLinesToSkip = 1)
    void test1pp2_4(String us, String ps, String mmes) {
        //Проверить вход с неверным паролем
        //Проверить вход с пустыми полями
        //Проверить вход заблокированного пользователя (locked_out_user)
         Login login_page2 = new Login();
         login_page2.logins(us, ps);
         login_page2.v_mess(mmes);
         System.out.println("проверка завершена для " + us);
        }

    @Test
    @DisplayName("POM-02. Вход st_user и усп поиск рейса")
    void test02pp1() {
        Login login_page = new Login();
        login_page.logins("standard_user", "stand_pass1");
        login_page.verify_successful_login();
        // поиск рейса успешный:
        PoiskTikets PTiket = new PoiskTikets();
        PTiket.findFlights("Москва", "Нью-Йорк","15.12.2025");
        ListReisov LR = new ListReisov();
         LR.chooseFirstFlight();   // нажали первую кнопку для перехода к подтверж регистрации
    }

    @DisplayName("POM-03. Тестирование формы поиска рейса без даты и с прошлой датой")
    @ParameterizedTest(name = "Негативые ТК Поиска рейса. Проверка #{index}")
    @CsvFileSource(resources = "poisk_reisa.csv", numLinesToSkip = 1)
    void test2pp2_4(String fr,String ff,String dd,String msm) {
        Login login_page2 = new Login();
        login_page2.logins("standard_user", "stand_pass1");
        login_page2.verify_successful_login();
        // поиск рейса успешный:
        PoiskTikets PTiket2 = new PoiskTikets();
        PTiket2.findFlights(fr, ff, dd);
        sleep(5_000);
        PTiket2.er_mess(msm);
        sleep(5_000);
    }
    @Test
    @DisplayName("POM-04. Тестирование списка рейсов")
    void spisokreisovfiltr() {
        //Проверить отображение найденных рейсов
        Login login_page3 = new Login();
        login_page3.logins("standard_user", "stand_pass1");
        login_page3.verify_successful_login();
        PoiskTikets PTiket = new PoiskTikets();
        PTiket.findFlights("Москва", "Нью-Йорк","15.12.2025");
        ListReisov LR = new ListReisov();
        //Проверить сортировку по времени (возрастание-по умолч/убывание - сделать)
        //sleep(5_000);
        LR.searchStrochkuReisa("Москва","Нью-Йорк","2025-12-15",
                "07:30","55 000 руб.");
        LR.SortSpiskaReisov("price");
        LR.searchStrochkuReisa("Москва","Нью-Йорк","2025-12-15",
                "09:00","53 000 руб.");
        //sleep(5_000);
        LR.SortSpiskaReisov("time");
        LR.searchStrochkuReisa("Москва","Нью-Йорк","2025-12-15",
                "07:30","55 000 руб.");
        //sleep(5_000);
        //Проверить сортировку по цене (возрастание/убывание)
        LR.filtrReisov("desc");
        LR.searchStrochkuReisa("Москва","Нью-Йорк","2025-12-15",
                "18:00","60 000 руб.");
        LR.SortSpiskaReisov("price");
        LR.searchStrochkuReisa("Москва","Нью-Йорк","2025-12-15",
                "18:00","60 000 руб.");
        LR.filtrReisov("asc");
        sleep(5_000);
        LR.searchStrochkuReisa("Москва","Нью-Йорк","2025-12-15",
                "09:00","53 000 руб.");
         LR.PoiskNew();
        //Проверить кнопку "Новый поиск"

    }

    @Test
    @DisplayName("POM-05. Тестирование формы регистрации+заполнение данных пользователя + " +
            "работа кнопки 'Вернуться к найденным рейсам'")
    void registform(){
        //вход, выбор куда и когда, выбор рейса и нажатие на кнопку Зарегистрироваться
            /*Тестирование формы регистрации:
                Проверить переход к форме регистрации+
                Проверить автозаполнение данных пользователя+
                Проверить успешную регистрацию
                Проверить кнопку "Вернуться к найденным рейсам"
*/
        Login login_page4 = new Login();
        login_page4.logins("standard_user", "stand_pass1");
        login_page4.verify_successful_login();
        PoiskTikets PTiketForReg = new PoiskTikets();
        PTiketForReg.findFlights("Москва", "Нью-Йорк","15.12.2025");
        ListReisov LR = new ListReisov();
        //Проверить переход к форме регистрации
        LR.chooseFirstFlight("3");
        //Проверить переход к форме регистрации
        FormRegistrations FR = new FormRegistrations();
        FR.control_dannclient("Иванов Иван Иванович","1234 567890","ivanov@example.com","+7 (123) 456-7890");
        FR.finish_registration_success();
        FR.BackFormsReisov();
        sleep(2_000);
    }
    @Test
    @DisplayName("POM-05-1. Форма регистрации - валидацию полей")
    void validationregistform(){
        /* Проверить валидацию полей:
    Проверить отправку формы с пустыми полями
    ФИО (только русские буквы)
    Паспорт (только цифры и пробелы) */
        Login login_page4 = new Login();
        login_page4.logins("standard_user", "stand_pass1");
        login_page4.verify_successful_login();
        PoiskTikets PTiketForReg = new PoiskTikets();
        PTiketForReg.findFlights("Москва", "Нью-Йорк","15.12.2025");
        ListReisov LR = new ListReisov();
        LR.chooseFirstFlight("4");
        FormRegistrations FR = new FormRegistrations();
        FR.paste_dannclient("", "", "", "");
        sleep(2_000);
        FR.FinishRegisr();
        FR.messagErr("Пожалуйста, заполните все поля.");
        sleep(2_000);
        FR.paste_dannclient("yuyuy", "rtr", "hfh", "fyhfh");
        FR.FinishRegisr();
        FR.messagErr("ФИО должно содержать только русские буквы, пробелы и дефис.");
        sleep(2_000);
        FR.paste_dannclient("Иванов И. И.", "rtr", "hfh", "fyhfh");
        FR.FinishRegisr();
        FR.messagErr("ФИО должно содержать только русские буквы, пробелы и дефис.");
        FR.paste_dannclient("Иванов Иван Иванович", "rtr", "hfh", "fyhfh");
        FR.FinishRegisr();
        FR.messagErr("Номер паспорта должен содержать только цифры и пробелы.");

    }
    @Test
    @DisplayName("POM-06. Нажатие Enter в формах")
    void clickEnterForms() {
        Login login_page4 = new Login();
        login_page4.loginsPressEnter("standard_user", "stand_pass1");
        sleep(5000);
        PoiskTikets PTiketForReg = new PoiskTikets();
        PTiketForReg.findFlightsPressEnter("Москва", "Нью-Йорк","15.12.2025");
        ListReisov LR = new ListReisov();
        //Проверить переход к форме регистрации
        LR.chooseFirstFlightPressEnter("2");
        //Проверить переход к форме регистрации
        FormRegistrations FR = new FormRegistrations();
        // FR.control_dannclient("Иванов Иван Иванович","1234 567890","ivanov@example.com","+7 (123) 456-7890");
        FR.finish_registration_successPressEnter();
        FR.BackFormsReisovPressEnter();
        sleep(2_000);
    }


}
