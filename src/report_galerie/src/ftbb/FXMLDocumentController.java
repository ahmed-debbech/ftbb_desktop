/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report_galerie.src.ftbb;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle; 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import news.src.utils.Utilities;
import report_galerie.src.Entites.Galerie;
import report_galerie.src.Services.ServiceGalerie;

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
    @FXML
    private TextArea tfdesc;
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn t1 = new TableColumn("Galerie ID ");
        t1.setCellValueFactory(new PropertyValueFactory<Galerie, String>("galerie_id"));
        TableColumn t2 = new TableColumn("Admin ID ");
        t2.setCellValueFactory(new PropertyValueFactory<Galerie, String>("admin_id"));
        TableColumn t3 = new TableColumn("Photo url");
        t3.setCellValueFactory(new PropertyValueFactory<Galerie, String>("photo_url"));
        TableColumn t4 = new TableColumn("Photo title");
        t4.setCellValueFactory(new PropertyValueFactory<Galerie, String>("photo_title"));
        TableColumn t5 = new TableColumn("Description");
        t5.setCellValueFactory(new PropertyValueFactory<Galerie, String>("description"));
        listphoto.getColumns().addAll(t1,t2,t3,t4,t5);
    }    

    @FXML
    private void addphoto(ActionEvent event) {
            Galerie g =new Galerie();
            String dir = Utilities.pathToUrl(this.tfphotourl.getText());
            g.setPhoto_title(tfphototitle.getText());
            g.setPhoto_url(dir);
            g.setDescription(tfdesc.getText());
            
       if((tfphototitle.getText().equals("")) || (tfphotourl.getText().equals("")) ||(tfdesc.getText().equals("")) ){
           
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
        tfdesc.setText(g.getDescription());
        }
        
    }

    @FXML
    private void ModeSubmit(ActionEvent event) {
        ServiceGalerie sg=new ServiceGalerie();
        Galerie g = new Galerie(Integer.parseInt(gld.getText()),tfphototitle1.getText(),tfdesc.getText());
        sg.ModifyPhoto(g);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Note!");
            alert.setHeaderText(null);
            alert.setContentText("Photo  modified successfully.");
            Optional<ButtonType> result = alert.showAndWait();
        
    }

    @FXML
    private void Browse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a banner file!");
        fileChooser.setInitialDirectory(new File("C:\\xampp\\htdocs\\uploads"));
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file = fileChooser.showOpenDialog(stage);
        try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                this.tfphotourl.setText(file.getAbsolutePath());
            } catch (IOException ex) {
                System.out.println("could not get the image");
            }
    }
    
    
}
