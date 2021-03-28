/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdev;

import Entities.Admin;
import Entities.Client;
import Service.ServiceAdmin;
import Service.ServiceClient;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class GestionUtilisateurController implements Initializable {

    @FXML
    private TableView<Client> table_ad;
    @FXML
    private TableColumn<Client, String> col_id;
    @FXML
    private TableColumn<Client, String> col_name;
    @FXML
    private TableColumn<Client, String> col_surname;
    @FXML
    private TableColumn<Client, String> col_email;
    @FXML
    private TableColumn<Client, String> col_numero;
    @FXML
    private TableColumn<Client, String> col_sex;
    @FXML
    private TableColumn<Client, String> col_date_c;
    @FXML
    private TableColumn<Client, String> col_status;
    @FXML
    private Button btretour;
    @FXML
    private TableColumn<Client, String> col_date_Birth;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ServiceClient sc =new  ServiceClient();
          List <Client> a =sc.AffichierClient();
        
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_numero.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_date_c.setCellValueFactory(new PropertyValueFactory<>("creation_date"));
        col_date_Birth.setCellValueFactory(new PropertyValueFactory<>("birthday"));        
        table_ad.setItems(sc.getData());
    }    

    @FXML
    private void BanUser(ActionEvent event) {
        ServiceClient sc =new  ServiceClient();
        ServiceAdmin  sa= new ServiceAdmin();
          
        Client b= new Client() ;
         b.setId(table_ad.getSelectionModel().getSelectedItem().getId());
         sa.BanClient(b.getId());
         List <Client> a =sc.AffichierClient();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_numero.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_date_c.setCellValueFactory(new PropertyValueFactory<>("creation_date"));
        col_date_Birth.setCellValueFactory(new PropertyValueFactory<>("birthday"));        
        table_ad.setItems(sc.getData());
        
    }

    @FXML
    private void unBanUser(ActionEvent event) {
        ServiceClient sc =new  ServiceClient();
        ServiceAdmin  sa= new ServiceAdmin();
       
        Client b= new Client() ;
         b.setId(table_ad.getSelectionModel().getSelectedItem().getId());
         sa.UnBanClient(b.getId());
         List <Client> a =sc.AffichierClient();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_numero.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_date_c.setCellValueFactory(new PropertyValueFactory<>("creation_date"));
        col_date_Birth.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        
        table_ad.setItems(sc.getData());
        
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        btretour.getScene().getWindow().hide();

        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    
}
