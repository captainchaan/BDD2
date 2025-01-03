package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement amountField = $("[data-test-id=amount] .input__control");
    private final SelenideElement numberCardField = $("[data-test-id=from] .input__control");
    private final SelenideElement buttonTransfer = $("[data-test-id=action-transfer]");
    private final SelenideElement errorMessage = $("[data-test-id='error-notification'] .notification__content");


    public TransferPage() {
        SelenideElement transferHead = $(byText("Пополнение карты"));
        transferHead.shouldBe(visible);
    }

    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountField.setValue(amountToTransfer);
        numberCardField.setValue(cardInfo.getCardNumber());
        buttonTransfer.click();
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(Condition.text(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }

}