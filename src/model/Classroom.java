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
        Career careerEnum = null;
        switch (gender) {
            case "male":
                genderEnum = Gender.MALE;
                break;
            case "female":
                genderEnum = Gender.FEMALE;
                break;
            case "other":
                genderEnum = Gender.OTHER;
                break;
        }
        switch (gender) {
            case "Software Engineering":
                careerEnum = Career.SOFTWARE_ENGINEERING;
                break;
            case "Telematic Engineering":
                careerEnum = Career.SYSTEM_ENGINEERING;
                break;
            case "System Engineering":
                careerEnum = Career.TELEMATIC_ENGINEERING;
                break;
        }
        accounts.add(new UserAccount(userName, password, userIcon, genderEnum, careerEnum, birthday, browser));
    }

    public List<UserAccount> getAccounts() {
        return accounts;
    }

}
