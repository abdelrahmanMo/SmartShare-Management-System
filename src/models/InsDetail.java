/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author abdelrahmanOsman
 */

public class InsDetail {
   
    private final IntegerProperty id;
    private final IntegerProperty numberOFins;
        private final StringProperty timeofins;
    private final StringProperty moneyofins;
    
    
    public InsDetail(Integer id, Integer numberodins, String timeofins, String moneyofins)
    {
         this.id = new SimpleIntegerProperty(id);
        this.numberOFins = new SimpleIntegerProperty(numberodins);
        this.timeofins = new SimpleStringProperty(timeofins);
        this.moneyofins = new SimpleStringProperty(moneyofins);
    }
    
     public InsDetail()
    {
         this.id = new SimpleIntegerProperty(0);
        this.numberOFins = new SimpleIntegerProperty(0);
        this.timeofins = new SimpleStringProperty("");
        this.moneyofins = new SimpleStringProperty("");
    }
     
    public int getid() {
         
        return id.get();
    }

    public void setid(int value) {
      
        id.set(value);
    }

    public IntegerProperty idProperty() {
       
        return id;
    }
    public int getnumberOFins() {
         
        return numberOFins.get();
    }

    public void setnumberOFins(int value) {
      
        numberOFins.set(value);
    }

    public IntegerProperty numberOFinsProperty() {
       
        return numberOFins;
    }
    
     public String gettimeofins() {
        return timeofins.get();
    }

    public void settimeofins(String value) {
        timeofins.set(value);
    }

    public StringProperty timeofinsProperty() {
        return timeofins;
    }
    
     public String getmoneyofins() {
        return moneyofins.get();
    }

    public void setmoneyofins(String value) {
        moneyofins.set(value);
    }

    public StringProperty moneyofinsProperty() {
        return moneyofins;
    }
    
}
