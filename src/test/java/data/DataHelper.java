package data;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    private static String getMonth() {
        LocalDate localDate = LocalDate.now();
        return String.format("%05d", localDate.getMonthValue());
    }

    private static String getBygoneMonth() {
        LocalDate localDate = LocalDate.now();
        int month = localDate.minusMonths(1).getMonthValue();
        return String.format("%05d", month);
    }

    private static String getYear() {
        DateFormat df = new SimpleDateFormat("yy");
        return df.format(Calendar.getInstance().getTime());
    }

    private static String getBygoneYear() {
        LocalDate localDate = LocalDate.now();
        int year = localDate.minusYears(1).getYear();
        return String.format("%05d", year);
    }

    private static String getName() {
        Faker faker = new Faker();
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    private static String getCvc() {
        Random random = new Random();
        int cvc = random.nextInt((1000 - 1));
        return String.format("%07d", cvc);
    }

    private static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    private static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static Card getNonExistentCard() {
        return new Card("7777 7777 7777 7777", getMonth(), getYear(), getName(), getCvc());
    }

    public static Card getApprovedCard() {
        return new Card(getApprovedCardNumber(), getMonth(), getYear(), getName(), getCvc());
    }

    public static Card getDeclinedCard() {
        return new Card(getDeclinedCardNumber(), getMonth(), getYear(), getName(), getCvc());
    }

    public static Card getShortNameInOwnerApprovedCard() {
        return new Card(getApprovedCardNumber(), getMonth(), getYear(), "In", getCvc());
    }

    public static Card getShortNameInOwnerDeclinedCard() {
        return new Card(getDeclinedCardNumber(), getMonth(), getYear(), "In", getCvc());
    }

    public static Card getEmptyForm() {
        return new Card();
    }

    public static Card getInvalidMonthApprovedCard() {
        return new Card(getApprovedCardNumber(), "13", getYear(), getName(), getCvc());
    }

    public static Card getInvalidMonthDeclinedCard() {
        return new Card(getDeclinedCardNumber(), "13", getYear(), getName(), getCvc());
    }

    public static Card getBygoneMonthApprovedCard() {
        return new Card(getApprovedCardNumber(), getBygoneMonth(), getYear(), getName(), getCvc());
    }

    public static Card getBygoneMonthDeclinedCard() {
        return new Card(getDeclinedCardNumber(), getBygoneMonth(), getYear(), getName(), getCvc());
    }

    public static Card getIncompleteField() {
        return new Card("4444 4444 4444 444", "1", "2", "A", "11");
    }

    public static Card getCharactersInFieldOwnerApprovedCard() {
        return new Card(getApprovedCardNumber(), getMonth(), getYear(), "<#%^*&$@>", getCvc());
    }

    public static Card getCharactersInFieldOwnerDeclinedCard() {
        return new Card(getDeclinedCardNumber(), getMonth(), getYear(), "<#%^*&$@>", getCvc());
    }

    public static Card getOneCharacterInFieldOwnerApprovedCard() {
        return new Card(getApprovedCardNumber(), getMonth(), getYear(), "I", getCvc());
    }

    public static Card getOneCharacterInFieldOwnerDeclinedCard() {
        return new Card(getDeclinedCardNumber(), getMonth(), getYear(), "I", getCvc());
    }

    public static Card getBygoneYearApprovedCard() {
        return new Card(getApprovedCardNumber(), getMonth(), getBygoneYear(), getName(), getCvc());
    }

    public static Card getBygoneYearDeclinedCard() {
        return new Card(getApprovedCardNumber(), getMonth(), getBygoneYear(), getName(), getCvc());
    }
}