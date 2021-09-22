package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.DataHelper;
import data.SqlHelper;
import pages.CreditPage;
import pages.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class CreditPageTest {

    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUpEach() {
        String url = System.getProperty("sut.url");
        open(url);
        SqlHelper.clearData();
    }

    @AfterAll
    static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldCreditByCardWithStatusApproved() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getApprovedCard());
        creditPage.waitNotificationOk();
        assertEquals("APPROVED", SqlHelper.getCreditStatus());
    }

    @Test
    void shouldCreditByCardWithStatusDecline() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getDeclinedCard());
        creditPage.waitNotificationError();
        assertEquals("DECLINED", SqlHelper.getCreditStatus());
    }

    @Test
    void shouldShortNameInOwnerApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getShortNameInOwnerApprovedCard());
        creditPage.waitNotificationOk();
    }

    @Test
    void shouldShortNameInOwnerDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getShortNameInOwnerDeclinedCard());
        creditPage.waitNotificationError();
    }

    @Test
    void shouldInvalidFieldMessageEmptyForm() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getEmptyForm());
        creditPage.getInputInvalid();
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", creditPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", creditPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getBygoneMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", creditPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getBygoneMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", creditPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageIncompleteField() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getIncompleteField());
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getCharactersInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getOneCharacterInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getOneCharacterInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getBygoneYearApprovedCard());
        assertEquals("Истёк срок действия карты", creditPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openCreditPage();
        val creditPage = new CreditPage();
        creditPage.fillData(DataHelper.getBygoneYearDeclinedCard());
        assertEquals("Истёк срок действия карты", creditPage.getInputInvalid());
    }
}

