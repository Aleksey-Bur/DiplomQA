package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.SqlHelper;
import data.Card;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.StartPage;

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
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void should1PaymentByCardWithStatusApproved() {
        SqlHelper.clearData();
        Card card = DataHelper.getApprovedCard();
        val startPage = new StartPage();
        val paymentPage = startPage.openDebitPage();
        paymentPage.fillData(card);
        paymentPage.waitNotificationOk();
        assertEquals("APPROVED", SqlHelper.getPaymentStatus());
    }

    @Test
    void should2PaymentByCardWithStatusDecline() {
        SqlHelper.clearData();
        Card card = DataHelper.getDeclinedCard();
        val startPage = new StartPage();
        val paymentPage = startPage.openDebitPage();
        paymentPage.fillData(card);
        paymentPage.waitNotificationError();
        assertEquals("DECLINED", SqlHelper.getPaymentStatus());
    }

    @Test
    void should3PaymentByNonExistentCard() {
        SqlHelper.clearData();
        Card card = DataHelper.getNonExistentCard();
        val startPage = new StartPage();
        val paymentPage = startPage.openDebitPage();
        paymentPage.fillData(card);
        paymentPage.waitNotificationError();
        assertEquals(0, SqlHelper.countRecords());
    }

    @Test
    void should4PaymentByIncorrectNumberCard() {
        Card card = DataHelper.getIncorrectNumberCard();
        val startPage = new StartPage();
        val paymentPage = startPage.openDebitPage();
        paymentPage.fillData(card);
        assertEquals("Неверный формат", paymentPage.getInputInvalid());
    }

    @Test
    void should5PaymentByExpiredMonthCard() {
        Card card = DataHelper.getExpiredMonthCard();
        val startPage = new StartPage();
        val paymentPage = startPage.openDebitPage();
        paymentPage.fillData(card);
        assertEquals("Истек срок действия карты", paymentPage.getInputInvalid());
    }

    @ParameterizedTest
    @CsvSource(value = {"'00'", "'13'"})
    void should6PaymentByIncorrectMonthCard(String month) {
        Card card = DataHelper.getIncorrectMonthCard(month);
        val startPage = new StartPage();
        val paymentPage = startPage.openDebitPage();
        paymentPage.fillData(card);
        assertEquals("Неверно указан срок действия карты", paymentPage.getInputInvalid());
    }

    @Test
    void should7PaymentByIncorrectFormatMonthCard() {
        Card card = DataHelper.getIncorrectFormatMonthCard();
        val startPage = new StartPage();
        val paymentPage = startPage.openDebitPage();
        paymentPage.fillData(card);
        assertEquals("Неверный формат", paymentPage.getInputInvalid());
    }

    @Test
    void should8PaymentByExpiredYearCard() {
        Card card = DataHelper.getExpiredYearCard();
        val startPage = new StartPage();
        val paymentPage = startPage.openDebitPage();
        paymentPage.fillData(card);
        assertEquals("Истёк срок действия карты", paymentPage.getInputInvalid());
    }

    @Test
    void should9PaymentByYearMore5Card() {
        Card card = DataHelper.getYearMore5Card();
        val startPage = new StartPage();
        val paymentPage = startPage.openDebitPage();
        paymentPage.fillData(card);
        assertEquals("Неверно указан срок действия карты", paymentPage.getInputInvalid());
    }

    @Test
    void should10PaymentByIncorrectYearCard() {
        Card card = DataHelper.getIncorrectYearCard();
        val startPage = new StartPage();
        val paymentPage = startPage.openDebitPage();
        paymentPage.fillData(card);
        assertEquals("Неверный формат", paymentPage.getInputInvalid());
    }

    @Test
    void should11PaymentByIncorrectHolderCard() {
        Card card = DataHelper.getIncorrectHolderCard();
        val startPage = new StartPage();
        val paymentPage = startPage.openDebitPage();
        paymentPage.fillData(card);
        assertEquals("Неверный формат", paymentPage.getInputInvalid());
    }

    @Test
    void should12PaymentByIncorrectCvcCard() {
        Card card = DataHelper.getIncorrectCVCCard();
        val startPage = new StartPage();
        val paymentPage = startPage.openDebitPage();
        paymentPage.fillData(card);
        assertEquals("Неверный формат", paymentPage.getInputInvalid());
    }
}