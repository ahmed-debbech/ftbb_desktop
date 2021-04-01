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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import user.src.Entities.Client;
import user.src.Entities.Password;
import user.src.Service.ServiceAdmin;
import user.src.Service.ServiceClient;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class InscriptionClientController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfnumber;
    @FXML
    private RadioButton Rmale;
    @FXML
    private ToggleGroup sexe;
    @FXML
    private RadioButton Rfemme;
    @FXML
    private DatePicker tdate;
    @FXML
    private Button Btretour;
    @FXML
    private PasswordField tfpass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Inscription(ActionEvent event) throws IOException {
           Client cl = new Client();
             boolean a=Utilities.validationEmail(tfemail.getText());        
        if (a=false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("votre email n'est pas valide");
            Optional<ButtonType> result = alert.showAndWait();
        }  else if (tfnom.getText().isEmpty()) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("votre nom n'est pas valide");
            Optional<ButtonType> result = alert.showAndWait();  
        }   else if (tfprenom.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("votre prenom n'est pas valide");
            Optional<ButtonType> result = alert.showAndWait(); 
            
        }   else if (tfpass.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("votre password n'est pas valide");
            Optional<ButtonType> result = alert.showAndWait(); 
        } else { if ((!Rmale.isSelected())&&(!Rfemme.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Selectionner le sexe ");
            Optional<ButtonType> result = alert.showAndWait(); }
        else {
        ServiceClient sc = new ServiceClient();
        ServiceAdmin sa = new ServiceAdmin();
        Password pass =new Password ();
        pass.setPwd(tfpass.getText());
        sa.AddPasswordAdmin(pass);
        cl.setPassword_id(pass.getPassword_id());
        
        cl.setName(tfnom.getText());
        cl.setSurname(tfprenom.getText());
        cl.setEmail(tfemail.getText());
        
        LocalDate d = tdate.getValue();
        System.out.println(d);
       cl.setBirthday(Date.valueOf(d));//**********
        int nb = Integer.parseInt(tfnumber.getText());
        cl.setNumber(nb);
        if (Rmale.isSelected()) {

            cl.setSex("Male");

        } else {
            if (Rfemme.isSelected()) {
                cl.setSex("Femme");
            }

        }
        
       
        System.out.println(cl.toString());
        sc.AddClient(cl);
         Btretour.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource("Cnx.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes!");
            alert.setHeaderText(null);
            alert.setContentText("Inscription validée");
            Optional<ButtonType> result = alert.showAndWait(); 
    }}}

    @FXML
    private void RetourCnx(ActionEvent event) throws IOException {
         Btretour.getScene().getWindow().hide();      
     
        Parent root = FXMLLoader.load(getClass().getResource("/user/src/pdev/Cnx.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    
}
