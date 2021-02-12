package ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import model.*;

public class ClassroomGUI implements Initializable {
    private Classroom classroom;

    private Image icon;

    public ClassroomGUI(Classroom cr) {
        this.classroom = cr;
    }

    @FXML
    private Pane mainPane;

    @FXML
    private TextField userUp;

    @FXML
    private PasswordField pswUp;

    @FXML
    private TextField urlBrowse;

    @FXML
    private ComboBox<String> cbBrowsers;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton maleRb;

    @FXML
    private RadioButton femaleRb;

    @FXML
    private RadioButton otherRb;

    @FXML
    private CheckBox cbSoft;

    @FXML
    private CheckBox cbSys;

    @FXML
    private CheckBox cbTel;

    @FXML
    private DatePicker dateBirth;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblnameUser;

    @FXML
    private ImageView userIcon;

    @FXML
    private TableView<UserAccount> tbAccounts;

    @FXML
    private TableColumn<UserAccount, String> tcUserName;

    @FXML
    private TableColumn<UserAccount, Gender> tcGender;

    @FXML
    private TableColumn<UserAccount, String> tcCareer;

    @FXML
    private TableColumn<UserAccount, String> tcBirthday;

    @FXML
    private TableColumn<UserAccount, String> tcBrowser;

    public void welcomeToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        fxmlLoader.setController(this);
        Parent login = fxmlLoader.load();
        mainPane.getChildren().setAll(login);
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        fxmlLoader.setController(this);
        Parent login = fxmlLoader.load();
        mainPane.getChildren().setAll(login);
    }

    @FXML
    void singIn(ActionEvent event) throws IOException {
        boolean found = false;
        if (classroom.getAccounts().size() != 0) {
            for (int i = 0; i < classroom.getAccounts().size() && !found; i++) {
                UserAccount accounts = classroom.getAccounts().get(i);
                if (txtUserName.getText().equals(accounts.getUserName())
                        & txtPassword.getText().equals(accounts.getPassword())) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accountList.fxml"));
                    fxmlLoader.setController(this);
                    Parent dash = fxmlLoader.load();
                    mainPane.getChildren().setAll(dash);
                    initTable();
                    lblnameUser.setText(accounts.getUserName());
                    userIcon.setImage(accounts.getUserIcon());
                    found = true;
                }
            }
            if (!found) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Listen carefully and beware");
                alert.setTitle("Error from Alejandro Varela App");
                alert.setContentText("Wrong username or password");
                alert.showAndWait();
            }
        } else {
            Alert alert1 = new Alert(AlertType.WARNING);
            alert1.setHeaderText("Listen carefully and beware");
            alert1.setTitle("WARNING from Alejandro Varela App");
            alert1.setContentText("There are not users already!");
            alert1.showAndWait();
        }
    }

    @FXML
    void singUp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
        fxmlLoader.setController(this);
        Parent registerForm = fxmlLoader.load();
        mainPane.getChildren().setAll(registerForm);
        getComboItems();
    }

    public void getComboItems() {
        ObservableList<String> browserList = FXCollections.observableArrayList("", "Chrome", "Mozilla", "Safari",
                "Opera", "Other");
        cbBrowsers.setValue("");
        cbBrowsers.setItems(browserList);
    }

    @FXML
    public void backToSingIn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        fxmlLoader.setController(this);
        Parent login = fxmlLoader.load();
        mainPane.getChildren().setAll(login);
    }

    @FXML
    public void createAccount(ActionEvent event) {
        if (userUp.getText().equals("") || pswUp.getText().equals("")
                || urlBrowse.getText().equals("Path does not exist") || gender.getSelectedToggle() == null
                || dateBirth.getValue() == null || cbBrowsers.getValue().equals("") || urlBrowse.getText().equals("")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText("Look out and take care");
            alert.setTitle("Warning message");
            alert.setContentText("There are empty fields or an image not loaded or even a genre not selected");
            alert.setHeight(400);
            alert.showAndWait();
        } else if (pswUp.getText().length() <= 6) {
            Alert alert1 = new Alert(AlertType.ERROR);
            alert1.setHeaderText("Listen carefully and beware");
            alert1.setTitle("Error Message");
            alert1.setContentText("Very weak password must be greater than 6 characters");
            alert1.showAndWait();
        } else if (!cbSoft.isSelected() & !cbSys.isSelected() & !cbTel.isSelected()) {
            Alert alert1 = new Alert(AlertType.INFORMATION);
            alert1.setHeaderText("Have you not studied?");
            alert1.setTitle("Information, Please Pay attention");
            alert1.setContentText("To create an account you must have at least one of these professions");
            alert1.setHeight(400);
            alert1.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText("Look, Consider the following");
            alert.setContentText("Are you sure to save this user?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Redux and React");
                alert2.setHeaderText("The User " + userUp.getText() + " have been added sucesfully");
                alert2.setContentText("Take it easy bro!");
                alert2.showAndWait();
                maleRb = (RadioButton) gender.getSelectedToggle();
                classroom.addAccount(userUp.getText(), pswUp.getText(), icon, maleRb.getText(), getCareers(),
                        dateBirth.getValue().toString(), cbBrowsers.getValue());
            }
            userUp.setText("");
            pswUp.setText("");
            urlBrowse.setText("");
            icon = null;
            maleRb.setSelected(false);
            femaleRb.setSelected(false);
            otherRb.setSelected(false);
            cbSoft.setSelected(false);
            cbSys.setSelected(false);
            cbTel.setSelected(false);
            dateBirth.setValue(null);
            cbBrowsers.setValue("");
        }
    }

    @FXML
    public void fileChooser(ActionEvent event) {
        Image userIcon = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            urlBrowse.setText(selectedFile.getPath());
            userIcon = new Image(selectedFile.toURI().toString());
            setUserIcon(userIcon);
        } else {
            urlBrowse.setText("Path does not exist");
        }
    }

    public void setUserIcon(Image userIcon) {
        this.icon = userIcon;
    }

    public String getCareers() {
        String careers = "";
        if (cbSoft.isSelected()) {
            careers += " " + cbSoft.getText();
        }
        if (cbSys.isSelected()) {
            careers += " " + cbSys.getText();
        }
        if (cbTel.isSelected()) {
            careers += " " + cbTel.getText();
        }
        return careers;
    }

    private void initTable() {
        ObservableList<UserAccount> userAccount = FXCollections.observableArrayList(classroom.getAccounts());
        tbAccounts.setItems(userAccount);
        tcUserName.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("userName"));
        tcGender.setCellValueFactory(new PropertyValueFactory<UserAccount, Gender>("gender"));
        tcCareer.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("career"));
        tcBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("birthday"));
        tcBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("browser"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
