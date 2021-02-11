package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Classroom {
    private List<UserAccount> accounts;

    public Classroom() {
        accounts = new ArrayList<UserAccount>();
    }

    public void addAccount(String userName, String password, Image userIcon, Gender gender, Career career,
            String birthday, String browser) {
        accounts.add(new UserAccount(userName, password, userIcon, gender, career, birthday, browser));
    }

    public List<UserAccount> getAccounts() {
        return accounts;
    }
}
