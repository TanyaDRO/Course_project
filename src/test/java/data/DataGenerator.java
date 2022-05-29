package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DataGenerator {
    private static Faker fakerEn = new Faker(new Locale("en"));
    private static Faker fakerRu = new Faker(new Locale("ru"));

    private static String generateDate(int months, String formatPattern) {
        return LocalDate.now().plusMonths(months).format(DateTimeFormatter.ofPattern(formatPattern));
    }


    public static Card getApprovedCard() {

        var approvedCardNum = "1111 2222 3333 4444";
        var cardMonth = generateDate(3, "MM");
        var cardYear = generateDate(3, "yy");
        var holder = fakerEn.name().firstName() + " " + fakerEn.name().lastName();
        var cvc = (int) (Math.random() * 1000);
        return new Card(approvedCardNum, cardMonth, cardYear, holder, String.format("%03d", cvc));
    }

    public static Card getDeclinedCard() {

        var declinedCardNum = "5555 6666 7777 8888";
        var cardMonth = generateDate(3, "MM");
        var cardYear = generateDate(3, "yy");
        var holder = fakerEn.name().firstName() + " " + fakerEn.name().lastName();
        var cvc = (int) (Math.random() * 1000);
        return new Card(declinedCardNum, cardMonth, cardYear, holder, String.format("%03d", cvc));
    }

    public static Card getInvalidNumberCard() {

        var invalidCardNum = "1111 2222 3333 444";
        var cardMonth = generateDate(3, "MM");
        var cardYear = generateDate(3, "yy");
        var holder = fakerEn.name().firstName() + " " + fakerEn.name().lastName();
        var cvc = (int) (Math.random() * 1000);
        return new Card(invalidCardNum, cardMonth, cardYear, holder, String.format("%03d", cvc));
    }

    public static Card getInvalidMonth() {

        var validCardNum = "1111 2222 3333 4444";
        var invalidCardMonth = "00";
        var cardYear = generateDate(3, "yy");
        var holder = fakerEn.name().firstName() + " " + fakerEn.name().lastName();
        var cvc = (int) (Math.random() * 1000);
        return new Card(validCardNum, invalidCardMonth, cardYear, holder, String.format("%03d", cvc));
    }

    public static Card getFakeCard() {

        var fakeCardNum = fakerEn.business().creditCardNumber();
        var cardMonth = generateDate(3, "MM");
        var cardYear = generateDate(3, "yy");
        var holder = fakerEn.name().firstName() + " " + fakerEn.name().lastName();
        var cvc = (int) (Math.random() * 1000);
        return new Card(fakeCardNum, cardMonth, cardYear, holder, String.format("%03d", cvc));
    }

    public static Card getInvalidOwnerCard() {

        var validCardNum = "1111 2222 3333 4444";
        var cardMonth = generateDate(3, "MM");
        var cardYear = generateDate(3, "yy");
        var invalidHolder = "123456789Йцукенгшщзхъ!\"№;%:?*()123456789Йцукенгшщзхъ!\"№;%:?*()";
        var cvc = (int) (Math.random() * 1000);
        return new Card(validCardNum, cardMonth, cardYear, invalidHolder, String.format("%03d", cvc));
    }

    public static Card getInvalidCvcCard() {

        var approvedCardNum = "1111 2222 3333 4444";
        var cardMonth = generateDate(3, "MM");
        var cardYear = generateDate(3, "yy");
        var holder = fakerEn.name().firstName() + " " + fakerEn.name().lastName();
        var invalidCvc = "99";
        return new Card(approvedCardNum, cardMonth, cardYear, holder, invalidCvc);
    }

    public static Card getExpiredCard() {

        var approvedCardNum = "1111 2222 3333 4444";
        var holder = fakerEn.name().firstName() + " " + fakerEn.name().lastName();
        var cvc = (int) (Math.random() * 1000);
        var date = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM.yy"));
        var strings = date.split("\\.");
        return new Card(approvedCardNum, strings[0], strings[1], holder, String.format("%03d", cvc));
    }

    public static Card getWrongDateCard() {

        var approvedCardNum = "1111 2222 3333 4444";
        var holder = fakerEn.name().firstName() + " " + fakerEn.name().lastName();
        var cvc = (int) (Math.random() * 1000);
        var date = LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("MM.yy"));
        var strings = date.split("\\.");
        return new Card(approvedCardNum, strings[0], strings[1], holder, String.format("%03d", cvc));
    }

    public static Card getEmptyCardNumber() {

        var cardMonth = generateDate(3, "MM");
        var cardYear = generateDate(3, "yy");
        var holder = fakerEn.name().firstName() + " " + fakerEn.name().lastName();
        var cvc = (int) (Math.random() * 1000);
        return new Card("", cardMonth, cardYear, holder, String.format("%03d", cvc));
    }

    public static Card getEmptyMonthCard() {

        var approvedCardNum = "1111 2222 3333 4444";
        var cardYear = generateDate(3, "yy");
        var holder = fakerEn.name().firstName() + " " + fakerEn.name().lastName();
        var cvc = (int) (Math.random() * 1000);
        return new Card(approvedCardNum, "", cardYear, holder, String.format("%03d", cvc));
    }

    public static Card getEmptyYearCard() {

        var approvedCardNum = "1111 2222 3333 4444";
        var cardMonth = generateDate(3, "MM");
        var holder = fakerEn.name().firstName() + " " + fakerEn.name().lastName();
        var cvc = (int) (Math.random() * 1000);
        return new Card(approvedCardNum, cardMonth, "", holder, String.format("%03d", cvc));
    }

    public static Card getEmptyHolderCard() {

        var approvedCardNum = "1111 2222 3333 4444";
        var cardMonth = generateDate(3, "MM");
        var cardYear = generateDate(3, "yy");
        var cvc = (int) (Math.random() * 1000);
        return new Card(approvedCardNum, cardMonth, cardYear, "", String.format("%03d", cvc));
    }

    public static Card getEmptyCVCCard() {

        var approvedCardNum = "1111 2222 3333 4444";
        var cardMonth = generateDate(3, "MM");
        var cardYear = generateDate(3, "yy");
        var holder = fakerEn.name().firstName() + " " + fakerEn.name().lastName();
        return new Card(approvedCardNum, cardMonth, cardYear, holder, "");
    }

    public static Card getNonexistentMonth() {

        var validCardNum = "1111 2222 3333 4444";
        var invalidCardMonth = "13";
        var cardYear = generateDate(3, "yy");
        var holder = fakerEn.name().firstName() + " " + fakerEn.name().lastName();
        var cvc = (int) (Math.random() * 1000);
        return new Card(validCardNum, invalidCardMonth, cardYear, holder, String.format("%03d", cvc));
    }

    public static Card getInvalidFormatMonth() {

        var validCardNum = "1111 2222 3333 4444";
        var invalidCardMonth = "1";
        var cardYear = generateDate(3, "yy");
        var holder = fakerEn.name().firstName() + " " + fakerEn.name().lastName();
        var cvc = (int) (Math.random() * 1000);
        return new Card(validCardNum, invalidCardMonth, cardYear, holder, String.format("%03d", cvc));
    }

    public static Card getInvalidFormatYear() {

        var validCardNum = "1111 2222 3333 4444";
        var cardMonth = generateDate(3, "MM");
        var cardYear = "1";
        var holder = fakerEn.name().firstName() + " " + fakerEn.name().lastName();
        var cvc = (int) (Math.random() * 1000);
        return new Card(validCardNum, cardMonth, cardYear, holder, String.format("%03d", cvc));
    }
}

