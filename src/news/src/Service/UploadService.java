/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.src.Service;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Ahmed
 */
public class UploadService {
    
    public static void uploadImage(String path){
           String attachmentName = "30";
            String attachmentFileName = "30.jpg";
            String crlf = "\r\n";
            String twoHyphens = "--";
            String boundary =  "*****";
            
            //get image 
            File file = new File(path);
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(file);
            } catch (IOException ex) {
                Logger.getLogger(UploadService.class.getName()).log(Level.SEVERE, null, ex);
            }
            ;
            
            //setup the request
            HttpURLConnection httpUrlConnection = null;
            URL url = null;
            try {
                url = new URL("http://127.0.0.1/uploads");
            } catch (MalformedURLException ex) {
                Logger.getLogger(UploadService.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                httpUrlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException ex) {
                Logger.getLogger(UploadService.class.getName()).log(Level.SEVERE, null, ex);
            }
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setDoOutput(true);

            try {
                httpUrlConnection.setRequestMethod("POST");
            } catch (ProtocolException ex) {
                Logger.getLogger(UploadService.class.getName()).log(Level.SEVERE, null, ex);
            }
            httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
            httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
            httpUrlConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            
            //start content wrapper
            try {
                 DataOutputStream request = new DataOutputStream(httpUrlConnection.getOutputStream());
                 request.writeBytes(twoHyphens + boundary + crlf);
                request.writeBytes("Content-Disposition: form-data; name=\"" +
                        attachmentName + "\";filename=\"" +
                        attachmentFileName + "\"" + crlf);
                request.writeBytes(crlf);
                
                //convert image to bytebuffer
            //I want to send only 8 bit black & white bitmaps
            byte[] pixels = new byte[bufferedImage.getWidth() * bufferedImage.getHeight()];
            for (int i = 0; i < bufferedImage.getWidth(); ++i) {
                for (int j = 0; j < bufferedImage.getHeight(); ++j) {
                    //we're interested only in the MSB of the first byte, 
                    //since the other 3 bytes are identical for B&W images
                    pixels[i + j] = (byte) ((bufferedImage.getRGB(i, j) & 0x80) >> 7);
                }
            }
                System.out.println("kkkkkk " + bufferedImage.getWidth());
                Path pp = Paths.get(path);
                byte[] b = Files.readAllBytes(pp);
             request.write(b);
             
            //end container
            request.writeBytes(crlf);
            request.writeBytes(twoHyphens + boundary + 
                twoHyphens + crlf);

            //flush
            request.flush();
            request.close();
            
            //disconnect
            httpUrlConnection.disconnect();
            
            } catch (IOException ex) {
                Logger.getLogger(UploadService.class.getName()).log(Level.SEVERE, null, ex);
            }
            InputStream responseStream = null;
        try {
            responseStream = new 
                    BufferedInputStream(httpUrlConnection.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(UploadService.class.getName()).log(Level.SEVERE, null, ex);
        }

        BufferedReader responseStreamReader = 
            new BufferedReader(new InputStreamReader(responseStream));

        String line = "";
        StringBuilder stringBuilder = new StringBuilder();

        try {
            while ((line = responseStreamReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(UploadService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            responseStreamReader.close();
        } catch (IOException ex) {
            Logger.getLogger(UploadService.class.getName()).log(Level.SEVERE, null, ex);
        }

        String response = stringBuilder.toString();
        System.out.println("res: "  + response);
    }
    
}
