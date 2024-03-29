/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.src.pdev;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import utils.Passable;
import pidev.AdminhomeController;
import user.src.Entities.Admin;
import user.src.Service.ServiceAdmin;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class ProfileController implements Initializable {

    @FXML
    private ImageView Iprofil;
    @FXML
    private Label lnom;
    @FXML
    private Label lprenom;
    @FXML
    private Label lbirth;
    @FXML
    private Button BAjout;
    @FXML
    private Label lnumber;
    @FXML
    private Label lsex;
    @FXML
    private Label lrole;

   Admin a =new Admin ();

   
    


   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  Admin a= new Admin();
  a= ServiceAdmin.getA();
         
        lnom.setText(a.getName()); 
        lprenom.setText(a.getSurname());
        
        lbirth.setText(a.getBirthday().toString()); 

        String s=String.valueOf(a.getNumber());
        lnumber.setText(s);
        lsex.setText(a.getSex());
    

    int lrole= a.getRole();
        if (lrole==1){
            this.lrole.setText("Admin");
        }
        else {  if (lrole==2){
            this.lrole.setText("Store Manager");
        }
            else {  if (lrole==3){
            this.lrole.setText("Editeur");
        }} 
        
    }
    }    

    @FXML
    private void Updateinfo(ActionEvent event) {

    }

  

    @FXML
    private void BajoutAdmin(ActionEvent event) throws IOException {
        Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/user/src/pdev/AjoutAdmin.fxml"));
            AnchorPane root = (AnchorPane) Passable.getInstance().getContainer();
                System.out.println("node " + root.toString());
            root.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(AdminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void GestionUtil(ActionEvent event) throws IOException {
        Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/user/src/pdev/GestionUtilisateur.fxml"));
            AnchorPane root = (AnchorPane) Passable.getInstance().getContainer();
                System.out.println("node " + root.toString());
            root.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(AdminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void deconnexion(ActionEvent event) throws IOException {
        BAjout.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource("/pidev/Login.fxml"));
                

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    
}
