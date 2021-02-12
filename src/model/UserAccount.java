package model;

import javafx.scene.image.Image;

public class UserAccount {
    private String userName;
    private Image userIcon;
    private String password;
    private Gender gender;
    private String career;
    private String birthday;
    private String browser;

    public UserAccount(String userName, String password, Image userIcon, Gender gender, String career, String birthday,
            String browser) {
        this.userName = userName;
        this.password = password;
        this.userIcon = userIcon;
        this.gender = gender;
        this.career = career;
        this.birthday = birthday;
        this.browser = browser;
    }

    public Image getUserIcon() {
        return this.userIcon;
    }

    public void setUserIcon(Image userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCareer() {
        return this.career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBrowser() {
        return this.browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

}
