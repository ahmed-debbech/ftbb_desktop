/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Ahmed
 */
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
    /**
     * @return the textData
     */
    public String getTextData() {
        return textData;
    }

    /**
     * @param textData the textData to set
     */
    public void setTextData(String textData) {
        this.textData = textData;
    }

    /**
     * @return the numberData
     */
    public int getNumberData() {
        return numberData;
    }

    /**
     * @param numberData the numberData to set
     */
    public void setNumberData(int numberData) {
        this.numberData = numberData;
    }

    /**
     * @return the anyData
     */
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
