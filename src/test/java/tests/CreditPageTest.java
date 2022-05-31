package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataGenerator;
import data.DbUtils;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.CreditPage;
import pages.StartPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditPageTest {

    @BeforeEach
    public void openPage() {
        open("http://localhost:8080");
    }

    @AfterEach
    public void cleanBase() {
        DbUtils.cleanDB();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    /*positive scenarios*/

    @Test
    @DisplayName("Покупка в кредит,операция прошла успешно,в БД появилась запись со статусом APPROVED")
    void shouldConfirmCreditPayWithApprovedCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getApprovedCard());
        creditPage.waitNotificationSuccessVisible();
        assertEquals("APPROVED", DbUtils.findCreditRequestStatus());
    }

    @Test
    @DisplayName("Покупка в кредит,операция отклонена банком,в БД появилась запись со статусом DECLINED")
    void shouldDeniedCreditPayWithDeclinedCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getDeclinedCard());
        creditPage.waitNotificationFailedVisible();
        assertEquals("DECLINED", DbUtils.findCreditRequestStatus());
    }

    /*negative scenarios*/

    @Test
    @DisplayName("Кредит по данным карты с невалидным значением поля \"Номер карты\"")
    void shouldCreditPayNonValidCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getInvalidNumberCard());
        creditPage.waitNotificationWrongFormatVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с невалидным значением поля \"Месяц\"")
    void shouldCreditPayInvalidDateMonthCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getInvalidMonth());
        creditPage.waitNotificationWrongMonthVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с невалидным значением поля \"Владелец\"")
    void shouldCreditPayInvalidOwnerCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getInvalidOwnerCard());
        creditPage.waitNotificationWrongOwnerVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с невалидным значением поля \"CVC/CVV\"")
    void shouldCreditPayInvalidCvcCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getInvalidCvcCard());
        creditPage.waitNotificationWrongCvcVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с истекшим сроком (поля \"Год\")")
    void shouldCreditPayExpiredCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getExpiredCard());
        creditPage.waitNotificationCardExpiredVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с указанием срока действия карты больше 5 лет")
    void shouldCreditPayWrongDateCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getWrongDateCard());
        creditPage.waitNotificationWrongDateCardVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с отправкой пустой формы")
    void shouldCreditPayEmptyFieldsCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        assertTrue(creditPage.emptyFieldsWarnings());
    }

    @Test
    @DisplayName("Кредит по неизвестной карте\"неопознанный номер карты\"")
    void shouldCreditPayUnknownCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getFakeCard());
        creditPage.waitNotificationFailedVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с отправкой пустого поля \"Номер карты\"")
    void shouldCreditPayEmptyFieldCardNumber() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getEmptyCardNumber());
        creditPage.waitNotificationWrongFormatVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с отправкой пустого поля \"Месяц\"")
    void shouldCreditPayEmptyFieldMonthCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getEmptyMonthCard());
        creditPage.waitNotificationWrongFormatVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с отправкой пустого поля \"Год\"")
    void shouldCreditPayEmptyFieldYearCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getEmptyYearCard());
        creditPage.waitNotificationWrongFormatVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с отправкой пустого поля \"Владелец\"")
    void shouldCreditPayEmptyFieldHolderCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getEmptyHolderCard());
        creditPage.waitNotificationEmptyOwnerVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с отправкой пустого поля \"CVC/CVV\"")
    void shouldCreditPayEmptyFieldCVCCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getEmptyCVCCard());
        creditPage.waitNotificationWrongFormatVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с несуществующим значением поля \"Месяц\"")
    void shouldCreditPayNonexistentDateMonthCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getNonexistentMonth());
        creditPage.waitNotificationWrongMonthVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с неверным форматом даты в поле \"Месяц\"")
    void shouldCreditPayInvalidFormatDateMonthCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getInvalidFormatMonth());
        creditPage.waitNotificationWrongFormatVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с неверным форматом даты в поле \"Год\"")
    void shouldCreditPayInvalidFormatDateYearCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getInvalidFormatYear());
        creditPage.waitNotificationWrongFormatVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с нулями в поле \"Номер карты\"")
    void shouldCreditPayZerosInCardNumber() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getZerosNumberCard());
        creditPage.waitNotificationFailedVisible();
    }

    @Test
    @DisplayName("Кредит по данным карты с нулями в поле \"Год\"")
    void shouldCreditPayZerosInYearCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getZerosInYear());
        creditPage.waitNotificationExpirationDateExpired();
    }

    @Test
    @DisplayName("Кредит по данным карты с нулями в поле \"CVC/CVV\"")
    void shouldCreditPayZerosInCvcCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getZerosInCvcCard());
        creditPage.waitNotificationFailedVisible();
    }
}