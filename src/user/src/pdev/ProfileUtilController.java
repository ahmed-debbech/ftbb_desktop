/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.src.pdev;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import utils.Passable;
import pidev.AdminhomeController;
import pidev.ClienthomeController;
import user.src.Entities.Client;
import user.src.Service.ServiceClient;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class ProfileUtilController implements Initializable {

    @FXML
    private ImageView Iprofil;
    @FXML
    private Label lnom;
    @FXML
    private Label lprenom;
    @FXML
    private Label lbirth;
    @FXML
    private Label lnumber;
    @FXML
    private Label lsex;
    @FXML
    private Button BtModif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Client a= new Client();
         a= ServiceClient.getA();
         
        lnom.setText(a.getName()); 
        lprenom.setText(a.getSurname());
        lbirth.setText(a.getBirthday().toString()); 
        
        String s=String.valueOf(a.getNumber());
        lnumber.setText(s);
        lsex.setText(a.getSex());
        Image im = null;
        try {
            URL imageUrl = new URL("http://127.0.0.1/uploads/"+ a.getPhoto_url());
            InputStream in = imageUrl.openStream();
            BufferedImage image = ImageIO.read(in);
            im = SwingFXUtils.toFXImage(image, null);
            in.close();
        }catch (IOException ioe) {
           im = new Image("/user/src/img/user.png");
        }
        this.Iprofil.setImage(im);
    }    

    @FXML
    private void Updateinfo(ActionEvent event) throws IOException {
             
        Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/user/src/pdev/EditProfileUtil.fxml"));
            AnchorPane linker  = (AnchorPane) Passable.getInstance().getContainer();
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(AdminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
   
    }

    @FXML
    private void deconnexion(ActionEvent event) throws IOException {
    BtModif.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource("/pidev/Login.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    
}
