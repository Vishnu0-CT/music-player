package org.example;
import java.time.*;

public class Date {
    static LocalDate day = LocalDate.now();

    static void dateSetter(String date) {
        day = LocalDate.parse(date);
    }

    static LocalDate dateGetter() {
        return day;
    }
}
