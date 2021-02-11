package ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import model.Classroom;

public class ClassroomGUI implements Initializable {
    private Classroom classroom;

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
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblnameUser;

    @FXML
    private ImageView userIcon;

    @FXML
    private TableView<?> tbAccounts;

    @FXML
    private TableColumn<?, ?> tcId;

    @FXML
    private TableColumn<?, ?> tcUserName;

    @FXML
    private TableColumn<?, ?> tcGender;

    @FXML
    private TableColumn<?, ?> tcCareer;

    @FXML
    private TableColumn<?, ?> tcBirthday;

    @FXML
    private TableColumn<?, ?> tcBrowser;

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accountList.fxml"));
        fxmlLoader.setController(this);
        Parent dash = fxmlLoader.load();
        mainPane.getChildren().setAll(dash);
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
        ObservableList<String> browserList = FXCollections.observableArrayList("Chrome", "Mozilla", "Safari", "Opera",
                "Other");
        cbBrowsers.setValue("Chrome");
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
    void birthdayDate(ActionEvent event) {

    }

    @FXML
    void browsers(ActionEvent event) {

    }

    @FXML
    void createAccount(ActionEvent event) {

    }

    @FXML
    public void fileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            urlBrowse.setText(selectedFile.getPath());
        } else {
            urlBrowse.setText("Path does not exist");
        }

    }

    @FXML
    void rdFemale(ActionEvent event) {

    }

    @FXML
    void rdMale(ActionEvent event) {

    }

    @FXML
    void rdOther(ActionEvent event) {

    }

    @FXML
    void sftEgn(ActionEvent event) {

    }

    @FXML
    void sysEgn(ActionEvent event) {

    }

    @FXML
    void telEgn(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
