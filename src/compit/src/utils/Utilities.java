/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compit.src.utils;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *
 * @author Lenovo
 */
public class Utilities implements Serializable {

    public static String pathToUrl(String str){
            Path p = Paths.get(str);
            String file = p.getFileName().toString();
            String s=(new StringBuilder()).append("http://127.0.0.1/uploads/").append(file).toString();  
            return s;
    }

    
}
