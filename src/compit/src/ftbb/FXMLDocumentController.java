/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compit.src.ftbb;


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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pidev.AdminhomeController;
import utils.Passable;

/**
 *
 * @author Lenovo
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    
    @FXML
    private Button btStatistics;
    @FXML
    private Button btCompetition;
    @FXML
    private Button btTeam;
    @FXML
    private Button btPlayer;
    @FXML
    private AnchorPane mainPane;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

//    private void AddCompetition(ActionEvent event) {
//        
//        ServiceCompetition sp = new ServiceCompetition ();
//        Competition c = new Competition ();
//        c.setName(ftName.getText());
//        
//        sp.AddCompetition(c);
//    }

    @FXML
    private void ShowStatistics(ActionEvent event) {
    }

    @FXML
    private void ManageCompetition (ActionEvent event) throws IOException  {
         
        // URL url1 = new File("C:\\Users\\Lenovo\\Documents\\ftbb\\src\\view\\competition.fxml").toURI().toURL();
       // Parent root1 =  FXMLLoader.load(url1);
       // Stage stage1 = new Stage ();
        //stage1.setScene(new Scene(root1));
        //stage1.setTitle("Competition");
        //stage1.show();
        
        
        Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/compit/src/view/competition.fxml"));
            AnchorPane linker = (AnchorPane) Passable.getInstance().getContainer();
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(AdminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
               
    }

    @FXML
    private void ListTeam(ActionEvent event) throws IOException {
       Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/compit/src/view/team.fxml"));
            AnchorPane linker = (AnchorPane) Passable.getInstance().getContainer();
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(AdminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void ListPlayer(ActionEvent event) {
    }
    
}
