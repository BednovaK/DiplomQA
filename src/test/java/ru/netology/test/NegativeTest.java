package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLunits;
import ru.netology.page.TourOfferPage;

import static com.codeborne.selenide.Selenide.open;


public class NegativeTest {

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

    @DisplayName("By Card with blank data.")
    @Test
    public void shouldNotConfirmPaymentWithEmptyForm() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var invalidCardInformation = DataHelper.getEmptyCardInformation();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("By Credit with blank data.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyForm() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var emptyCardInformation = DataHelper.getEmptyCardInformation();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(emptyCardInformation);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("By Card with Blank card number.")
    @Test
    public void shouldNotConfirmPaymentWithEmptyFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var fieldCardEmpty = DataHelper.getFieldCardEmpty();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(fieldCardEmpty);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("By Credit with Blank card data.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var fieldCardEmpty = DataHelper.getFieldCardEmpty();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(fieldCardEmpty);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("By Card with Year field blank data.")
    @Test
    public void shouldNotConfirmPaymentWithEmptyFieldYear() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var fieldYearEmpty = DataHelper.getFieldYearEmpty();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(fieldYearEmpty);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("By Credit with Year field blank data.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyFieldYear() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var fieldYearEmpty = DataHelper.getFieldYearEmpty();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(fieldYearEmpty);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("By Card with Month field blank data.")
    @Test
    public void shouldNotConfirmPaymentWithEmptyFieldMonth() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var fieldMonthEmpty = DataHelper.getFieldMonthEmpty();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(fieldMonthEmpty);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("By Credit with Month field blank data.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyFieldMonth() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var fieldMonthEmpty = DataHelper.getFieldMonthEmpty();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(fieldMonthEmpty);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("By Card with Holder field blank data")
    @Test
    public void shouldNotConfirmPaymentWithEmptyFieldHolder() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var fieldHolderEmpty = DataHelper.getFieldHolderEmpty();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(fieldHolderEmpty);
        payByCard.requiredPayCardToFillIn();
    }

    @DisplayName("By Credit with Holder field blank data")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyFieldHolder() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var fieldHolderEmpty = DataHelper.getFieldHolderEmpty();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(fieldHolderEmpty);
        payByCreditCard.requiredCreditCardToFillIn();
    }

    @DisplayName("By Card with blank CVV field data.")
    @Test
    public void shouldNotConfirmPaymentWithEmptyFieldCvv() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var fieldCvvEmpty = DataHelper.getFieldCVVEmpty();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(fieldCvvEmpty);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("By Credit with blank CVV field data.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyFieldCvv() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var fieldCvvEmpty = DataHelper.getFieldCVVEmpty();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(fieldCvvEmpty);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("By Card Declined card.")
    @Test
    public void shouldPaymentWithDeclinedCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var declinedCardInformation = DataHelper.getDeclinedCardInformation();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(declinedCardInformation);
        payByCard.notSuccessfulPayCardPayment();

        var paymentId = SQLunits.getPaymentId();
        var statusForPayment = SQLunits.getStatusForPayment(paymentId);
        Assertions.assertEquals("DECLINED", statusForPayment);
    }

    @DisplayName("By Credit Declined card.")
    @Test
    public void shouldPaymentWithDeclinedCreditCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var declinedCardInformation = DataHelper.getDeclinedCardInformation();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(declinedCardInformation);
        payByCreditCard.notSuccessfulCreditCardPayment();

        var paymentId = SQLunits.getPaymentId();
        var statusForPayment = SQLunits.getStatusForCredit(paymentId);
        Assertions.assertEquals("DECLINED", statusForPayment);
    }

    @DisplayName("By card expired card (in previous years)")
    @Test
    public void shouldNotConfirmPaymentWithExpiredYearCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var invalidCardInformation = DataHelper.getCardInformationWithExpiredYear();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.expiredPayCardYear();
    }

    @DisplayName("By credit expired card (in previous years)")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithExpiredYearCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var invalidCardInformation = DataHelper.getCardInformationWithExpiredYear();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.expiredCreditCardYear();
    }

    @DisplayName("By card expired card (previous month)")
    @Test
    public void shouldNotConfirmPaymentWithExpiredMonthCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var invalidCardInformation = DataHelper.getCardInformationWithExpiredMonth();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.expiredPayCardMonth();
    }

    @DisplayName("By Credit expired card (previous month)")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithExpiredMonthCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var invalidCardInformation = DataHelper.getCardInformationWithExpiredMonth();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.expiredCreditCardMonth();
    }

    @DisplayName("By card with zeros in month field.")
    @Test
    public void shouldNotConfirmPaymentWithZeroMonth() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var invalidCardInformation = DataHelper.getCardInformationWithZeroMonth();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.expiredPayCardMonth();
    }

    @DisplayName("By credit with card with zeros in month field")
    @Test
    public void shouldConfirmBuyingOnCreditWithZeroMonth() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var invalidCardInformation = DataHelper.getCardInformationWithZeroMonth();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.expiredCreditCardMonth();
    }

    @DisplayName("By card with an incorrect date field format")
    @Test
    public void shouldNotConfirmPaymentWithWrongFormatFieldsCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var invalidCardInformation = DataHelper.getCardInformationWithWrongFormatDate();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("By credit with card with an incorrect date field format")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithWrongFormatFieldsCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var invalidCardInformation = DataHelper.getCardInformationWithWrongFormatDate();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("By card with the holder's name in Cyrillic")
    @Test
    public void shouldNotConfirmPaymentWithCyrillicHolderFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var invalidCardInformation = DataHelper.getCardInformationWithCyrillicName();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("By Credit. Card with the holder's name in Cyrillic")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithCyrillicHolderFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var invalidCardInformation = DataHelper.getCardInformationWithCyrillicName();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("By card with the holder's name in Special Symbol.")
    @Test
    public void shouldNotConfirmPaymentWithSpecialSymbolInHolderFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var invalidCardInformation = DataHelper.getCardInformationSpecialSymbolInHolderFieldCard();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("By credit. Card with the holder's name Special Symbol")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEnglishPlusNumbersPlusSpecialSymbolInHolderFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var invalidCardInformation = DataHelper.getCardInformationSpecialSymbolInHolderFieldCard();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("By card with numbers in the name of the holder")
    @Test
    public void shouldNotConfirmPaymentWithNumericHolderFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var invalidCardInformation = DataHelper.getCardInformationWithNumericName();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("By Credit. Card data with numbers in the name of the holder")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithNumericHolderFieldCard() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var invalidCardInformation = DataHelper.getCardInformationWithNumericName();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("By card with Zero Format CVV")
    @Test
    public void shouldNotConfirmPaymentWithZeroFormatCVV() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var invalidCardInformation = DataHelper.getCardInformationWithZeroFormatCVV();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("By credit. Card with Zero Format CVV")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithZeroFormatCVV() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var invalidCardInformation = DataHelper.getCardInformationWithZeroFormatCVV();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("By card with Invalid Format CVV.")
    @Test
    public void shouldNotConfirmPaymentWithInvalidFormatCVV() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var invalidCardInformation = DataHelper.getCardInformationWithInvalidFormatCVV();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.invalidPayCardFormat();
    }

    @DisplayName("By Credit. Card with Invalid Format CVV")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithInvalidFormatCVV() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var invalidCardInformation = DataHelper.getCardInformationWithInvalidFormatCVV();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.invalidCreditCardFormat();
    }

    @DisplayName("By Card Invalid card number")
    @Test
    public void shouldNotConfirmedPaymentWithInvalidNumber() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var invalidCardInformation = DataHelper.getCardInformationWithInvalidNumber();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.invalidPayCardFormat();

    }

    @DisplayName("By Credit invalid card number.")
    @Test
    public void shouldNotPaymentWithInvalidCreditNumber() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var invalidCardInformation = DataHelper.getCardInformationWithInvalidNumber();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.invalidCreditCardFormat();

    }

    @DisplayName("By card with zero in year field.")
    @Test
    public void shouldNotConfirmPaymentWithZeroYear() {
        var tourOfferPage = new TourOfferPage();
        var payByCard = tourOfferPage.payByCard();
        var invalidCardInformation = DataHelper.getCardInformationWithZeroYear();
        tourOfferPage.payByCard();
        payByCard.enterPayCardData(invalidCardInformation);
        payByCard.expiredPayCardYear();
    }

    @DisplayName("By credit with card with zero in year field")
    @Test
    public void shouldConfirmBuyingOnCreditWithZeroYear() {
        var tourOfferPage = new TourOfferPage();
        var payByCreditCard = tourOfferPage.buyOnCredit();
        var invalidCardInformation = DataHelper.getCardInformationWithZeroYear();
        tourOfferPage.buyOnCredit();
        payByCreditCard.enterCreditCardData(invalidCardInformation);
        payByCreditCard.expiredCreditCardYear();
    }
}
