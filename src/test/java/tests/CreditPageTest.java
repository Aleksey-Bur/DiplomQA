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

public class CreditPageTest {
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
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getApprovedCard());
        creditPage.checkStatusOk();
        assertEquals("APPROVED", SqlHelper.getCreditRequestStatus());
    }

    @Test
    void  shouldBuyWithDeclinedCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getDeclinedCard());
        creditPage.checkStatusDenied();
        assertEquals("DECLINED", SqlHelper.getCreditRequestStatus());
    }

    @Test
    void shouldBuyWithNonexistentCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getNonexistentCard());
        creditPage.checkStatusDenied();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void shouldBuyWithIncompleteCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getIncompleteNumberCard());
        creditPage.checkStatusInvalidField();
        assertEquals("Неверный формат", creditPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithMonthZeroCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getMonthZeroCard());
        creditPage.checkStatusInvalidField();
        assertEquals("Неверный формат", creditPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithNonexistentMonthCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getNonexistentMonthCard());
        creditPage.checkStatusInvalidField();
        assertEquals("Неверно указан срок действия карты", creditPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithExpiredMonthCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getExpiredMonthCard());
        creditPage.checkStatusInvalidField();
        assertEquals("Истёк срок действия карты", creditPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithExpiredYearCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getExpiredYearCard());
        creditPage.checkStatusInvalidField();
        assertEquals("Истёк срок действия карты", creditPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectMonthCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getIncorrectMonthCard());
        creditPage.checkStatusInvalidField();
        assertEquals("Неверный формат", creditPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectYearCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getIncorrectYearCard());
        creditPage.checkStatusInvalidField();
        assertEquals("Неверный формат", creditPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectNameCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getIncorrectNameCard());
        creditPage.checkStatusInvalidField();
        assertEquals("Неверный формат", creditPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectCVCCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getIncorrectCVCCard());
        creditPage.checkStatusInvalidField();
        assertEquals("Неверный формат", creditPage.checkStatusInvalidField());
    }

    @Test
    void shouldBuyWithEmptyFieldCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getEmptyCard());
        creditPage.checkStatusInvalidField();
        assertEquals("Поле обязательно для заполнения", creditPage.checkStatusInvalidField());
    }
}