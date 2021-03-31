/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.src.pdev;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import user.src.Entities.Client;
import user.src.Service.ServiceClient;
import user.src.Utils.ImageUtils;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class EditProfileUtilController implements Initializable {

    @FXML
    private ImageView Iprofil;
    @FXML
    private Button BtVal;
    @FXML
    private TextField Tnom;
    @FXML
    private TextField Tprenom;
    @FXML
    private TextField Tnumero;
    @FXML
    private DatePicker Tdate;
        Client c = new Client ();
    @FXML
    private TextField tpath;
         
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       c = ServiceClient.getA();
        Tnom.setText(c.getName());
        Tprenom.setText(c.getSurname());
        Tnumero.setText(""+c.getNumber());
        Tdate.setValue(c.getBirthday().toLocalDate()); /// a editer //
        tpath.setText(c.getPhoto_url());
        //Image img = new Image("C:\\xampp\\htdocs\\ProfileImg\\22781996.JPG");
       // this.Iprofil.setImage(img);
        //Iprofil.setImage(img);
        Image im = null;
        try {
            URL imageUrl = new URL("http://127.0.0.1/uploads/"+ c.getPhoto_url());
            InputStream in = imageUrl.openStream();
            BufferedImage image = ImageIO.read(in);
            im = SwingFXUtils.toFXImage(image, null);
            in.close();
        }catch (IOException ioe) {
           im = new Image("img/user.png");
        }
        this.Iprofil.setImage(im);
    }    

    @FXML
    private void ValidationEditProfil(ActionEvent event) throws IOException {
        ServiceClient sc= new ServiceClient();
        c = ServiceClient.getA();
        c.setName(Tnom.getText());
        c.setSurname(Tprenom.getText());
        c.setNumber(Integer.parseInt(Tnumero.getText()));
         LocalDate d = Tdate.getValue();
        System.out.println(d);
       c.setBirthday(Date.valueOf(d));
       
        c.setPhoto_url(ImageUtils.CopyfileClient(tpath.getText(), c));
        sc.UpdateClient(c);
        ServiceClient.setA(c);
         BtVal.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource("/user/src/pdev/ProfileUtil.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes!");
            alert.setHeaderText(null);
            alert.setContentText("Modification validée");
            Optional<ButtonType> result = alert.showAndWait(); 
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        BtVal.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource("/user/src/pdev/ProfileUtil.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    private void path(ActionEvent event) {
        Stage stage = new Stage ();
        FileChooser fil_chooser = new FileChooser();
         File file = fil_chooser.showOpenDialog(stage);
  
                if (file != null) {
                      
                    tpath.setText(file.getAbsolutePath());
                }
               
    }
    
}
