package models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PROJECTS {

    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty address;
    private final StringProperty type;
    private final IntegerProperty number_of_year;
    private final StringProperty price_of_land;
    private final StringProperty price_of_build;
    private final StringProperty ed_money;
    private final StringProperty total_money;
    private final DoubleProperty edprecent;
    
                    
    public PROJECTS(int id, String names, String addresss, String types ,String price_of_lands,String ed_moneys,String price_of_builds,String total_moneys,int number_of_years ) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(names);
        this.address = new SimpleStringProperty(addresss);
        this.type = new SimpleStringProperty(types);  
        this.price_of_land = new SimpleStringProperty(price_of_lands);
        this.ed_money= new SimpleStringProperty(ed_moneys);  
        this.price_of_build= new SimpleStringProperty(price_of_builds);  
        this.total_money=new SimpleStringProperty(total_moneys);
        this.number_of_year= new SimpleIntegerProperty(number_of_years);
       
        this.edprecent=new SimpleDoubleProperty((Double.parseDouble(ed_moneys.replace(",", ""))/(Double.parseDouble(price_of_lands.replace(",", ""))+Double.parseDouble(price_of_builds.replace(",", ""))))*100);
      // (Double.parseDouble((ed_moneys.replace(",", ""))/(Double.parseDouble(price_of_lands.replace(",", ""))+Double.parseDouble(price_of_builds.replace(",", ""))))*100
    }
    public PROJECTS()
    {
               this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.address = new SimpleStringProperty("");
        this.type = new SimpleStringProperty("");  
        this.price_of_land = new SimpleStringProperty("");
        this.ed_money= new SimpleStringProperty("");  
        this.price_of_build= new SimpleStringProperty("");  
        this.total_money=new SimpleStringProperty("");
        this.number_of_year= new SimpleIntegerProperty(0);
       
        this.edprecent=new SimpleDoubleProperty(0);
    }
    

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
        
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getNames() {
        return name.get();
    }

    public void setNames(String value) {
        name.set(value);
    }

    public StringProperty namesProperty() {
        return name;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String value) {
        address.set(value);
    }

    public StringProperty AddressProperty() {
        return address;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String value) {
        type.set(value);
    }

    public StringProperty TypeProperty() {
        return type;
    }
    
        public int getNumber() {
        return number_of_year.get();
    }

    public void setNumber(int value) {
        number_of_year.set(value);
    }

    public IntegerProperty NumberProperty() {
        return number_of_year;
    }


    public String getPrice_OF_Land() {
        return price_of_land.get();
    }

    public void setPrice_OF_Land(String value) {
        price_of_land.set(value);
    }

    public StringProperty Price_OF_LandProperty() {
        return price_of_land;
    }

    
    public String getPrice_OF_Build() {
        return price_of_build.get();
    }

    public void setPrice_OF_Build(String value) {
        price_of_build.set(value);
    }

    public StringProperty Price_OF_BuildProperty() {
        return price_of_build;
    }
   
    
    public String getEd_Money() {
        return ed_money.get();
    }

    public void setgetEd_Money(String value) {
       ed_money.set(value);
    }

    public StringProperty Ed_MoneyProperty() {
        return ed_money;
    }
    
    
    public String getTotal_Money() {
        return total_money.get();
    }

    public void setTotal_Money(String value) {
       total_money.set(value);
    }

    public StringProperty Total_MoneyProperty() {
        return total_money;
    }
    
     public double getedprecent() {
        return edprecent.get();
    }

    public void setTotal_Money(double value) {
       edprecent.set(value);
    }

    public DoubleProperty edprecentProperty() {
        return edprecent;
    }
}
