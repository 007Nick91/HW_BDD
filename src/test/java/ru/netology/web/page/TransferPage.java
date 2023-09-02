package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Text;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement transferPage = $("h1.heading");
    private final SelenideElement amountCash = $("[data-test-id=amount] input");
    private final SelenideElement fromCard = $ ("[data-test-id= from] input");
    private final SelenideElement transferButton = $ ("[data-test-id=action-transfer]");
    private final SelenideElement errorText = $ ("[data-test-id=error]");

    public TransferPage() {
        transferPage.shouldBe(Condition.visible);
    }
    public DashboardPage validTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo){
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }
    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo){
        amountCash.setValue(amountToTransfer);
        fromCard.setValue(cardInfo.getCardNumber());
        transferButton.click();

    }

    public void findError(String expextedText){
        errorText.shouldHave(Condition.exactText(expextedText), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

}
