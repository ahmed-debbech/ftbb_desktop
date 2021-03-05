/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdev;

import Entities.Admin;
import Entities.Password;
import Service.ServiceAdmin;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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
    private Label laff;
    @FXML
    private TextField tidadmin;
    @FXML
    private TextField tidadModif;
    @FXML
    private TextField tNvNom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void TajoutAdmin(ActionEvent event) {
        Admin ad = new Admin();
        ServiceAdmin sa = new ServiceAdmin();
        Password pass =new Password ();
        pass.setPwd(tfpass.getText());
        sa.AddPasswordAdmin(pass);
        ad.setPassword_id(pass.getPassword_id());
        
        ad.setName(tfnom.getText());
        ad.setSurname(tfprenom.getText());
        ad.setEmail(tfemail.getText());

        ad.setBirthday(Date.valueOf(tdate.getValue()));
        int nb = Integer.parseInt(tfnumber.getText());
        ad.setNumber(nb);
        if (Rmale.isSelected()) {

            ad.setSex("Male");

        } else {
            if (Rfemme.isSelected()) {
                ad.setSex("Femme");
            }

        }
        int rol=0;
        if (Radmin.isSelected()) {

            rol=1;

        } else {
            if (Rediteur.isSelected()) {
                rol=2;
            } else {
                if (Rstore.isSelected()) {
                    rol=3;
                }
            }
        }
        ad.setRole(rol);
        System.out.println(ad.toString());
        sa.AddAdmin(ad);
    }

    @FXML
    private void TAfficherAdmin(ActionEvent event) {
        ServiceAdmin sp = new ServiceAdmin();
        
          laff.setText(sp.AffichierAdmin().toString());
    }

    @FXML
    private void DeleteAdmin(ActionEvent event) {
        ServiceAdmin sp = new ServiceAdmin();
        Admin a =new Admin();
        a.setId(Integer.parseInt(tidadmin.getText()));
        sp.DeleteAdmin(a);
    }

    @FXML
    private void Modifname(ActionEvent event) {
        ServiceAdmin sp = new ServiceAdmin();
        Admin a =new Admin();
        a=sp.SelectAdmin(Integer.parseInt(tidadModif.getText()));
        a.setName(tNvNom.getText());
        sp.UpdateAdmin(a);
    }

}
