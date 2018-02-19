
package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Partners {
    private final IntegerProperty id;
    private final StringProperty names;
    private final StringProperty phone;
    private final StringProperty address;
    private final StringProperty job;
    private final StringProperty pro_name;
    private final StringProperty total_fin;
    private final StringProperty paid;
     private final IntegerProperty total_numberOfins;
      private final IntegerProperty paid_ins;
    //  private final IntegerProperty nopaid_ins ;
       private final StringProperty moneyof_ins;

    public Partners(Integer id,String names, String phone, String address, String job, String pro_name, String total_fin, String paid, Integer total_numberOfins,Integer paid_ins,String moneyof_ins) {
        this.id = new SimpleIntegerProperty(id);
        this.names = new SimpleStringProperty(names);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
        this.job = new SimpleStringProperty(job);
        this.pro_name = new SimpleStringProperty(pro_name);
        this.total_fin = new SimpleStringProperty(total_fin);
        this.paid = new SimpleStringProperty(paid);
        this.total_numberOfins = new SimpleIntegerProperty(total_numberOfins);
        this.paid_ins = new SimpleIntegerProperty(paid_ins);
      // this.nopaid_ins=new SimpleIntegerProperty(total_numberOfins-paid_ins);
        this.moneyof_ins=new SimpleStringProperty(moneyof_ins);
    }
    public Partners(){
         this.id = new SimpleIntegerProperty(0);
        this.names = new SimpleStringProperty("");
        this.phone = new SimpleStringProperty("");
        this.address = new SimpleStringProperty("");
        this.job = new SimpleStringProperty("");
        this.pro_name = new SimpleStringProperty("");
        this.total_fin = new SimpleStringProperty("");
        this.paid = new SimpleStringProperty("");
        this.total_numberOfins = new SimpleIntegerProperty(0);
        this.paid_ins = new SimpleIntegerProperty(0);
      // this.nopaid_ins=new SimpleIntegerProperty(total_numberOfins-paid_ins);
        this.moneyof_ins=new SimpleStringProperty("");
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

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String value) {
        phone.set(value);
    }

    public StringProperty phoneProperty() {
        return phone;
    }
    
    public String getaddress() {
        return address.get();
    }

    public void setaddress(String value) {
        address.set(value);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getjob() {
        return job.get();
    }

    public void setjob(String value) {
        job.set(value);
    }

    public StringProperty jobProperty() {
        return job;
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

    public String gettotal_fin() {
        return total_fin.get();
    }

    public void settotal_fin(String value) {
        total_fin.set(value);
    }

    public StringProperty total_finProperty() {
        return total_fin;
    }

    public String getpaid() {
        return paid.get();
    }

    public void setpaid(String value) {
        paid.set(value);
    }

    public StringProperty paidProperty() {
        return paid;
    }
    
    public int gettotal_numberOfins() {
        return total_numberOfins.get();
    }

    public void settotal_numberOfins(int value) {
        total_numberOfins.set(value);
    }

    public IntegerProperty total_numberOfinsProperty() {
        return total_numberOfins;
    }
    
     public int getpaid_ins() {
        return paid_ins.get();
    }

    public void setpaid_ins(int value) {
        paid_ins.set(value);
    }

    public IntegerProperty paid_insProperty() {
        return paid_ins;
    }
    
    
   //  public int getnopaid_ins() {
    //    return nopaid_ins.get();
    //}

    //public void setnopaid_ins(int value) {
      //  nopaid_ins.set(value);
    //}

    //public IntegerProperty nopaid_insProperty() {
      //  return nopaid_ins;
    //}

    public String getmoneyof_ins() {
        return moneyof_ins.get();
    }

    public void setmoneyof_ins(String value) {
        moneyof_ins.set(value);
    }

    public StringProperty moneyof_insProperty() {
        return moneyof_ins;
    }
    
}
