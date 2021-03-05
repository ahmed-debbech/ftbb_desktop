/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Entites.Galerie;
import Services.ServiceGalerie;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author narug
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField tfphototitle;
    @FXML
    private TextField tfphotourl;
    private Label labelaffiche;
    @FXML
    private TextField tfphototitle1;
    @FXML
    private TableView<Galerie> listphoto;
    @FXML
    private Label gld;
    @FXML
    private Button sub;
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addphoto(ActionEvent event) {
            Galerie g =new Galerie();
            g.setPhoto_title(tfphototitle.getText());
            g.setPhoto_url(tfphotourl.getText());
            
       if((tfphototitle.getText() == " ") || (tfphotourl.getText() == " ")){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Please Type!.");
            Optional<ButtonType> result = alert.showAndWait();
            
        }else{
           ServiceGalerie sg=new ServiceGalerie();
           sg.AddPhoto(g);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Note!");
            alert.setHeaderText(null);
            alert.setContentText("Photo added successfully!.");
            Optional<ButtonType> result = alert.showAndWait();
        }
    
    }

    @FXML
    private void deletephoto(ActionEvent event) {
           Galerie g =listphoto.getSelectionModel().getSelectedItem();
          if(g == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Please select a Photo title from the list.");
            Optional<ButtonType> result = alert.showAndWait();
        }else{
           
           ServiceGalerie sg= new ServiceGalerie();
           sg.DeletePhoto(g);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Note!");
            alert.setHeaderText(null);
            alert.setContentText("Photo Deleted Successfully!.");
            Optional<ButtonType> result = alert.showAndWait();
    }
    }
    @FXML
    private void showphoto(ActionEvent event)throws SQLException {
        ServiceGalerie sg=new ServiceGalerie();
        List<Galerie> g = sg.ShowPhoto();
        TableColumn t1 = new TableColumn("Galerie ID ");
        t1.setCellValueFactory(new PropertyValueFactory<Galerie, String>("galerie_id"));
        TableColumn t2 = new TableColumn("Admin ID ");
        t2.setCellValueFactory(new PropertyValueFactory<Galerie, String>("admin_id"));
        TableColumn t3 = new TableColumn("Photo url");
        t3.setCellValueFactory(new PropertyValueFactory<Galerie, String>("photo_url"));
        TableColumn t4 = new TableColumn("Photo title");
        t4.setCellValueFactory(new PropertyValueFactory<Galerie, String>("photo_title"));
        listphoto.getColumns().addAll(t1,t2,t3,t4);
        ObservableList<Galerie> data =FXCollections.observableArrayList(g);
        listphoto.setItems(data);
        
    }
    @FXML
    private void modifyphoto(ActionEvent event) {
        Galerie g =listphoto.getSelectionModel().getSelectedItem();
        if(g == null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Please select a Photo title from the list.");
            Optional<ButtonType> result = alert.showAndWait();
        }else{
        gld.setText(String.valueOf(g.getGalerie_id()));
        tfphototitle1.setText(g.getPhoto_title());
        }
        
    }

    @FXML
    private void ModeSubmit(ActionEvent event) {
        ServiceGalerie sg=new ServiceGalerie();
        Galerie g = new Galerie(Integer.parseInt(gld.getText()),tfphototitle1.getText());
        sg.ModifyPhoto(g);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Note!");
            alert.setHeaderText(null);
            alert.setContentText("Photo title modified successfully.");
            Optional<ButtonType> result = alert.showAndWait();
        
    }
    
}
