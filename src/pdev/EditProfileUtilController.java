/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdev;

import Entities.Client;
import Service.ServiceClient;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class EditProfileUtilController implements Initializable {

    @FXML
    private ImageView Iprofil;
    @FXML
    private Button BtVal;
    @FXML
    private TextField Tnom;
    @FXML
    private TextField Tprenom;
    @FXML
    private TextField Tnumero;
    @FXML
    private DatePicker Tdate;
        Client c = new Client ();
         
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       c = ServiceClient.getA();
        Tnom.setText(c.getName());
        Tprenom.setText(c.getSurname());
        Tnumero.setText(""+c.getNumber());
        
    }    

    @FXML
    private void ValidationEditProfil(ActionEvent event) throws IOException {
        ServiceClient sc= new ServiceClient();
        c = ServiceClient.getA();
        c.setName(Tnom.getText());
        c.setSurname(Tprenom.getText());
        c.setNumber(Integer.parseInt(Tnumero.getText()));
         LocalDate d = Tdate.getValue();
        System.out.println(d);
       c.setBirthday(Date.valueOf(d));
        sc.UpdateClient(c);
        ServiceClient.setA(c);
         BtVal.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource("ProfileUtil.fxml"));
        
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
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        BtVal.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource("ProfileUtil.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    
}
