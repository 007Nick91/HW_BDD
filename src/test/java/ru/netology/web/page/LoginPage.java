package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginFile = $("[data-test-id=login] input");
    private SelenideElement passwordFile = $("[data-test-id=password] input");
    private SelenideElement buttonFile = $("[data-test-id=action-login]");


    public VerificationPage validLogin(DataHelper.AuthInfo inform) {
        loginFile.setValue(inform.getLogin());
        passwordFile.setValue(inform.getPassword());
        buttonFile.click();
        return new VerificationPage();
    }
}

//{
//private SelenideElement codeFile = $("[data-test-id=code] input");
//private SelenideElement codeButton = $("[data-test-id=action-verify]");
//
//public VerificationPage(){
//        codeFile.shouldBe(Condition.visible);
//        }
//public DashboardPage validVerify(DataHelper.VerificationCode verificationCode){
//        codeFile.setValue(verificationCode.getCode());
//        codeButton.click();
//        return new DashboardPage();
//        }
