package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
       private SelenideElement codeFile = $("[data-test-id=code] input");
       private SelenideElement codeButton = $("[data-test-id=action-verify]");

       public VerificationPage(){
        codeFile.shouldBe(Condition.visible);
        }
        public DashboardPage validVerify(DataHelper.VerificationCode verificationCode){
           codeFile.setValue(verificationCode.getCode());
           codeButton.click();
           return new DashboardPage();
        }
    }


