/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll_fttb;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author sbs
 */
public class EndedPollController implements Initializable {

    @FXML
    private TextArea polldescription;
    @FXML
    private TextField option1;
    @FXML
    private TextField option2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    polldescription.setEditable(false);
        option1.setEditable(false);
        option2.setEditable(false);
    }    
    
}
