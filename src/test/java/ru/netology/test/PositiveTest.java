package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLunits;
import ru.netology.page.TourOfferPage;

import static com.codeborne.selenide.Selenide.open;

public class PositiveTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openChrome() {
        open("http://localhost:8080/");
    }


    @DisplayName("Successful purchase by card.")
    @Test
    public void shouldConfirmPaymentWithApprovedCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var approvedCardInformation = DataHelper.getValidCardInformation();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(approvedCardInformation);
        payByCard.successfulPayCardPayment();

        var paymentId = SQLunits.getPaymentId();
        var statusForPayment = SQLunits.getStatusForPayment(paymentId);
        Assertions.assertEquals("APPROVED", statusForPayment);
    }

    @DisplayName("Successful credit purchase")
    @Test
    public void shouldConfirmBuyingOnCreditWithApprovedCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var approvedCardInformation = DataHelper.getValidCardInformation();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(approvedCardInformation);
        payByCreditCard.successfulCreditCardPayment();

        var paymentId = SQLunits.getPaymentId();
        var statusForPayment = SQLunits.getStatusForCredit(paymentId);
        Assertions.assertEquals("APPROVED", statusForPayment);
    }

    @DisplayName("By card that expires in the current month and year.")
    @Test
    public void shouldConfirmPaymentWithCurrentMonthAndYear() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var validCardInformation = DataHelper.getCardInformationWithCurrentMonthAndYear();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(validCardInformation);
        payByCard.successfulPayCardPayment();

    }

    @DisplayName("By credit with credit card that expires in the current month and year")
    @Test
    public void shouldConfirmBuyingOnCreditWithCurrentMonthAndYear() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var validCardInformation = DataHelper.getCardInformationWithCurrentMonthAndYear();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(validCardInformation);
        payByCreditCard.successfulCreditCardPayment();

    }

    @DisplayName("By card that expires next month")
    @Test
    public void shouldConfirmPaymentWithNextMonthCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var validCardInformation = DataHelper.getCardInformationWithNextMonth();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(validCardInformation);
        payByCard.successfulPayCardPayment();

    }

    @DisplayName("By Credit with card that expires next month.")
    @Test
    public void shouldConfirmBuyingOnCreditWithNextMonthCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var validCardInformation = DataHelper.getCardInformationWithNextMonth();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(validCardInformation);
        payByCreditCard.successfulCreditCardPayment();

    }

}
