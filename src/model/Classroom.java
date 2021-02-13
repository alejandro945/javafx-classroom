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

    public boolean validateUser(String userName, String password) {
        boolean already = false;
        for (int i = 0; i < getAccounts().size() && !already; i++) {
            UserAccount accounts = getAccounts().get(i);
            if (userName.equals(accounts.getUserName()) & password.equals(accounts.getPassword())) {
                already = true;
            }
        }
        return already;
    }

    public UserAccount getUser(String userName, String password) {
        boolean found = false;
        UserAccount user = null;
        for (int i = 0; i < getAccounts().size() && !found; i++) {
            if (userName.equals(getAccounts().get(i).getUserName())
                    & password.equals(getAccounts().get(i).getPassword())) {
                found = true;
                user = getAccounts().get(i);
            }
        }
        return user;
    }
}
