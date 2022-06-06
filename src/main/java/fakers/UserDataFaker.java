package fakers;

import com.github.javafaker.Faker;

import java.util.Locale;

public class UserDataFaker {
    Faker faker = new Faker(new Locale("es"));

    public String firstName() {
        return faker.name().firstName();
    }

    public String password(int minimumLength, int maximumLength, boolean includeUppercase, boolean includeSpecial) {
        if (includeSpecial) {
            char[] password = faker.lorem().characters(minimumLength, maximumLength, includeUppercase).toCharArray();
            char[] special = new char[]{'!', '@', '#', '$', '%', '^', '&', '*'};
            for (int i = 0; i < faker.random().nextInt(minimumLength); i++) {
                password[faker.random().nextInt(password.length)] = special[faker.random().nextInt(special.length)];
            }
            return new String(password);
        } else {
            return faker.lorem().characters(minimumLength, maximumLength, includeUppercase);
        }
    }

    public String emailAddress() {
        return faker.internet().emailAddress();
    }
}

