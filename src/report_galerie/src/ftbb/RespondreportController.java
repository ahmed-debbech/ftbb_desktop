/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report_galerie.src.ftbb;


//import java.net.Authenticator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import report_galerie.src.Utils.Utilities;
import utils.Passable;


/**
 * FXML Controller class
 *
 * @author narug
 */
public class RespondreportController implements Initializable {

    @FXML
    private Label tfce;
    @FXML
    private TextArea tfan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         tfce.setText(Passable.getInstance().getTextData());
    }    
    
     public void pass(String x){
       
    
    }

        

    @FXML
    private void respondreport(ActionEvent event) {
       Utilities.sendMail( this.tfce.getText(), this.tfan.getText());
    }
    
}
