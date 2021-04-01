/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report_galerie.src.ftbb;


import report_galerie.src.ftbb.PostViewController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import report_galerie.src.Entites.Galerie;
import report_galerie.src.Services.ServiceGalerie;

/**
 * FXML Controller class
 *
 * @author narug
 */
public class GalerieviewController implements Initializable {

    @FXML
    private VBox galview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         this.galview.getChildren().clear();
        ServiceGalerie sr = new ServiceGalerie();
           List<Galerie> list = null;
        try {
            list = sr.ShowPhoto();
        } catch (SQLException ex) {
            Logger.getLogger(GalerieviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
            try{
                
                for(Galerie r : list){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/report_galerie/src/ftbb/PostView.fxml"));
                    Node postbox = loader.load();
                    PostViewController pc = loader.getController();
                    pc.setData(r);
                    this.galview.getChildren().add(postbox);
                    
                }
            }catch(IOException e){
                  System.out.println("error");
            }
    }    
    
}
