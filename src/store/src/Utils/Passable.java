/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store.src.Utils;

import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Ahmed
 */
public class Passable {
    private String textData;
    private int numberData;
    private Object anyData;
    private AnchorPane container;

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
        setAnyData(null);
        setInstance(null);
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

    /**
     * @return the container
     */
    public AnchorPane getContainer() {
        return container;
    }

    /**
     * @param container the container to set
     */
    public void setContainer(AnchorPane container) {
        this.container = container;
    }

    /**
     * @param aInstance the instance to set
     */
    public static void setInstance(Passable aInstance) {
        instance = aInstance;
    }
    
}
