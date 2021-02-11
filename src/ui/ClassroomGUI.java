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
        for (int i = 0; i < classroom.getAccounts().size(); i++) {
            UserAccount accounts = classroom.getAccounts().get(i);
            if (txtUserName.getText().equals(accounts.getUserName())
                    && txtPassword.getText().equals(accounts.getPassword())) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accountList.fxml"));
                fxmlLoader.setController(this);
                Parent dash = fxmlLoader.load();
                mainPane.getChildren().setAll(dash);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Pareme bolas");
                alert.setTitle("Error");
                alert.setContentText("Usario o cantra fail");
                alert.showAndWait();
            }
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
            alert.setHeaderText("Pareme bolas");
            alert.setTitle("Warning");
            alert.setContentText("Hay campos vacíos o imagen no cargada o inclusive un genero no seleccionado");
            alert.setHeight(400);
            alert.showAndWait();
        } else if (pswUp.getText().length() <= 6) {
            Alert alert1 = new Alert(AlertType.ERROR);
            alert1.setHeaderText("Pareme bolas");
            alert1.setTitle("Error");
            alert1.setContentText("Contraseña muy debil");
            alert1.showAndWait();
        } else if (!cbSoft.isSelected() & !cbSys.isSelected() & !cbTel.isSelected()) {
            Alert alert1 = new Alert(AlertType.INFORMATION);
            alert1.setHeaderText("No has estudiado");
            alert1.setTitle("Information");
            alert1.setContentText("Para crear una cuenta debe tener al menos una de estas profesiones");
            alert1.setHeight(400);
            alert1.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you ok with this?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("redux and react");
                alert2.setHeaderText("The User " + userUp.getText() + " have been added sucesfully");
                alert2.setContentText("Take it easy bro!");
                alert2.showAndWait();
                classroom.addAccount(userUp.getText(), pswUp.getText(), icon, gender.getSelectedToggle().toString(),
                        getCareers(), dateBirth.getValue().toString(), cbBrowsers.getValue());
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
            careers += cbSoft.getText();
        } else if (cbSys.isSelected()) {
            careers += cbSys.getText();
        } else if (cbTel.isSelected()) {
            careers += cbTel.getText();
        }
        return careers;
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

    @FXML
    void birthdayDate(ActionEvent event) {

    }

    @FXML
    void browsers(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
