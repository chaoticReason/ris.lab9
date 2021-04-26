package com.bsuir.stankevich.lab8.myutils;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Checker {
    static String PHONE_REGEX="^\\+(\\d){1,6}\\s?[\\(\\-]?(\\d){1,6}[\\)\\-]?\\s?([\\d\\-])+$";
    static String EMAIL_REGEX="^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$";

    public static Boolean isPhoneNumber(String number) {
        return Pattern.matches(PHONE_REGEX, number);
    }
    public static Boolean isEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }
    public static Boolean containsData(String str){ return str != null && !str.isEmpty();}
    public static Boolean notNull(String str){return str != null; }
    public static Boolean notNull(LocalDate date){return date != null; }
}
