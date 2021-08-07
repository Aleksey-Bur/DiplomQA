package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SqlHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import pages.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitPageTest {
    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void openHost() {
        String url = System.getProperty("sut.url");
        open(url);
    }

    @AfterEach
    public void clearBD() {
        SqlHelper.clearDB();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldBuyWithApprovedCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getApprovedCard());
        debitPage.checkStatusOk();
        assertEquals("APPROVED", SqlHelper.getPaymentStatus());
    }

    @Test
    void  shouldBuyWithDeclinedCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getDeclinedCard());
        debitPage.checkStatusDenied();
        assertEquals("DECLINED", SqlHelper.getPaymentStatus());
    }

    @Test
    void shouldBuyWithNonexistentCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getNonexistentCard());
        debitPage.checkStatusDenied();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldBuyWithIncompleteCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getIncompleteNumberCard());
        debitPage.checkStatusInvalidField();
        assertEquals("Неверный формат", debitPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithMonthZeroCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getMonthZeroCard());
        debitPage.checkStatusInvalidField();
        assertEquals("Неверный формат", debitPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithNonexistentMonthCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getNonexistentMonthCard());
        debitPage.checkStatusInvalidField();
        assertEquals("Неверно указан срок действия карты", debitPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithExpiredMonthCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getExpiredMonthCard());
        debitPage.checkStatusInvalidField();
        assertEquals("Истёк срок действия карты", debitPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithExpiredYearCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getExpiredYearCard());
        debitPage.checkStatusInvalidField();
        assertEquals("Истёк срок действия карты", debitPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectMonthCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getIncorrectMonthCard());
        debitPage.checkStatusInvalidField();
        assertEquals("Неверный формат", debitPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectYearCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getIncorrectYearCard());
        debitPage.checkStatusInvalidField();
        assertEquals("Неверный формат", debitPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectNameCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getIncorrectNameCard());
        debitPage.checkStatusInvalidField();
        assertEquals("Неверный формат", debitPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectCVCCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getIncorrectCVCCard());
        debitPage.checkStatusInvalidField();
        assertEquals("Неверный формат", debitPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithEmptyFieldCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getEmptyCard());
        debitPage.checkStatusInvalidField();
        assertEquals("Поле обязательно для заполнения", debitPage.checkStatusInvalidField());
    }
}