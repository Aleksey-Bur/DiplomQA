package data;

public class DataHelper {
    private DataHelper() {
    }

    public static Card getApprovedCard() {
        return new Card("4444444444444441", "12", "24", "Ivan Petrov", "123");
    }

    public static Card getDeclinedCard() {
        return new Card("4444444444444442", "12", "24", "Ivan Petrov", "123");
    }

    public static Card getNonexistentCard() {
        return new Card("7777777777777777", "12", "24", "Ivan Petrov", "123");
    }

    public static Card getIncompleteNumberCard() {
        return new Card("444444444444444", "12", "24", "Ivan Petrov", "123");
    }

    public static Card getMonthZeroCard() {
        return new Card("4444444444444441", "00", "24", "Ivan Petrov", "123");
    }

    public static Card getNonexistentMonthCard() {
        return new Card("4444444444444441", "13", "24", "Ivan Petrov", "123");
    }

    public static Card getExpiredMonthCard() {
        return new Card("4444444444444441", "01", "21", "Ivan Petrov", "123");
    }

    public static Card getExpiredYearCard() {
        return new Card("4444444444444441", "12", "20", "Ivan Petrov", "123");
    }

    public static Card getIncorrectMonthCard() {
        return new Card("4444444444444441", "0", "20", "Ivan Petrov", "123");
    }

    public static Card getIncorrectYearCard() {
        return new Card("4444444444444441", "12", "0", "Ivan Petrov", "123");
    }

    public static Card getIncorrectNameCard() {
        return new Card("4444444444444441", "12", "24", "Иван1234*!):%;", "123");
    }

    public static Card getIncorrectCVCCard() {
        return new Card("4444444444444441", "12", "24", "Ivan Petrov", "1");
    }

    public static Card getEmptyCard() {
        return new Card("", "", "", "", "");
    }
}
