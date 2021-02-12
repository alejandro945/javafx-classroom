package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Classroom {
    private List<UserAccount> accounts;

    public Classroom() {
        accounts = new ArrayList<UserAccount>();
    }

    public void addAccount(String userName, String password, Image userIcon, String gender, String career,
            String birthday, String browser) {
        Gender genderEnum = null;
        if (gender.equalsIgnoreCase("male")) {
            genderEnum = Gender.MALE;
        } else if (gender.equalsIgnoreCase("female")) {
            genderEnum = Gender.FEMALE;
        } else if (gender.equalsIgnoreCase("other")) {
            genderEnum = Gender.OTHER;
        }
        accounts.add(new UserAccount(userName, password, userIcon, genderEnum, career, birthday, browser));
    }

    public List<UserAccount> getAccounts() {
        return accounts;
    }

}
