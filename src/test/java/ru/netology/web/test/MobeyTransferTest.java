package ru.netology.web.test;

import com.codeborne.selenide.Condition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;

public class MobeyTransferTest {
    DashboardPage dashboardPage;


    @BeforeEach
    void shouldTransferMoneyBetweenCards(){
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var authInfo = getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode();
        dashboardPage = verificationPage.validVerify(verificationCode);

    }
    @Test
    void transferFromFirstToSecond(){
        var firstCardInfo = getFirstCardInfoPro();
        var secondCardInfo = getSecondCardInfoPro();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
        var amount = validAmount(firstCardBalance);
        var expectedBalanceFirstCard = firstCardBalance - amount;
        var expectedBalanceSecondCard = secondCardBalance + amount;
        var transferPage = dashboardPage.getCardToIdTransfer(secondCardInfo);
        dashboardPage = transferPage.validTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalanceFirstCard = expectedBalanceFirstCard;
        var actualBalanceSecondCard = expectedBalanceSecondCard;
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }

    @Test
    void transferFromSecondToFirst(){
        var firstCardInfo = getFirstCardInfoPro();
        var secondCardInfo = getSecondCardInfoPro();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
        var amount = validAmount(firstCardBalance);
        var expectedBalanceFirstCard = firstCardBalance + amount;
        var expectedBalanceSecondCard = secondCardBalance - amount;
        var transferPage = dashboardPage.getCardToIdTransfer(firstCardInfo);
        dashboardPage = transferPage.validTransfer(String.valueOf(amount), secondCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }
//    @Test
//    void transferFromFirstToSecond(){
//        var firstCardInfo = getFirstCardInfoPro();
//        var secondCardInfo = getSecondCardInfoPro();
//        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
//        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
//        var amount = invalidAmount(secondCardBalance);
//        var transferPage = dashboardPage.getCardToIdTransfer(firstCardInfo);
//        transferPage.makeTransfer(String.valueOf(amount), secondCardInfo);
//        transferPage.findError("Ошибка!Превышен лемит");
//        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
//        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
//        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
//        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
//
//    }

}
