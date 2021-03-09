/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author narug
 */
public class RespondreportController implements Initializable {

    @FXML
    private TextField tfce;
    @FXML
    private TextArea tfan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void pass(String x){
        tfce.setText(x);
    
    }

    @FXML
    private void respondreport(ActionEvent event) {
        
    }
    
}
