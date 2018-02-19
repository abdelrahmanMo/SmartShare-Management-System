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
public class PartnersIns {
    //  private final IntegerProperty names;
       private final IntegerProperty id;
    private final StringProperty names;
    private final StringProperty pro_name;
    private final StringProperty phone1;
    private final StringProperty phone2;
    private final StringProperty time;
   
    private final StringProperty moneyof_ins; 
    private final IntegerProperty numberOfins;

    //  private final IntegerProperty nopaid_ins ;
   

    public PartnersIns(Integer id ,String names, String pro_name, String phone1, String phone2, String time,String moneyof_ins ,Integer numberOfins) {
       //System.out.println("first");
        this.id = new SimpleIntegerProperty(id);
        this.names = new SimpleStringProperty(names);
        this.pro_name = new SimpleStringProperty(pro_name);
        this.phone1 = new SimpleStringProperty(phone1);
        this.phone2 = new SimpleStringProperty(phone2);
        this.time = new SimpleStringProperty(time);
      this.moneyof_ins=new SimpleStringProperty(moneyof_ins);
       
        this.numberOfins = new SimpleIntegerProperty(numberOfins);
      //  System.out.println("end");

    }
    public PartnersIns(){
      
        this.id = new SimpleIntegerProperty(0);
            this.names = new SimpleStringProperty("");
        this.pro_name = new SimpleStringProperty("");
        this.phone1 = new SimpleStringProperty("");
        this.phone2 = new SimpleStringProperty("");
        this.time = new SimpleStringProperty("");
      this.moneyof_ins=new SimpleStringProperty("");
       
        this.numberOfins = new SimpleIntegerProperty(0);
      
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
    
    public String getNames() {
        return names.get();
    }

    public void setNames(String value) {
        names.set(value);
    }

    public StringProperty namesProperty() {
        return names;
    }
     public String getpro_name() {
        return pro_name.get();
    }

    public void setpro_name(String value) {
        pro_name.set(value);
    }

    public StringProperty pro_nameProperty() {
        return pro_name;
    }

    public String getPhone1() {
        return phone1.get();
    }

    public void setPhone1(String value) {
        phone1.set(value);
    }

    public StringProperty phone1Property() {
        return phone1;
    }
    
    public String getphone2() {
        return phone2.get();
    }

    public void setphone2(String value) {
        phone2.set(value);
    }

    public StringProperty phone2Property() {
        return phone2;
    }

    public String gettime() {
        return time.get();
    }

    public void settime(String value) {
        time.set(value);
    }

    public StringProperty timeProperty() {
        return time;
    }

    
      public String getmoneyof_ins() {
        return moneyof_ins.get();
    }

    public void setmoneyof_ins(String value) {
        moneyof_ins.set(value);
    }

    public StringProperty moneyof_insProperty() {
        return moneyof_ins;
    }

    public int getnumberOfins() {
        return numberOfins.get();
    }

    public void setnumberOfins(int value) {
        numberOfins.set(value);
    }

    public IntegerProperty numberOfinsProperty() {
        return numberOfins;
    }
    
  
  
}
