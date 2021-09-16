package data;


import java.time.LocalDate;

public class DataHelper {
    private DataHelper() {
    }

    public static Card getApprovedCard() {
        LocalDate today = LocalDate.now();
        String month = String.valueOf(today).substring(5,7);
        String year = String.valueOf(today).substring(2,4);
        return new Card("4444 4444 4444 4441", month, year, "Ivan Petrov", "123");
    }

    public static Card getDeclinedCard() {
        LocalDate today = LocalDate.now();
        String month = String.valueOf(today).substring(5,7);
        String year = String.valueOf(today).substring(2,4);
        return new Card("4444 4444 4444 4442", month, year, "Ivan Petrov", "123");
    }

    public static Card getNonExistentCard() {
        LocalDate today = LocalDate.now();
        String month = String.valueOf(today).substring(5,7);
        String year = String.valueOf(today).substring(2,4);
        return new Card("1111 1111 1111 1111", month, year, "Ivan Petrov", "123");
    }

    public static Card getIncorrectNumberCard() {
        LocalDate today = LocalDate.now();
        String month = String.valueOf(today).substring(5,7);
        String year = String.valueOf(today).substring(2,4);
        return new Card("4444 4444 4444 444", month, year, "Ivan Petrov", "123");
    }

    public static Card getExpiredMonthCard() {
        LocalDate today = LocalDate.now();
        LocalDate lastMonth = today.minusMonths(1);
        String month = String.valueOf(lastMonth).substring(5,7);
        String year = String.valueOf(today).substring(2,4);
        return new Card("4444 4444 4444 4441", month, year, "Ivan Petrov", "123");
    }

    public static Card getIncorrectMonthCard(String month) {
        LocalDate today = LocalDate.now();
        String year = String.valueOf(today).substring(2,4);
        return new Card("4444 4444 4444 4441", month, year, "Ivan Petrov", "123");
    }

    public static Card getIncorrectFormatMonthCard() {
        LocalDate today = LocalDate.now();
        String year = String.valueOf(today).substring(2,4);
        return new Card("4444 4444 4444 4441", "0", year, "Ivan Petrov", "123");
    }

    public static Card getExpiredYearCard() {
        LocalDate today = LocalDate.now();
        LocalDate lastYear = today.minusYears(1);
        String month = String.valueOf(today).substring(5,7);
        String year = String.valueOf(lastYear).substring(2,4);
        return new Card("4444 4444 4444 4441", month, year, "Ivan Petrov", "123");
    }

    public static Card getYearMore5Card() {
        LocalDate today = LocalDate.now();
        LocalDate newYear = today.plusYears(6);
        String month = String.valueOf(today).substring(5,7);
        String year = String.valueOf(newYear).substring(2,4);
        return new Card("4444 4444 4444 4441", month, year, "Ivan Petrov", "123");
    }

    public static Card getIncorrectYearCard() {
        LocalDate today = LocalDate.now();
        String month = String.valueOf(today).substring(5,7);
        return new Card("4444 4444 4444 4441", month, "0", "Ivan Petrov", "123");
    }

    public static Card getIncorrectHolderCard() {
        LocalDate today = LocalDate.now();
        String month = String.valueOf(today).substring(5,7);
        String year = String.valueOf(today).substring(2,4);
        return new Card("4444 4444 4444 4441", month, year, "Petrov!@#$%^&*()_+", "123");
    }

    public static Card getIncorrectCVCCard() {
        LocalDate today = LocalDate.now();
        String month = String.valueOf(today).substring(5,7);
        String year = String.valueOf(today).substring(2,4);
        return new Card("4444 4444 4444 4441", month, year, "Ivan Petrov", "0");
    }
}