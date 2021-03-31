/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.src.pdev;

import user.src.Utils.Utilities;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.mail.Service;
import user.src.Entities.Admin;
import user.src.Entities.Password;
import user.src.Service.ServiceAdmin;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class AjoutAdminController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;

    @FXML
    private TextField tfnumber;
    @FXML
    private ToggleGroup sexe;
    @FXML
    private DatePicker tdate;
    @FXML
    private ToggleGroup role;
    @FXML
    private RadioButton Rmale;
    @FXML
    private RadioButton Rfemme;
    @FXML
    private RadioButton Radmin;
    @FXML
    private RadioButton Rediteur;
    @FXML
    private RadioButton Rstore;
    @FXML
    private PasswordField tfpass;
    @FXML
    private TableColumn<Admin, String> col_id;
    @FXML
    private TableColumn<Admin, String> col_name;
    @FXML
    private TableColumn<Admin, String> col_surname;
    @FXML
    private TableColumn<Admin, String> col_email;
    @FXML
    private TableColumn<Admin, String> col_numero;
    @FXML
    private TableColumn<Admin, String> col_sex;
    @FXML
    private TableColumn<Admin, String> col_role;
    @FXML
    private TableView<Admin> table_ad;
    @FXML
    private Button btretour;
    @FXML
    private TableColumn<Admin,String> col_date_Birth;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceAdmin sp = new ServiceAdmin();
        List<Admin> a = sp.AffichierAdmin();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        col_numero.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_date_Birth.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        table_ad.setItems(sp.getData());
    }

    @FXML
    private void TajoutAdmin(ActionEvent event) {
        Admin ad = new Admin();
        boolean a = Utilities.validationEmail(tfemail.getText());
        if (a = false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("votre email n'est pas valide");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (tfnom.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("votre nom n'est pas valide");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (tfprenom.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("votre prenom n'est pas valide");
            Optional<ButtonType> result = alert.showAndWait();

        } else if (tfpass.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("votre password n'est pas valide");
            Optional<ButtonType> result = alert.showAndWait();
        } else if ((!Rmale.isSelected()) && (!Rfemme.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Selectionner le sexe ");
            Optional<ButtonType> result = alert.showAndWait();
        } else if ((!Radmin.isSelected()) && (!Rediteur.isSelected()) && (!Rstore.isSelected())) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Selectionner le role ");
            Optional<ButtonType> result = alert.showAndWait();
        } //         else if (tdate.isPressed()){
        //           
        //            Alert alert = new Alert(Alert.AlertType.ERROR);
        //            alert.setTitle("WARNING!");
        //            alert.setHeaderText(null);
        //            alert.setContentText("Selectionner date de naissance ");
        //            Optional<ButtonType> result = alert.showAndWait(); 
        //        }
        else {

            ServiceAdmin sa = new ServiceAdmin();
            Password pass = new Password();
            pass.setPwd(tfpass.getText());
            sa.AddPasswordAdmin(pass);
            ad.setPassword_id(pass.getPassword_id());

            ad.setName(tfnom.getText());
            ad.setSurname(tfprenom.getText());
            ad.setEmail(tfemail.getText());
            LocalDate d = tdate.getValue();
            System.out.println(d);
            ad.setBirthday(Date.valueOf(d));

            int nb = Integer.parseInt(tfnumber.getText());
            ad.setNumber(nb);
            if (Rmale.isSelected()) {

                ad.setSex("Male");

            } else {
                if (Rfemme.isSelected()) {
                    ad.setSex("Femme");
                }

            }
            int rol = 0;
            if (Radmin.isSelected()) {

                rol = 1;

            } else {
                if (Rediteur.isSelected()) {
                    rol = 2;
                } else {
                    if (Rstore.isSelected()) {
                        rol = 3;
                    }
                }
            }
            ad.setRole(rol);
            System.out.println(ad.toString());
            sa.AddAdmin(ad);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes!");
            alert.setHeaderText(null);
            alert.setContentText("Ajout terminer");
            Optional<ButtonType> result = alert.showAndWait();

        }
        ServiceAdmin sp = new ServiceAdmin();
        List<Admin> v = sp.AffichierAdmin();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        col_numero.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_date_Birth.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        table_ad.setItems(sp.getData());
    }

    @FXML
    private void DeleteAdmin(ActionEvent event) {
        ServiceAdmin sp = new ServiceAdmin();
        Admin b = new Admin();
        b.setId(table_ad.getSelectionModel().getSelectedItem().getId());
        b= sp.SelectAdmin(b.getId());
        sp.DeleteAdmin(b);

        List<Admin> a = sp.AffichierAdmin();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        col_numero.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_date_Birth.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        table_ad.setItems(sp.getData());
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        btretour.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("/user/src/pdev/Profile.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

}
