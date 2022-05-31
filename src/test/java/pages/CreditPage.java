package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.Card;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private SelenideElement heading = $$("h3").find(exactText("Кредит по данным карты"));
    private SelenideElement cardField = $("[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement monthField = $(byText("Месяц")).parent().$("[class=\"input__control\"]");
    private SelenideElement yearField = $(byText("Год")).parent().$("[class=\"input__control\"]");
    private SelenideElement ownerField = $(byText("Владелец")).parent().$("[class=\"input__control\"]");
    private SelenideElement CVCField = $(byText("CVC/CVV")).parent().$("[class=\"input__control\"]");


    private SelenideElement successfulOperation = $$("[class=\"notification__content\"]").find(text("Операция одобрена Банком."));
    private SelenideElement failedOperation = $$("[class=\"notification__content\"]").find(text("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement wrongFormatError = $(byText("Неверный формат"));
    private ElementsCollection wrongFormatFourError = $$(byText("Неверный формат"));
    private SelenideElement validityError = $(byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpiredError = $(byText("Истёк срок действия карты"));
    private SelenideElement fieldRequiredError = $(byText("Поле обязательно для заполнения"));

    private SelenideElement cancelSuccessField = $$("[class=\"icon-button__text\"]").first();
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

    public CreditPage() {
        heading.shouldBe(visible);
    }

    public void fillInTheFields(Card card) {
        cardField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        ownerField.setValue(card.getOwner());
        CVCField.setValue(card.getCvc());
        continueButton.click();
    }


    public void waitNotificationSuccessVisible() {
        successfulOperation.shouldBe(visible, Duration.ofSeconds(10));
        cancelSuccessField.click();
    }

    public void waitNotificationFailedVisible() {
        failedOperation.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void waitNotificationWrongFormatVisible() {
        wrongFormatError.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void waitNotificationWrongMonthVisible() {
        validityError.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void waitNotificationWrongOwnerVisible() {
        wrongFormatError.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void waitNotificationWrongCvcVisible() {
        wrongFormatError.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void waitNotificationCardExpiredVisible() {
        validityError.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void waitNotificationWrongDateCardVisible() {
        validityError.shouldBe(visible, Duration.ofSeconds(10));
    }

    public boolean emptyFieldsWarnings() {
        continueButton.click();
        return wrongFormatError.shouldBe(visible).isDisplayed()
                && fieldRequiredError.shouldBe(visible).isDisplayed();
    }

    public void waitNotificationEmptyOwnerVisible() {
        fieldRequiredError.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void waitNotificationExpirationDateExpired(){
        cardExpiredError.shouldBe(visible, Duration.ofSeconds(10));
    }
}