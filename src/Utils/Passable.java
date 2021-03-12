/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


public class Passable {
    private String textData;
    private int numberData;
    private Object anyData;

    private static Passable instance;

    private Passable (){

    }
    public static Passable getInstance(){
        if(instance == null){
               instance = new Passable();
        }
        return instance;
    }

    public void erase(){
        anyData = null;
        instance = null;
    }
    
    public String getTextData() {
        return textData;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }

  
    public int getNumberData() {
        return numberData;
    }

    
    public void setNumberData(int numberData) {
        this.numberData = numberData;
    }

   
    public Object getAnyData() {
        return anyData;
    }

    /**
     * @param anyData the anyData to set
     */
    public void setAnyData(Object anyData) {
        this.anyData = anyData;
    }

}
