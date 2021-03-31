/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Utils.Utilities;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author narug
 */
public class RespondfeedbackController implements Initializable {

    @FXML
    private TextArea tfdesc;
    @FXML
    private Label clem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void pass(String x){
        clem.setText(x);
    
    }
   
    
    @FXML
    private void respondfeedback(ActionEvent event) {
        Utilities.sendMail("x", this.clem.getText(), this.tfdesc.getText());
        
    }
    
}
