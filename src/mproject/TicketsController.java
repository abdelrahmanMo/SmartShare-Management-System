/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mproject;


import classes.DbHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import models.PROJECTS;
import models.Partners;
import models.PartnersIns;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class TicketsController implements Initializable {

    private ObservableList<PartnersIns> data;
    @FXML
    private ImageView imgBack;
    @FXML
    private JFXComboBox<String> comboFrom;
    @FXML
    private JFXComboBox<String> comboTo;
    @FXML
    private JFXTextField txtFare;
    @FXML
    private JFXButton btnPay;
    
    private Connection conn;
    private DbHandler handler;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView tableTickets;
    @FXML
    private Label lblTicketsSold;
    @FXML
    private Label lblTotalSales;

    @FXML
    private TableView<PartnersIns> tablepartnersInfo2;
    @FXML
    private TableColumn<PartnersIns, Integer> colIds;
    @FXML
    private TableColumn<PartnersIns, String> colName;
    @FXML
    private TableColumn<PartnersIns, String> colprojectName;
    @FXML
    private TableColumn<PartnersIns, String> colphone1;
    @FXML
    private TableColumn<PartnersIns, String> colphone2;  
    @FXML
    private TableColumn<PartnersIns, String> colTimeOfins;
    @FXML
    private TableColumn<PartnersIns, String> colmoneyOfins;
     @FXML
    private TableColumn<PartnersIns, Integer> colnumberOfins;
      
     static String paypartners="";
      static int paypartnersID;
     final ObservableList<PartnersIns> daa=FXCollections.observableArrayList();
    //public TicketsController() {
      //  this.data = null;
    //}
    
  
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // populate combos
      //  populateCombos();
        //Instantian Dbhandler class
        handler = new DbHandler();
        //Validate Amount textfield with validator
        /*StringConverter<Integer> formatter = new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                if (object == null) {
                    return "0";
                }
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        };
        txtFare.setTextFormatter(new TextFormatter<>(formatter));
        //Populate tables
        buildDataTable();
        updateStatistics();
*/
        buildDataTable();
    }

    @FXML
    private void goBack(MouseEvent event) {
        try {
            imgBack.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("Menus.fxml"));
            Scene scene = new Scene(root);
            Stage menuStage = new Stage();
            menuStage.setScene(scene);
            menuStage.show();
        } catch (IOException ex) {
            Logger.getLogger(DriversController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void bookTicket(ActionEvent event) throws SQLException {
        //Get variables
        String from = comboFrom.getSelectionModel().getSelectedItem();
        String to = comboTo.getSelectionModel().getSelectedItem();
        Double amount = Double.parseDouble(txtFare.getText().trim());
        // Establish connection
        conn = handler.getConnection();
        String sql = "INSERT INTO tickets(origin,destination,amount,`time`) VALUES (?,?,?,now())";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, from);
        pst.setString(2, to);
        pst.setDouble(3, amount);
        int success = pst.executeUpdate();
        if (success == 1) {
            JFXSnackbar fXSnackbar = new JFXSnackbar(rootPane);
            fXSnackbar.show("Ticket book successful", 4000);
            buildDataTable();
            updateStatistics();
            
        }

    }

    private void populateCombos() {

        comboFrom.getItems().addAll("Nairobi", "Mombasa", "Kisumu", "Kitale", "Machakos",
                "Thika", "Kiambu", "Voi", "Embu", "Marsabit");
        comboTo.getItems().addAll("Nairobi", "Mombasa", "Kisumu", "Kitale", "Machakos",
                "Thika", "Kiambu", "Voi", "Embu", "Marsabit");

    }
    public static String formatt(float c) {

        DecimalFormat myFormatter = new DecimalFormat("##,###,###");
        float iAsFloat = Float.parseFloat("" + c);
        // System.out.println("long i = " + c + " becomes " + myFormatter.format(iAsFloat));
        return myFormatter.format(iAsFloat);

    }

    private void buildDataTable() {
        try {
            String project_name="";
             String ins_money="";
             String phone1="" ;
             String phone2="";
             String names="";
             int ins_paid=0;
             int i=1;
             int idd=0;
             String n="";
            
             String query1="select par_id , deadline from quest where reminder >= now()";
             conn = handler.getConnection();
             ResultSet set1=conn.createStatement().executeQuery(query1);
           // System.out.println(set1.next());
             while(set1.next())
             {
                  idd=i;
                i++;
                 int id = set1.getInt(1);
                  Date time=set1.getDate(2);
                // System.out.println(time);
            String query = "SELECT  par_name,par_phone1, par_phone2, ins_paid FROM partners where par_id = "+id;
          //  data = FXCollections.observableArrayList();
            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {
                  
               
               names = set.getString(1);
               // System.out.println(names);
                String sql="SELECT pro_name from projects where pro_id =(SELECT pro_id from enrollment where par_id="+ id +")"; 
               
                ResultSet set2=conn.createStatement().executeQuery(sql);
                
                 while (set2.next()){
                 project_name= set2.getString(1);
                 }
              
                phone1= set.getString(2);
                 phone2 = set.getString(3);  
                 if(phone2==null)
                 {
                     phone2="لا يوجد";
                 }
                 ins_paid = set.getInt(4);
                  
                String sql2= "SELECT money from total_ins where par_id="+id+" and number_of_ins= 1";
                ResultSet set3=conn.createStatement().executeQuery(sql2);
               
                while(set3.next()){
                 ins_money= set3.getString(1);
                
                }
                  
            // System.out.println("7");
            }
            
           
      
                     try {
                    daa.add(new PartnersIns(idd,names, project_name, phone1, phone2, time.toString(), formatt(Float.parseFloat(ins_money)),ins_paid+1));
                        
               //  data.add(new PartnersIns(names, project_name, phone1, phone2, time.toString(), formatt(Float.parseFloat(ins_money)),ins_paid+1));
                } catch (Exception e) {
                      System.out.println("ffffffffffffffffffff");
                }
                  
           
            }
             colIds.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("names"));
            colprojectName.setCellValueFactory(new PropertyValueFactory<>("pro_name"));
              colphone1.setCellValueFactory(new PropertyValueFactory<>("phone1"));
              colphone2.setCellValueFactory(new PropertyValueFactory<>("phone2"));
            colTimeOfins.setCellValueFactory(new PropertyValueFactory<>("time"));
            colmoneyOfins.setCellValueFactory(new PropertyValueFactory<>("moneyof_ins"));
            colnumberOfins.setCellValueFactory(new PropertyValueFactory<>("numberOfins"));
           
          
           
           

            tablepartnersInfo2.setItems(null);
       tablepartnersInfo2.setItems(daa);

        } catch (SQLException ex) {
            Logger.getLogger(DriversController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

     
  
    private void updateStatistics() {
        try {
            String query = "SELECT count(id),sum(amount) FROM  tickets";
            conn = handler.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                lblTicketsSold.setText(rs.getString(1));
                lblTotalSales.setText("Kes "+rs.getInt(2));

            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // الدفع 
      @FXML
    private void Selectedtable(Event event) throws SQLException  {
       //  System.out.println("Selected Value" );
        PartnersIns s= new PartnersIns();
        s=tablepartnersInfo2.getSelectionModel().getSelectedItem();
        paypartners=s.getNames();
       //  System.out.println(paypartners);
         String sql="SELECT partners.par_id  FROM partners where par_name =\""+paypartners+"\"";
         conn = handler.getConnection();
          try {
             ResultSet resultSet = conn.createStatement().executeQuery(sql);
             while(resultSet.next())
             {
                 paypartnersID = resultSet.getInt(1) ;
             }
          } catch (SQLException ex) {
               Logger.getLogger(BusesController.class.getName()).log(Level.SEVERE, null, ex);
          }
     
    }
    
    @FXML
     private void switchtopay(ActionEvent event )
    {
        payController1.payId=paypartnersID;
        payController1.paypartnersname=paypartners;
        if(paypartners!=""){
         try {
           imgBack.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("pay.fxml"));
            Scene scene = new Scene(root);
            Stage menuStage = new Stage();
            menuStage.setScene(scene);
            menuStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenusController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }
        
    }
   

}
