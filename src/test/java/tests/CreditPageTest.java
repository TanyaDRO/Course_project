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
        assertTrue(creditPage.waitNotificationWrongFormatVisible());
    }

    @Test
    @DisplayName("Кредит по данным карты с невалидным значением поля \"Месяц\"")
    void shouldCreditPayInvalidDateMonthCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getInvalidMonth());
        assertTrue(creditPage.waitNotificationWrongMonthVisible());
    }

    @Test
    @DisplayName("Кредит по данным карты с невалидным значением поля \"Владелец\"")
    void shouldCreditPayInvalidOwnerCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getInvalidOwnerCard());
        assertTrue(creditPage.waitNotificationWrongOwnerVisible());
    }

    @Test
    @DisplayName("Кредит по данным карты с невалидным значением поля \"CVC/CVV\"")
    void shouldCreditPayInvalidCvcCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getInvalidCvcCard());
        assertTrue(creditPage.waitNotificationWrongCvcVisible());
    }

    @Test
    @DisplayName("Кредит по данным карты с истекшим сроком (поля \"Год\")")
    void shouldCreditPayExpiredCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getExpiredCard());
        assertTrue(creditPage.waitNotificationCardExpiredVisible());
    }

    @Test
    @DisplayName("Кредит по данным карты с указанием срока действия карты больше 5 лет")
    void shouldCreditPayWrongDateCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        creditPage.fillInTheFields(DataGenerator.getWrongDateCard());
        assertTrue(creditPage.waitNotificationWrongDateCardVisible());
    }

    @Test
    @DisplayName("Кредит по данным карты с отправкой пустой формы")
    void shouldCreditPayEmptyFieldsCard() {
        StartPage startPage = new StartPage();
        CreditPage creditPage = startPage.byCredit();
        assertTrue(creditPage.emptyFieldsWarnings());
    }

}