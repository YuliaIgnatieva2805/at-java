package pages;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;


public class ListReisov {
    SelenideElement
            firstRegButton = $x("//button[@class='register-btn']"),
            flightsCount = $x("//div[@id='flightsCount']"),
            newpoiskreisa = $x("//*[@id='flightsList']/button"),
            SortSpiskaReisov = $x("//*[@id='sortField']"),
            FiltrR = $x("//*[@id='sortDirection']"),
            DepartureCity = $x("//*[@id='flightsContainer']/tr[2]/td[2]"),
            ArrivalCity = $x("//*[@id='flightsContainer']/tr[2]/td[3]"),
            DepartureDate = $x("//*[@id='flightsContainer']/tr[2]/td[4]"),
            DepartureTime = $x("//*[@id='flightsContainer']/tr[2]/td[5]"),
            DeparturePrice = $x("//*[@id='flightsContainer']/tr[2]/td[6]"),
            NewPoisk = $x("//*[@id='flightsList']/button");

    public void chooseFirstFlight() {
        firstRegButton.click();
    }
    //УКАЗАТЬ КАКУЮ КНОПКУ ПО СПИСКУ РЕЙСАМ НАЖАТЬ
    public void chooseFirstFlight(String pp) {
        //firstRegButton.click();
        // путь //*[@id='flightsList']//tr[2]//button
        String ss = "//*[@id='flightsList']//tr[" + pp +"]/td[7]/button";
        $x(ss).click();
    }

    public void chooseFirstFlightPressEnter(String pp) {
        //firstRegButton.click();
        // путь //*[@id='flightsList']//tr[2]//button
        String ss = "//*[@id='flightsList']//tr[" + pp +"]/td[7]/button";
        $x(ss).pressEnter();
    }

    public void verifySuccessfullSearch() {
        //flightsCount.shouldNotHave(text("Найдено рейсов: 0"));
        //assertEquals(7, flightsList.size());
        firstRegButton.shouldNotBe(visible);
    }
    public void verifyNotButtonRegistration() {
        //flightsCount.shouldNotHave(text("Найдено рейсов: 0"));
        //assertEquals(7, flightsList.size());
        firstRegButton.shouldNotBe(visible);
    }
    public void backviborreisa() {
        newpoiskreisa.click();

    }
    public void SortSpiskaReisov(String price_time ) {
        //выставить по цене
        SortSpiskaReisov.selectOptionByValue(price_time);
    }

    public void filtrReisov(String asc_desc) {
        //выставить фильтр
        FiltrR.selectOptionByValue(asc_desc);
    }

     //хочу найти в таблице рейсов найти ук5азанную по порядку запись и сравнить с данными
     public void searchStrochkuReisa(String departure_city,
                        String arrival_city, String departure_date, String Timme, String Prrice) {
         DepartureCity.shouldHave(text(departure_city));
         ArrivalCity.shouldHave(text(arrival_city));
         DepartureDate.shouldHave(text(departure_date));
         DepartureTime.shouldHave(text(Timme));
         DeparturePrice.shouldHave(text(Prrice));
         }
    public void PoiskNew() {
        NewPoisk.click();
    }


}