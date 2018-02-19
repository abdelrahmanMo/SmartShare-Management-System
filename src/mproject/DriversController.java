/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mproject;

import classes.DbHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import javax.swing.JFormattedTextField;
import models.driver;
import models.Partners;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.textfield.TextFields;
/**
 * FXML Controller class
 *
 * @author danml
 */
public class DriversController implements Initializable {

    @FXML
    private ImageView imgBack;
    @FXML
    private AnchorPane topAnchor;
    @FXML
    private ImageView imgProfile;
    @FXML
    private JFXButton btnChoose;
    @FXML
    private StackPane rootPane;
    @FXML
    private ToggleGroup gender;
    @FXML
    private ToggleGroup family;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXTextField txtSerialNo;
    @FXML
    private JFXButton btnGenerate;
    @FXML
    private JFXComboBox<String> comboLicence;
    @FXML
    private JFXComboBox<Number> comboYear;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXSnackbar snackEdit;
    @FXML
    private JFXTextField txtFirstName;
    
    @FXML
    private JFXTextField txtMiddleName;
    @FXML
    private JFXTextField txtSurName;
    @FXML
    private JFXDatePicker dtDOB;
    @FXML
    private JFXTextField txtPhone;
    @FXML
    private RadioButton rdMale;
    @FXML
    private RadioButton rdFemale;
    @FXML
    private RadioButton rdSingle;
    @FXML
    private RadioButton rdMarried;
    @FXML
    private RadioButton rdOthers;
    private DbHandler handler;
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet rs;
    @FXML
    private JFXComboBox<String> comboPLate;
  @FXML
    private TableView<driver> tableDriversInfo;
    @FXML
    private TableColumn<driver, String> colNames;
    @FXML
    private TableColumn<driver, String> colDob;
    @FXML
    private TableColumn<driver, String> colGender;
    @FXML
    private TableColumn<driver, String> colPhone;
    @FXML
    private TableColumn<driver, String> colStatus;
    @FXML
    private TableColumn<driver, String> colLicense;
    @FXML
    private TableColumn<driver, String> colIssued;

   
    
    // new data for me 
    @FXML 
    private JFXTextField txtphonesearch;
    @FXML
    private JFXTextField txtName;
    @FXML 
    private JFXTextField txtsalary;
    @FXML
    private JFXTextField txtphone1;
    @FXML
    private JFXTextField txtphone2;
    @FXML
    private JFXTextField txtaddress;
    @FXML
    private JFXTextField  txtfin_share;
     @FXML
    private JFXComboBox<String> comboproject;
     @FXML 
  private JFXTextField txtfirst_paid;
     @FXML
    private JFXDatePicker dtDatestart;
      private int numberOfYear;
      private int typeOFproject;
      
       private PreparedStatement ps;
     
     private final String pattern = "yyyy-MM-dd";
     
    @FXML
    private TableView<Partners> tablepartnersInfo;
    @FXML
    private TableColumn<Partners, String> colName;
    @FXML
    private TableColumn<Partners, String> colphone;
    @FXML
    private TableColumn<Partners, String> coladdress;
    @FXML
    private TableColumn<Partners, String> coljob;
    @FXML
    private TableColumn<Partners, String> colpro_name;
    @FXML
    private TableColumn<Partners, String> coltotal_fin;
    @FXML
    private TableColumn<Partners, String> coltotal_paid;
    
     @FXML
    private TableColumn<Partners, Integer> coltotal_number;
     @FXML
    private TableColumn<Partners, Integer> colnumber_ofpaid;
     @FXML
    private TableColumn<Partners, String> colmoneyof_ins;
     
     private ObservableList<Partners> data;
     
     @FXML
    private Label labempty1;
     
    private int IDselectedforins;
    private String nameselectedforins;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Instantiate db class
        handler = new DbHandler();

        JFXRippler backRippler = new JFXRippler(imgBack, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
       topAnchor.getChildren().add(imgBack);
       populateCombos();
     //  buildDriversTable();
       // System.out.println(dtDatestart.getStyleClass());
      //  System.out.println(txtName.getStyleClass());
       // System.out.println(dtDatestart.getStyleClass().size());
    //  selectprojectdata();
        // Set default selected radio buttons
        //rdMale.setSelected(true);
        //rdSingle.setSelected(true);
        //populate table
        buildDriversTable();
       
      // Rectangle clip=new Rectangle(imgProfile.getFitWidth(), imgProfile.getFitHeight());
    //    clip.setArcHeight(120);
  //      clip.setArcWidth(120);
//        imgProfile.setClip(clip);
//        SnapshotParameters parameters=new SnapshotParameters();
//        parameters.setFill(Color.TRANSPARENT);
//        WritableImage img=imgProfile.snapshot(parameters, null);
//        imgProfile.setClip(null);
//        imgProfile.setImage(img);
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
    private void choosePhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filterJPG = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter filterPNG = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(filterPNG, filterJPG);
        //show open dialog
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                Rectangle clip = new Rectangle(imgProfile.getFitWidth(), imgProfile.getFitHeight());
                clip.setArcHeight(180);
                clip.setArcWidth(180);
                imgProfile.setImage(image);
                imgProfile.setClip(clip);
                SnapshotParameters parameters = new SnapshotParameters();
                parameters.setFill(Color.TRANSPARENT);
                WritableImage img = imgProfile.snapshot(parameters, null);
                imgProfile.setClip(null);
                imgProfile.setImage(img);

            } catch (IOException ex) {
                Logger.getLogger(DriversController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void saveDriver(ActionEvent event) throws SQLException {
        if(txtName.getText().isEmpty()||txtphone1.getText().isEmpty()||txtaddress.getText().isEmpty()||txtsalary.getText().isEmpty()||txtfin_share.getText().isEmpty()||txtfirst_paid.getText().isEmpty()||comboproject.getSelectionModel().isEmpty()||dtDatestart.getValue().equals(""))
        {
            checkempty();
            labempty1.setText("الرجاء ادخال جميع البيانات");
        }
        else{
             labempty1.setText("");
            write();
             boolean x = checkinput();
            if (x) {
               JFXSnackbar wrongint = new JFXSnackbar(rootPane);
                wrongint.show("الرجاء ادخال البيانات بشكل صحيح", "exit", 0, (e) -> {
                    wrongint.close();
                });}
            else
            {
               
        try {
            String insert="";
            //if(!comboproject.getSelectionModel().isEmpty())
           // {
                
           // }
            if(!txtphone2.getText().isEmpty()){
             insert = "INSERT INTO partners(par_name,job,par_phone1,par_address,fin_share,first_paid,"
                    + "paid,notpaid,par_phone2) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";}
            else if(txtphone2.getText().isEmpty())
                    {
                          insert = "INSERT INTO partners(par_name,job,par_phone1,par_address,fin_share,first_paid,"
                    + "paid,notpaid) "
                    + "VALUES (?,?,?,?,?,?,?,?)";
                    }
                
            conn = handler.getConnection();
            pst = conn.prepareStatement(insert);
            pst.setString(1, txtName.getText());
            pst.setString(2, txtsalary.getText());
            pst.setString(3, txtphone1.getText().trim());
            pst.setString(4, txtaddress.getText());
            pst.setString(5, txtfin_share.getText());
            pst.setString(6, txtfirst_paid.getText());
            pst.setString(7, txtfirst_paid.getText());
            double not=Double.parseDouble(txtfin_share.getText())-Double.parseDouble(txtfirst_paid.getText());
            pst.setString(8, Double.toString(not));
                if(!txtphone2.getText().isEmpty()){
                     pst.setString(9, txtphone2.getText());
                      }
           int success = pst.executeUpdate();
           if(success==1)
           {
               selectprojectdata();
               insertintoEnroll();
               insertIntoQuest();
               clearFields();
               buildDriversTable();
              JFXSnackbar fXSnackbar = new JFXSnackbar(rootPane);
                        // fXSnackbar.show("New project saved into the records", 5000);
                        fXSnackbar.show("تم تسجيل مشروع جديد بنجاح", "exit", 200, (c) -> {
                            fXSnackbar.close();
                        });
           }
           else
           {
                System.out.println("error in send data to database in partners");
           }
         }catch (SQLException ex) {
           Logger.getLogger(DriversController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }}
    }

    @FXML
    private void editDriver(ActionEvent event) {
       // JFXSnackbar fXSnackbar = new JFXSnackbar(rootPane);
        //fXSnackbar.show("Edit functionality will be implemented later on", 5000);
        //System.out.println(dtDatestart.getValue());
    // Date.from(dtDatestart.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    Date c=java.sql.Date.valueOf(dtDatestart.getValue());
        System.out.println( c.getClass().getSimpleName() );
        
      //  Date x= dtDatestart.getValue();
     //   addDays(dtDatestart.getValue(), 30);
       
               // System.out.println(java.sql.Date.valueOf(dtDatestart.getValue()));
        
    }

   

    //@FXML
   // private void clearFields(ActionEvent event) {

    //}

    private void populateCombos() {
        try {
           // for (int i = 2017; i > 1970; i--) {
             //   comboYear.getItems().addAll(i);
            //}
          // comboLicence.getItems().addAll("A", "B", "C");
            conn = handler.getConnection();
            pst = conn.prepareStatement("SELECT projects.pro_name FROM projects");
            rs = pst.executeQuery();
            while (rs.next()) {
                comboproject.getItems().addAll(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


    private void buildDriversTable() {
        try {
            String project_name="";
             String ins_money="";
            String query = "SELECT par_id, par_name, job, par_phone1, par_address, fin_Share, paid, total_of_ins, ins_paid FROM partners";
            conn = handler.getConnection();
            data = FXCollections.observableArrayList();
            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {
                 
                int id = set.getInt(1);
                String names = set.getString(2);
               // System.out.println(names);
                String sql="SELECT pro_name from projects where pro_id =(SELECT pro_id from enrollment where par_id="+ id +")"; 
               
                ResultSet set2=conn.createStatement().executeQuery(sql);
                
                 while (set2.next()){
                 project_name= set2.getString(1);
                 }
                
                String job = set.getString(3);
                String phone= set.getString(4);
                String address = set.getString(5);  
                String fin_share = set.getString(6);
                String paid = set.getString(7);
                int total_of_ins = set.getInt(8);
                int ins_paid = set.getInt(9);
                  
                String sql2= "SELECT money from total_ins where par_id="+id+" and number_of_ins= 1";
                ResultSet set3=conn.createStatement().executeQuery(sql2);
                while(set3.next()){
                 ins_money= set3.getString(1);
                
                }
                  
                data.add(new Partners(id,names, phone, address, job, project_name, formatt(Float.parseFloat(fin_share)), formatt(Float.parseFloat(paid)), total_of_ins,ins_paid,formatt(Float.parseFloat(ins_money))));
               
            }
         
            colName.setCellValueFactory(new PropertyValueFactory<>("names"));
     
            colphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
            coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));

            coljob.setCellValueFactory(new PropertyValueFactory<>("job"));
          
            colpro_name.setCellValueFactory(new PropertyValueFactory<>("pro_name"));
         
            coltotal_fin.setCellValueFactory(new PropertyValueFactory<>("total_fin"));
           
            coltotal_paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
           
            coltotal_number.setCellValueFactory(new PropertyValueFactory<>("total_numberOfins"));
           
            colnumber_ofpaid.setCellValueFactory(new PropertyValueFactory<>("paid_ins"));
           
            colmoneyof_ins.setCellValueFactory(new PropertyValueFactory<>("moneyof_ins"));

            tablepartnersInfo.setItems(null);
            tablepartnersInfo.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(DriversController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
       private void clearFields() {
        txtName.setText("");
       txtphone1.setText("");
        txtaddress.setText("");
        comboproject.getSelectionModel().clearSelection();
        txtphone2.setText("");
         txtfin_share.setText("");
        txtfirst_paid.setText("");
         dtDatestart.setValue(null);
         txtsalary.setText("");
         labempty1.setText("");
        write();
        
    }
       //بجيب الداتا بتاعت المشروع ال علي اساسها هحدد عدد الاقساط وموعدها 
       private void selectprojectdata()
       {
         
            try {
         
           String query="select projects.ins_id , projects.number_of_year from projects where pro_name =\""+comboproject.getSelectionModel().getSelectedItem()+"\"";
            conn = handler.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                typeOFproject=rs.getInt(1);
               numberOfYear=rs.getInt(2);
               
               
            }
            if(typeOFproject==1)
            {
                int month=3;
                int number_of_ins=4*numberOfYear;
                savainsquarter(number_of_ins,month);
            }
            else if(typeOFproject==2)
            {
                int month=6;
                int number_of_ins=2*numberOfYear ;
                  savainsquarter(number_of_ins,month);
            }
            else if(typeOFproject==3)
            {
                 int month=1;
                 int number_of_ins=12*numberOfYear ;
                 savainsquarter(number_of_ins,month);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       }

    @FXML
    private void resetFields(ActionEvent event) {
        clearFields();
    }
    
    
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days

        return cal.getTime();
    }
    
    void sqlDateFormatter() {
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
         

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return formatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, formatter);

                } else {
                    return null;
                }
            }

        };
        dtDatestart.setConverter(converter);
   
    }
    
    
    //تسجيل كل الاقساط في جدل الاقساط
    public void savainsquarter(int numberOdins, int month)
    {
        String not_paid = null;
        int par_id=0;
        try{
        conn = handler.getConnection();
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT partners.par_id , partners.notpaid FROM partners where par_phone1 =\""+txtphone1.getText()+"\"");
            while (resultSet.next()) {
               par_id = resultSet.getInt(1) ;
              not_paid=resultSet.getString(2);
            }   
            double notPaid= Double.parseDouble(not_paid);
            double ins_money=notPaid/numberOdins;
            insert_intoTotal(numberOdins, notPaid, ins_money, par_id,month);
            
            // تسجل عدد الاقساط الكليه والمتبقيه في جدول الشركاء
            
                
           // conn = handler.getConnection();
            pst = conn.prepareStatement("update partners set total_of_ins=? , ins_paid=?  WHERE par_id="+par_id);
            pst.setInt(1,numberOdins );
            pst.setInt(2, 0);    
            pst.executeUpdate();
            
            //فاضب كود تسجيل الدااتا في ال total_ins          
            
        } catch (SQLException ex) {
            Logger.getLogger(BusesController.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }
  
    //فانكشن تسجيل كل الاقساط
    public void insert_intoTotal(int numberofIns,double notpaid,double ins_Money,int par_id,int month)
    {
         int success=0;
        String m=Double.toString(ins_Money);
        try{
        String insertQuery = "INSERT INTO total_ins(par_id,number_of_ins,time_of_ins,money) "
                            + "VALUES (?,?,?,?)";
                    conn = handler.getConnection();
                    LocalDate a= dtDatestart.getValue();
                    for(int i=0;i<numberofIns;i++){
                    ps = conn.prepareStatement(insertQuery);
                    ps.setInt(1, par_id);
                    ps.setInt(2, (i+1));
                     //LocalDate a= dtDatestart.getValue().plusMonths(month);
                     a=a.plusMonths(month);
                     ps.setDate(3, java.sql.Date.valueOf(a));
                    ps.setString(4, m);
                     success = ps.executeUpdate();
                   }
                    // ps.setDate(6, java.sql.Date.valueOf(dtDateInsured.getValue()));
                    //      ps.setDate(7, java.sql.Date.valueOf(dtExpiryDate.getValue()));

                  
                    if (success == 1) {
                      
                    } else {
                        JFXSnackbar fXSnackbar = new JFXSnackbar(rootPane);
                        System.out.println("error in send data to database in total_ins");
                    }
        }catch (SQLException ex){
        }
    }
    public void insertintoEnroll()
        {
            try{
                int par_id=0;
                int pro_id=0;
        conn = handler.getConnection();
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT partners.par_id  FROM partners where par_phone1 =\""+txtphone1.getText()+"\"");
            while (resultSet.next()) {
             par_id= resultSet.getInt(1);
            }   
               conn = handler.getConnection();
            ResultSet resultSet2 =conn.createStatement().executeQuery("select projects.pro_id from projects where pro_name =\""+comboproject.getSelectionModel().getSelectedItem()+"\"");
            //فاضب كود تسجيل الدااتا في ال total_ins    
            while (resultSet2.next()) {
             pro_id= resultSet2.getInt(1);  
            }   
            // conn.close();
            String insertQuery = "INSERT INTO enrollment(par_id,pro_id,time_of_enrol) "
                            + "VALUES (?,?,?)";
                    conn = handler.getConnection();
                    ps = conn.prepareStatement(insertQuery);

                    ps.setInt(1, par_id);
                    ps.setInt(2, pro_id);
                   // System.out.println(java.sql.Date.valueOf(dtDatestart.getValue()));
                     ps.setDate(3, java.sql.Date.valueOf(dtDatestart.getValue()));
                  
                    int success = ps.executeUpdate();
                    if(success!=1)
                    {
                        System.out.println(" في مشكله في تدخيل بيانات الاشتراك");
                    }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BusesController.class.getName()).log(Level.SEVERE, null, ex);
        }
              
       }
    public void insertIntoQuest()
    {
          try{
                int par_id=0;
             
        conn = handler.getConnection();
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT partners.par_id  FROM partners where par_phone1 =\""+txtphone1.getText()+"\"");
            while (resultSet.next()) {
             par_id= resultSet.getInt(1);
            }
              String insertQuery = "INSERT INTO quest (par_id,deadline,reminder) "
                            + "VALUES (?,?,?)";
                    conn = handler.getConnection();
                    ps = conn.prepareStatement(insertQuery);

                    ps.setInt(1, par_id);
                    // pst.setDate(2, java.sql.Date.valueOf(dtDatestart.getValue()));
                    
                   
            if(typeOFproject==1)
            {
                LocalDate a=dtDatestart.getValue();
                
                ps.setDate(2, java.sql.Date.valueOf(a.plusMonths(3)));
                 ps.setDate(3, java.sql.Date.valueOf(a.plusMonths(3).minusDays(7)));
            }
            else if(typeOFproject==2)
            {
               LocalDate a=dtDatestart.getValue();
                
                ps.setDate(2, java.sql.Date.valueOf(a.plusMonths(6)));
                 ps.setDate(3, java.sql.Date.valueOf(a.plusMonths(6).minusDays(7)));
            }
            else if(typeOFproject==3)
            {
                LocalDate a=dtDatestart.getValue();
                
                ps.setDate(2, java.sql.Date.valueOf(a.plusMonths(1)));
                 ps.setDate(3, java.sql.Date.valueOf(a.plusMonths(1).minusDays(7)));
            }
            int success = ps.executeUpdate();
             if(success!=1)
                    {
                        System.out.println(" في مشكله في تدخيل بيانات الكويست");
                    }
          } catch (SQLException ex) {
            Logger.getLogger(BusesController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
  public static String formatt(float c) {

        DecimalFormat myFormatter = new DecimalFormat("##,###,###");
        float iAsFloat = Float.parseFloat("" + c);
        // System.out.println("long i = " + c + " becomes " + myFormatter.format(iAsFloat));
        return myFormatter.format(iAsFloat);

    }
  
  
  // الفانكشن بتاعت فالديت الانبوت 
  
   private void checkempty() {
        if (txtName.getText().isEmpty()) {
            if (txtName.getStyleClass().size() < 4) {
                txtName.getStyleClass().add("empty");
            }
        } else {
            txtName.getStyleClass().remove("empty");
        }

        if (txtphone1.getText().equals("")) {
            if (txtphone1.getStyleClass().size() < 4) {
                txtphone1.getStyleClass().add("empty");
            }
        } else {
            txtphone1.getStyleClass().remove("empty");
        }

        if (txtaddress.getText().isEmpty()) {
            if (txtaddress.getStyleClass().size() < 4) {
                txtaddress.getStyleClass().add("empty");
            }
        } else {
            txtaddress.getStyleClass().remove("empty");
        }

        if (txtsalary.getText().isEmpty()) {
            if (txtsalary.getStyleClass().size() < 4) {
                txtsalary.getStyleClass().add("empty");
            }
        } else {
            txtsalary.getStyleClass().remove("empty");
        }

        if (txtfin_share.getText().isEmpty()) {
            if (txtfin_share.getStyleClass().size() < 4) {
                txtfin_share.getStyleClass().add("empty");
            }
        } else {
            txtfin_share.getStyleClass().remove("empty");
        }

        if (txtfirst_paid.getText().isEmpty()) {
            if (txtfirst_paid.getStyleClass().size() < 4) {
                txtfirst_paid.getStyleClass().add("empty");
            }
        } else {
            txtfirst_paid.getStyleClass().remove("empty");
        }

        if (comboproject.getSelectionModel().isEmpty()) {
            if (comboproject.getStyleClass().size() < 5) {
                comboproject.getStyleClass().add("empty");
            }
        } else {
            comboproject.getStyleClass().remove("empty");
        }

      // if (dtDatestart.getValue().equals("")) {
           // if (dtDatestart.getStyleClass().size() < 4) {
            //   dtDatestart.getStyleClass().add("empty");
         //   }
      //  } else {
       //     dtDatestart.getStyleClass().remove("empty");
      //  }
    }

    //بغير الوان الحاجه الفيلد ال مملهاش لما يبدا يملاها تاني 
    private void write() {
        txtName.getStyleClass().remove("empty");
        txtphone1.getStyleClass().remove("empty");
        txtaddress.getStyleClass().remove("empty");
        txtsalary.getStyleClass().remove("empty");
        txtfin_share.getStyleClass().remove("empty");
        txtfirst_paid.getStyleClass().remove("empty");
        comboproject.getStyleClass().remove("empty");
       // dtDatestart.getStyleClass().remove("empty");

    }

    private boolean isInt(JFXTextField input) {
        try {
            double age = Double.parseDouble(input.getText());
            //  System.out.println("User is: " + age);
            return true;
        } catch (NumberFormatException e) {

            //  System.out.println("Error: " + message + " is not a number");
            return false;
        }
    }

    private boolean checkinput() {
        int counter = 0;
        if (!isInt(txtfin_share)) {
            if (txtfin_share.getStyleClass().size() < 4) {
                txtfin_share.getStyleClass().add("empty");
            }
            counter++;
        }
        if (!isInt(txtfirst_paid)) {
            if (txtfirst_paid.getStyleClass().size() < 4) {
                txtfirst_paid.getStyleClass().add("empty");
            }
            counter++;
        }
       
        if (counter > 0) {
            return true;
        } else {
            return false;
        }

    }

    // search for  partner By phone
    
     @FXML
     private void search(Event event )
    {
        buildDriversTableSearch();
    }
    
    private void buildDriversTableSearch() {
        try {
            String project_name="";
             String ins_money="";
            String query = "SELECT par_id, par_name, job, par_phone1, par_address, fin_Share, paid, total_of_ins, ins_paid FROM partners where par_phone1 like \"%"+txtphonesearch.getText().trim()+"%\"";
            conn = handler.getConnection();
            data = FXCollections.observableArrayList();
            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {
                 
                int id = set.getInt(1);
                String names = set.getString(2);
               // System.out.println(names);
                String sql="SELECT pro_name from projects where pro_id =(SELECT pro_id from enrollment where par_id="+ id +")"; 
               
                ResultSet set2=conn.createStatement().executeQuery(sql);
                
                 while (set2.next()){
                 project_name= set2.getString(1);
                 }
                
                String job = set.getString(3);
                String phone= set.getString(4);
                String address = set.getString(5);  
                String fin_share = set.getString(6);
                String paid = set.getString(7);
                int total_of_ins = set.getInt(8);
                int ins_paid = set.getInt(9);
                  
                String sql2= "SELECT money from total_ins where par_id="+id+" and number_of_ins= 1";
                ResultSet set3=conn.createStatement().executeQuery(sql2);
                while(set3.next()){
                 ins_money= set3.getString(1);
                
                }
                  
                data.add(new Partners(id,names, phone, address, job, project_name, formatt(Float.parseFloat(fin_share)), formatt(Float.parseFloat(paid)), total_of_ins,ins_paid,formatt(Float.parseFloat(ins_money))));
               
            }
         
            colName.setCellValueFactory(new PropertyValueFactory<>("names"));
     
            colphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
            coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));

            coljob.setCellValueFactory(new PropertyValueFactory<>("job"));
          
            colpro_name.setCellValueFactory(new PropertyValueFactory<>("pro_name"));
         
            coltotal_fin.setCellValueFactory(new PropertyValueFactory<>("total_fin"));
           
            coltotal_paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
           
            coltotal_number.setCellValueFactory(new PropertyValueFactory<>("total_numberOfins"));
           
            colnumber_ofpaid.setCellValueFactory(new PropertyValueFactory<>("paid_ins"));
           
            colmoneyof_ins.setCellValueFactory(new PropertyValueFactory<>("moneyof_ins"));

            tablepartnersInfo.setItems(null);
            tablepartnersInfo.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(DriversController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
     @FXML
    private void bookTransit(Event event)  {
        // System.out.println("Selected Value" );
        Partners s= new Partners();
        s=tablepartnersInfo.getSelectionModel().getSelectedItem();
       //  System.out.println(s.getNames());
         
       //  tablepartnersInfo.getSelectionModel().setCellSelectionEnabled(true);
        //ObservableList selectedCells = tablepartnersInfo.getSelectionModel().getSelectedCells();
         //TablePosition tablePosition = (TablePosition) selectedCells.get(0);
         //Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
         //System.out.println("Selected Value" + val);
         
     //   selectedCells.addListener(new ListChangeListener() {
    //@Override
    //public void onChanged(Change c) {
      //  TablePosition tablePosition = (TablePosition) selectedCells.get(0);
       // Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
        //System.out.println("Selected Value" + val);
    //}
//});
    }
    
    //delete partners and update print in excel and paid ins
   // tablepartnersInfo
      @FXML
    private void Selectedtable(Event event)  {
       //  System.out.println("Selected Value" );
        Partners s= new Partners();
        s=tablepartnersInfo.getSelectionModel().getSelectedItem();
        nameselectedforins=s.getNames();
        IDselectedforins=s.getid();
           //System.out.println(nameselectedforins);
         //System.out.println(IDselectedforins);  
    }
    
     @FXML
     private void switchtopay(ActionEvent event )
    {
        payController1.payId=IDselectedforins;
        payController1.paypartnersname=nameselectedforins;
        if(nameselectedforins!=""){
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
          
    }}
        
        //delete partners
     @FXML
    private void deletepartners(ActionEvent event) throws SQLException  {
       
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                 
                alert.setTitle("Confirmation Dialog");
               // alert.setHeaderText("Look, a Confirmation Dialog");
                alert.setContentText("هل تريد حذف العميل ؟");        
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    try {
                        
                 conn = handler.getConnection();
            String query = "delete  FROM partners where par_id="+IDselectedforins;
                   // System.out.println(query);
           PreparedStatement rst = conn.prepareStatement(query);
           rst.executeUpdate(); 
            buildDriversTable();
                    } catch (SQLException ex) {
                         Logger.getLogger(DriversController.class.getName()).log(Level.SEVERE, null, ex);
                    }
             
                } else {
                      // System.out.println("you choose no"); 
            }
            }
    // print to excal
    
         @FXML
    private void print(ActionEvent event) throws SQLException, FileNotFoundException, IOException  {
                TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("print as ExcelSheet");
        dialog.setContentText("Please enter file name:");
        
        

// Traditional way to get the response value.
Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                     

             XSSFWorkbook wb= new XSSFWorkbook();
             XSSFSheet sheet=wb.createSheet("partners sheet");
             XSSFRow header=sheet.createRow(0);
             header.createCell(0).setCellValue("اسم العميل");
             header.createCell(1).setCellValue("رقم التلفون");
             header.createCell(2).setCellValue("العنوان");
             header.createCell(3).setCellValue("الوظيفه");
             header.createCell(4).setCellValue("اسم المشروع");
             header.createCell(5).setCellValue("اجمالي المبلغ");
                  header.createCell(6).setCellValue("اجمالى المدفوع");
             header.createCell(7).setCellValue("عدد الاقساط");
             header.createCell(8).setCellValue("عدد الاقساط المدفوعه");
             header.createCell(9).setCellValue("قيمة القسط");
             sheet.autoSizeColumn(0);
              sheet.autoSizeColumn(1);
              sheet.autoSizeColumn(2);
              sheet.autoSizeColumn(3);
              sheet.autoSizeColumn(4);
              sheet.autoSizeColumn(5);
              sheet.autoSizeColumn(6);
              sheet.autoSizeColumn(7);
               sheet.autoSizeColumn(8);
               sheet.autoSizeColumn(9);
               sheet.setZoom(130);
             for( int i = 0; i< tablepartnersInfo.getItems().size(); i++) {
                 XSSFRow row= sheet.createRow(i+1);
                 row.createCell(0).setCellValue(data.get(i).getNames());
                 row.createCell(1).setCellValue(data.get(i).getPhone());
                 row.createCell(2).setCellValue(data.get(i).getaddress());
                 row.createCell(3).setCellValue(data.get(i).getjob());
                 row.createCell(4).setCellValue(data.get(i).getpro_name());
                 row.createCell(5).setCellValue(data.get(i).gettotal_fin());
                 row.createCell(6).setCellValue(data.get(i).getpaid());
                 row.createCell(7).setCellValue(data.get(i).gettotal_numberOfins());
                 row.createCell(8).setCellValue(data.get(i).getpaid_ins());
                  row.createCell(9).setCellValue(data.get(i).getmoneyof_ins());

            }
             FileOutputStream fileout=new FileOutputStream(result.get()+".xlsx");
             wb.write(fileout);
             fileout.close();
                }
            }
    //update 
    
}

    
    
    
    
    
    


