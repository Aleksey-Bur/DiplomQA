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
import pages.StartPage;
import pages.DebitPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitPageTest {
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
    void shouldDebitByCardWithStatusApproved() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getApprovedCard());
        debitPage.waitNotificationOk();
        assertEquals("APPROVED", SqlHelper.getDebitStatus());
    }

    @Test
    void shouldDebitByCardWithStatusDecline() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getDeclinedCard());
        debitPage.waitNotificationError();
        assertEquals("DECLINED", SqlHelper.getDebitStatus());
    }

    @Test
    void shouldShortNameInOwnerApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getShortNameInOwnerApprovedCard());
        debitPage.waitNotificationOk();
    }

    @Test
    void shouldShortNameInOwnerDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getShortNameInOwnerDeclinedCard());
        debitPage.waitNotificationError();
    }

    @Test
    void shouldInvalidFieldMessageEmptyForm() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getEmptyForm());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getBygoneMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getBygoneMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageIncompleteField() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getIncompleteField());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getOneCharacterInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getBygoneYearApprovedCard());
        assertEquals("Истёк срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openDebitPage();
        val debitPage = new DebitPage();
        debitPage.fillData(DataHelper.getBygoneYearDeclinedCard());
        assertEquals("Истёк срок действия карты", debitPage.getInputInvalid());
    }
}