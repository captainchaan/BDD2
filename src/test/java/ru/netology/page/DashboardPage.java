package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.w3c.dom.html.HTMLInputElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final ElementsCollection cards = $$(".list__item div");
    private final SelenideElement button = $("[data-test-id='action-reload']" );
    private final String balanceStart = "баланс:";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        var text = getCard(cardInfo).getText();
        return extractBalance(text);
    }

    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
        getCard(cardInfo).$("button").click();
        return new TransferPage();
    }

    private SelenideElement getCard(DataHelper.CardInfo cardInfo) {
        return cards.findBy(Condition.attribute("data-test-id", cardInfo.getTestId()));
    }

    public void reloadDashboardPage() {
        button.click();
        heading.shouldBe(visible);
    }


    public int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.lastIndexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish).trim();
        return Integer.parseInt(value);
    }


}