/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.src.Utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import user.src.Entities.Client;

public class ImageUtils {
  
  
public static String CopyfileClient (String a,Client c) throws IOException {
  
  FileInputStream in = new FileInputStream(a);
  String str = c.getId()+".JPG";
  FileOutputStream ou = new FileOutputStream("C:\\xampp\\htdocs\\uploads\\"+c.getId()+".JPG");
  BufferedInputStream bin = new BufferedInputStream(in);
  BufferedOutputStream bou = new BufferedOutputStream(ou);
  int b=0;
  while(b!=-1){
   b=bin.read();
   bou.write(b);
  }
  bin.close();
  bou.close();
  return str;
  
 }
public static void CopyfileAdmin (String a,Client c) throws IOException {
  
  FileInputStream in = new FileInputStream(a);
  FileOutputStream ou = new FileOutputStream("C:\\xampp\\htdocs\\ProfileImg\\"+c.getId()+".JPG");
  BufferedInputStream bin = new BufferedInputStream(in);
  BufferedOutputStream bou = new BufferedOutputStream(ou);
  int b=0;
  while(b!=-1){
   b=bin.read();
   bou.write(b);
  }
  bin.close();
  bou.close();
  
 }

}