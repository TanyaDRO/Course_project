package data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
    public static Card getApprovedCard() {
        return new Card("1111 2222 3333 4444", "08", "22", "Card Holder", "999");
    }

    public static Card getDeclinedCard() {
        return new Card("5555 6666 7777 8888", "08", "22", "Card Holder", "999");
    }

    public static Card getInvalidNumberCard() {
        return new Card("1111 2222 3333 44", "08", "22", "Card Holder", "999");
    }

    public static Card getInvalidMonth() {
        return new Card("1111 2222 3333 4444", "00", "22", "Card Holder", "999");
    }

    public static Card getFakeCard() {
        return new Card("1111 1111 1111 1111", "08", "22", "Card Holder", "999");
    }

    public static Card getInvalidOwnerCard() {
        return new Card("1111 2222 3333 4444", "08", "22", "123456789Йцукенгшщзхъ!\"№;%:?*()123456789Йцукенгшщзхъ!\"№;%:?*()", "999");
    }

    public static Card getInvalidCvcCard() {
        return new Card("1111 2222 3333 4444", "08", "22", "Card Holder", "99");
    }

    public static Card getExpiredCard() {

        var date = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM.yy"));
        var strings = date.split("\\.");
        return new Card("1111 2222 3333 4444", strings[0], strings[1], "Card Holder", "999");
    }
    public static Card getWrongDateCard() {

        var date = LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("MM.yy"));
        var strings = date.split("\\.");
        return new Card("1111 2222 3333 4444", strings[0], strings[1], "Card Holder", "999");
    }
}
