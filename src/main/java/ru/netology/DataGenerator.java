package ru.netology;

import com.github.javafaker.Faker;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String dataGen(int plusDays) {
        LocalDate todayPlus = LocalDate.now();
        return todayPlus.plusDays(plusDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String nameGen(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().name();
    }

    public static String nameGenUS() {
        Faker faker = new Faker(new Locale("usa"));
        return faker.name().name();
    }

    public static String nameGenSingle(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().firstName();
    }

    public static String phoneGen(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static String cityGen(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.address().city();
    }

    public static String cityGenUS() {
        Faker faker = new Faker((new Locale("usa")));
        return faker.address().city();
    }

    public static String cityGenUA() {
        Faker faker = new Faker((new Locale("uk_UA")));
        return faker.address().city();
    }

    public static class Registration {
        private Registration() {
        }

        public static User userReg(String locale) {
            return new User(
                    nameGen(locale),
                    phoneGen(locale),
                    cityGen(locale)
            );
        }
    }

    @Data
    public static class User {
        private final String name;
        private final String phone;
        private final String city;
    }

}
