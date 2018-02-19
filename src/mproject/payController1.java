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
import java.time.LocalDate;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import models.InsDetail;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class payController1 implements Initializable {

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

    // my variable
   
    @FXML
    private JFXTextField txtpartnersName;
    @FXML
    private JFXTextField txtNote;
    
    @FXML
    private TableView<InsDetail> tableinspartners;
    @FXML
    private TableColumn<InsDetail, Integer> colnumberofins;
    @FXML
    private TableColumn<InsDetail, String> colTimeofins;
    @FXML
    private TableColumn<InsDetail, String> colmoneyofins;
   
      static int payId;
      static String paypartnersname;
     int ins_id;
     int ins_idselected;
     java.sql.Date nextTime;
     int counterpaid_ins;
     boolean end=false;
     
    // observableArrayList()
     final ObservableList<InsDetail> daa=FXCollections.observableArrayList();
    // private ObservableList<InsDetail> daa=null;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // populate combos
        //populateCombos();
        //Instantian Dbhandler class
        handler = new DbHandler();
        //Validate Amount textfield with validator
       
        //txtFare.setTextFormatter(new TextFormatter<>(formatter));
        //Populate tables
        //buildDataTable();
        
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
    private void bookTicket(ActionEvent event) throws SQLException, Exception {
      
        payins();

    }

    public static String formatt(float c) {

        DecimalFormat myFormatter = new DecimalFormat("##,###,###");
        float iAsFloat = Float.parseFloat("" + c);
        // System.out.println("long i = " + c + " becomes " + myFormatter.format(iAsFloat));
        return myFormatter.format(iAsFloat);

    }


    private void buildDataTable() {
       
        txtpartnersName.setText(paypartnersname);
        try {
            conn = handler.getConnection();
            String query = "SELECT * FROM  total_ins where par_id = "+payId+" and state=0";
            
            ResultSet rst = conn.createStatement().executeQuery(query);
           // ObservableList<ObservableList> data = FXCollections.observableArrayList();
          while(rst.next())
          {
               ins_id=rst.getInt(1);
               int numberOfins=rst.getInt(3);
               Date timee= rst.getDate(4);
               String moneyofins= rst.getString(6);
               daa.add(new InsDetail(ins_id, numberOfins, timee.toString(), formatt(Float.parseFloat(moneyofins))));
          }
          
            colnumberofins.setCellValueFactory(new PropertyValueFactory<>("numberOFins"));
            colTimeofins.setCellValueFactory(new PropertyValueFactory<>("timeofins"));
            colmoneyofins.setCellValueFactory(new PropertyValueFactory<>("moneyofins"));
           
          
           
           

            tableinspartners.setItems(null);
                tableinspartners.setItems(daa);
        } catch (SQLException ex) {
            Logger.getLogger(payController1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  @FXML
    private void Selectedtable(Event event)  {
       //  System.out.println("Selected Value" );
        InsDetail s= new InsDetail();
        
        
        s=tableinspartners.getSelectionModel().getSelectedItem();
        ins_idselected=s.getid();
        //System.out.println(s.getNames());
         
     
    }
    private void payins() throws SQLException
    {
          try {
                        
              conn = handler.getConnection();
            String query = "update  total_ins set state=1 where total_ins_id="+ins_idselected ;
                   // System.out.println(query);
           PreparedStatement rst = conn.prepareStatement(query);
           int success=rst.executeUpdate();
          if( success==1 )
          {
              update();
              Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("تم الدفع بنجاح");
                           alert.showAndWait();
                           changePartners();
                           checkifend();
                           if(end)
                           {
                               Alert alert2 = new Alert(AlertType.INFORMATION);
                    alert2.setTitle("Information Dialog");
                        alert2.setHeaderText(null);
                        alert2.setContentText("تم انتهاء اقساط العميل");
                           alert2.showAndWait();
                           
                           }
                           else
                           {
                               changequest();
                               
                           }
                           
                     }
                
                    } catch (SQLException ex) {
                         Logger.getLogger(DriversController.class.getName()).log(Level.SEVERE, null, ex);
                    }
             
    }
    
    private void changePartners() throws SQLException
    {
        try {
              String sql="select count(*) from total_ins where par_id="+payId +" and state=1";
                ResultSet rst = conn.createStatement().executeQuery(sql);
                while(rst.next())
                {
                    counterpaid_ins=rst.getInt(1);
                }
                
               String sql2="update partners set ins_paid="+counterpaid_ins+" where par_id="+payId ;
               PreparedStatement set=conn.prepareStatement(sql2);
                 int success=set.executeUpdate();
                
        } catch (SQLException  e) {
            Logger.getLogger(payController1.class.getName()).log(Level.SEVERE, null, e);
        }
                
        
        
    }
    
    
    private void changequest() throws SQLException
    {
         try {
            conn = handler.getConnection();
            String query = "select time_of_ins from total_ins where  total_ins_id=(select  min(total_ins_id) from total_ins where state=0 && par_id="+payId + ")";
            ResultSet rst = conn.createStatement().executeQuery(query);
           // ObservableList<ObservableList> data = FXCollections.observableArrayList();
          while(rst.next())
          {
               nextTime=rst.getDate(1);
          }
          
            LocalDate next=nextTime.toLocalDate();
            LocalDate reminderr=next.minusDays(7);
            //java.sql.Date D=java.sql.Date.valueOf(reminderr);
            //Date r= java.sql.Date.valueOf(reminderr);
            //Date c=java.sql.Date.valueOf(next);
            // System.out.println(r);
              //System.out.println();
          String sql="update quest set deadline=?, reminder=? where par_id ="+payId;
            PreparedStatement set=conn.prepareStatement(sql);
            set.setDate(1, nextTime);
            set.setDate(2, java.sql.Date.valueOf(reminderr));
           int success=set.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(payController1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void checkifend() throws SQLException
    {
        int total=2;
        int paid=0 ;
         try {
            conn = handler.getConnection();
            String query = "select total_of_ins , ins_paid from partners where par_id="+payId;
            ResultSet rst = conn.createStatement().executeQuery(query);
           // ObservableList<ObservableList> data = FXCollections.observableArrayList();
          while(rst.next())
          {
               total =rst.getInt(1);
                paid =rst.getInt(2);
          }
         if(total==paid){
             end=true;
         }
        } catch (SQLException ex) {
            Logger.getLogger(payController1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void update()
    {
        daa.removeAll(daa);
        try {
         Connection   conn2 = handler.getConnection();
            String query = "SELECT * FROM  total_ins where par_id = "+payId+" and state=0";
          //  System.out.println(query);
            ResultSet rst = conn2.createStatement().executeQuery(query);
           // ObservableList<ObservableList> data = FXCollections.observableArrayList();
          while(rst.next())
          {
               ins_id=rst.getInt(1);
               int numberOfins=rst.getInt(3);
               Date timee= rst.getDate(4);
               String moneyofins= rst.getString(6);
               daa.add(new InsDetail(ins_id, numberOfins, timee.toString(), formatt(Float.parseFloat(moneyofins))));
          }
       //   tableinspartners.getColumns().clear();
            colnumberofins.setCellValueFactory(new PropertyValueFactory<>("numberOFins"));
            colTimeofins.setCellValueFactory(new PropertyValueFactory<>("timeofins"));
            colmoneyofins.setCellValueFactory(new PropertyValueFactory<>("moneyofins"));
                //  tableinspartners.setItems(daa);
                // tableinspartners.refresh();
                  tableinspartners.setItems(null);
                tableinspartners.setItems(daa);
                //tableinspartners.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(payController1.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }

}
