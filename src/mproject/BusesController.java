package mproject;

import classes.DbHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javax.swing.JFormattedTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import static jdk.nashorn.internal.objects.NativeMath.round;
import models.Movements;
import models.PROJECTS;
import models.Partner2;
import static mproject.DriversController.formatt;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * FXML Controller class
 *
 * @author danml
 */
public class BusesController implements Initializable {

    @FXML
    private ImageView imgBack;
    @FXML
    private ImageView imgBus;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnEdit1;
    @FXML
    private StackPane rootPane;
    @FXML
    private JFXTextField txtprojectName;
    @FXML
    private JFXTextField txtbuildingMo;
    @FXML
    private JFXTextField txtaddress;
    @FXML
    private JFXTextField txtEdmoney;
    @FXML
    private JFXTextField txtPriceOfLand;
    @FXML
    private JFXTextField txtyear;
    @FXML
    private Label labempty;

    @FXML
    private JFXTabPane tabparent;
    //@FXML
    // private JFXDatePicker dtDatePurchased;
    @FXML
    private JFXTextField txtInsuranceStatus;
    //@FXML
    //private JFXDatePicker dtDateInsured;
    //@FXML
    //private JFXDatePicker dtExpiryDate;
    @FXML
    private JFXComboBox<String> comboDriverName;
    @FXML
    private JFXComboBox<String> comboProjectName;
    @FXML
    private JFXComboBox<String> projectType;
    @FXML
    private JFXComboBox<String> projectIns;
    @FXML
    private JFXComboBox<String> comboDeparture;
    @FXML
    private JFXComboBox<String> comboDestination;
    private DbHandler handler;
    private Connection conn;
    private Statement stmt;
    private PreparedStatement ps;
    private ResultSet rs;
    private JFXSnackbar s;
    private JFXSnackbar fXSnackbar;
    private JFXSnackbar wrongint;

    private final String pattern = "yyyy-MM-dd";
    @FXML
    private JFXButton btnReset;
    @FXML
    private JFXButton btnprinttoexcel;
    // @FXML
    //private TableView tableBusesList;
    @FXML
    private JFXButton bookTransit;
    @FXML
    private JFXButton btndeleteproject;
    @FXML
    private TableView<Movements> tableMovements;
    @FXML
    private TableColumn<Movements, Integer> colId;
    @FXML
    private TableColumn<Movements, String> colDriver;
    @FXML
    private TableColumn<Movements, String> colOrigin;
    @FXML
    private TableColumn<Movements, String> colDestination;
    @FXML
    private TableColumn<Movements, String> colTime;
    private ObservableList<Movements> data;

    // table for projects show 
    @FXML
    private Tab tabshow;
    @FXML
    private Tab tabcreate;
    @FXML
    private Tab tabpartener;

    @FXML
    private TableView<PROJECTS> tableProjects;
    @FXML
    private TableColumn<PROJECTS, Integer> colIds;
    @FXML
    private TableColumn<PROJECTS, String> colName;
    @FXML
    private TableColumn<PROJECTS, String> colAddress;
    @FXML
    private TableColumn<PROJECTS, String> coltype;
    @FXML
    private TableColumn<PROJECTS, Integer> colNumber;
    @FXML
    private TableColumn<PROJECTS, String> colPrice_OF_LA;
    @FXML
    private TableColumn<PROJECTS, String> colPrice_Of_Build;
    @FXML
    private TableColumn<PROJECTS, String> colEd_Money;
    @FXML
    private TableColumn<PROJECTS, String> coltotal_Money;
    @FXML
    private TableColumn<PROJECTS, Double> coled_precent; 
    private ObservableList<PROJECTS> data1;

    // table for العملا في المشروع 
      @FXML
    private TableView<Partner2> tablepartnersInfo;
    @FXML
    private TableColumn<Partner2, String> col_Name;
    @FXML
    private TableColumn<Partner2, String> colphone;
    @FXML
    private TableColumn<Partner2, String> coladdress;
    @FXML
    private TableColumn<Partner2, String> coljob;
    
    @FXML
    private TableColumn<Partner2, String> coltotal_fin;
    @FXML
    private TableColumn<Partner2, String> coltotal_paid;
    
     @FXML
    private TableColumn<Partner2, Integer> coltotal_number;
     @FXML
    private TableColumn<Partner2, Integer> colnumber_ofpaid;
     @FXML   
    private TableColumn<Partner2, Integer> colnumber_ofnotpaid;
     @FXML
    private TableColumn<Partner2, String> colmoneyof_ins;
     
     private ObservableList<Partner2> data2;
     
     private String deleteselector="";
     private PROJECTS build;
     private int selectid=0;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //   int priv=1;
        // if(priv==1)
        //{
        //  tab2.setDisable(true);
        //tab1.setDisable(true);

        //  }
     
           projectType.getItems().addAll("فيلا");
            projectType.getItems().addAll("عمارة");
            projectIns.getItems().add("ربع سنوي");
             projectIns.getItems().add("نصف سنوي");
             projectIns.getItems().add("شهري");
        //Instantiate database class
        handler = new DbHandler();
        // Date converter for javafx datepicker to conform with sql date format
        //      sqlDateFormatter();
        //Anmate bus movement
        animateBus();
        populateCombooxes();
        buildproTable();
      //  System.out.println("______________________________________");
      //System.out.println(tableProjects.getItems().size());
       //System.out.println("______________________________________");
        //System.out.println(data1.get(0).getNames());
    }

    public static String formatt(float c) {

        DecimalFormat myFormatter = new DecimalFormat("##,###,###");
        float iAsFloat = Float.parseFloat("" + c);
        // System.out.println("long i = " + c + " becomes " + myFormatter.format(iAsFloat));
        return myFormatter.format(iAsFloat);

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
            // Logger.getLogger(DriversController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void animateBus() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        KeyValue kv = new KeyValue(imgBus.xProperty(), 700);
        KeyFrame kf = new KeyFrame(Duration.seconds(10), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    @FXML
    private void saveDriver(ActionEvent event) {

        if (txtprojectName.getText().equals("") || txtEdmoney.getText().isEmpty() || txtPriceOfLand.getText().isEmpty() || txtbuildingMo.getText().isEmpty() || txtyear.getText().isEmpty() || projectType.getSelectionModel().isEmpty() || projectIns.getSelectionModel().isEmpty()) {

            checkempty();
            labempty.setText("الرجاء ادخال جميع البيانات");

        } // هنا لو البيانات كلها موجوده 
        else {
            //  write();
            write();
             labempty.setText("");
            boolean x = checkinput();
            if (x) {
                wrongint = new JFXSnackbar(rootPane);
                wrongint.show("الرجاء ادخال البيانات بشكل صحيح", "exit", 0, (e) -> {
                    wrongint.close();
                });
            } else {
                write();
               

                try {
                    DecimalFormat df = new DecimalFormat("0.00");
                    double edmoneypr = Double.parseDouble(txtEdmoney.getText()) / 100;
                    double total1 = (Double.parseDouble(txtPriceOfLand.getText()) + Double.parseDouble(txtbuildingMo.getText()));
                    double edmoney = edmoneypr * total1;
                    edmoney = Double.parseDouble(df.format(edmoney));
                   // System.out.println(edmoney);
                    double total = total1 + edmoney;

                    String year = projectIns.getSelectionModel().getSelectedItem();
                    // Validation has not been observed,this is justb a prototype.So fuck off if you think i know nothing about validation.
                    String insertQuery = "INSERT INTO projects(pro_name,pro_address,pro_type,price_of_la,edmoney,price_of_build,total_price,number_of_year,ins_id) "
                            + "VALUES (?,?,?,?,?,?,?,?,?)";
                    conn = handler.getConnection();
                    ps = conn.prepareStatement(insertQuery);
                    ps.setString(1, txtprojectName.getText());
                    ps.setString(2, txtaddress.getText());
                    ps.setString(3, projectType.getSelectionModel().getSelectedItem());
                    ps.setString(4, txtPriceOfLand.getText());
                    ps.setString(5, Double.toString(edmoney));
                    ps.setString(6, txtbuildingMo.getText());
                    ps.setString(7, Double.toString(total));
                    ps.setInt(8, Integer.parseInt(txtyear.getText()));
                    if (year == "ربع سنوي") {
                        ps.setInt(9, 1);
                    } else if (year == "نصف سنوي") {
                        ps.setInt(9, 2);
                    } else if (year == "شهري") {
                        ps.setInt(9, 3);
                    }
                    // ps.setDate(6, java.sql.Date.valueOf(dtDateInsured.getValue()));
                    //      ps.setDate(7, java.sql.Date.valueOf(dtExpiryDate.getValue()));

                    int success = ps.executeUpdate();
                    if (success == 1) {
                        fXSnackbar = new JFXSnackbar(rootPane);
                        // fXSnackbar.show("New project saved into the records", 5000);
                        fXSnackbar.show("تم تسجيل مشروع جديد بنجاح", "exit", 200, (c) -> {
                            fXSnackbar.close();
                        });
                        clearFields();
                        buildproTable();
                        populateCombooxes();
                    } else {
                        JFXSnackbar fXSnackbar = new JFXSnackbar(rootPane);
                        fXSnackbar.show("Check validity of data entered and save.I am too lazy to impose validation", 9000);
                    }
                } catch (SQLException ex) {
                    JFXSnackbar nackbar = new JFXSnackbar(rootPane);
                    nackbar.show("ERROR \n" + ex.getMessage() + "\n Contact developer.", 9000);
                }

            }
        }
    }

    @FXML
    private void editDriver(ActionEvent event) {
        JFXSnackbar fXSnackbar = new JFXSnackbar(rootPane);
        fXSnackbar.show("This functionality will not be implemented here. I am tired", 5000);
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
        // dtDateInsured.setConverter(converter);
        // dtDatePurchased.setConverter(converter);
        // dtExpiryDate.setConverter(converter);
    }

    private void clearFields() {
        txtprojectName.setText("");
        txtbuildingMo.setText("");
        txtaddress.setText("");
        projectType.getSelectionModel().clearSelection();
        projectIns.getSelectionModel().clearSelection();
        txtEdmoney.setText("");
        txtyear.setText("");
        txtPriceOfLand.setText("");
        write();

    }

    @FXML
    private void resetFields(ActionEvent event) {
        clearFields();
    }

    private void buildBusesTable() {

        try {
            conn = handler.getConnection();
            String query = "SELECT * FROM project";

            ResultSet rst = conn.createStatement().executeQuery(query);
            ObservableList<ObservableList> data = FXCollections.observableArrayList();
            //  tableBusesList.getColumns().clear();
            int cols = rst.getMetaData().getColumnCount();

            for (int i = 0; i < cols; i++) {
                final int j = i;
                TableColumn col = new TableColumn(rst.getMetaData().getColumnName(i + 1).toUpperCase());
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());

                    }
                });

                col.setPrefWidth(130);
                // tableBusesList.getColumns().addAll(col);
            }
            while (rst.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int k = 1; k <= rst.getMetaData().getColumnCount(); k++) {
                    row.add(rst.getString(k));
                }
                data.add(row);
            }
            //  tableBusesList.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(BusesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void bookTransit(ActionEvent event)  {
        //conn = handler.getConnection();
        //String query = "INSERT  INTO movements(driver,origin,destination,departure_time) VALUES (?,?,?,now())";
        //ps = conn.prepareStatement(query);
        //ps.setString(1, comboDriverName.getSelectionModel().getSelectedItem());
        //ps.setString(2, comboDeparture.getSelectionModel().getSelectedItem());
       // ps.setString(3, comboDestination.getSelectionModel().getSelectedItem());

        //int success = ps.executeUpdate();
        //if (success == 1) {
            //  buildMovementsTable();
        //}
       // tabparent.setSelectionModel(tabshow);
       // SingleSelectionModel<Tab> selectionModel = tabparent.getSelectionModel();

        //selectionModel.select(tabshow);
       comboProjectName.setValue("");
        for ( int i = 0; i<tablepartnersInfo.getItems().size(); i++) {
                 tablepartnersInfo.getItems().clear();
                 
            }
    }
    
    private void buildproTable() {
        try {
            double edperce;
            int i=1;
            String query = "SELECT * FROM projects";
            conn = handler.getConnection();
            data1 = FXCollections.observableArrayList();
            ResultSet set = conn.createStatement().executeQuery(query);
            while (set.next()) {
                //int id = set.getInt(1);
               // System.out.println("1");
                int id= i;
                i++;
                String names = set.getString(2);
                String address = set.getString(3);
                String type = set.getString(4);
                String priceOFLa = set.getString(5);
                String edmoney = set.getString(6);
                String PriceOFBuild = set.getString(7);
                String total_money = set.getString(8);
                int number = set.getInt(10);
                // System.out.println("6");
                 build=new PROJECTS(id, names, address, type, formatt(Float.parseFloat(priceOFLa)), formatt(Float.parseFloat(edmoney)), formatt(Float.parseFloat(PriceOFBuild)), formatt(Float.parseFloat(total_money)), number);
                data1.addAll(build);
                // System.out.println("2");
            }

            //System.out.println(data1.get(i).Total_MoneyProperty().getValue());
        // System.out.println("3");
            colIds.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("names"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
            colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));

            colPrice_OF_LA.setCellValueFactory(new PropertyValueFactory<>("Price_OF_Land"));
            coled_precent.setCellValueFactory(new PropertyValueFactory<>("edprecent"));
            colPrice_Of_Build.setCellValueFactory(new PropertyValueFactory<>("Price_OF_Build"));
            
            colEd_Money.setCellValueFactory(new PropertyValueFactory<>("Ed_Money"));
            coltotal_Money.setCellValueFactory(new PropertyValueFactory<>("Total_Money"));

            tableProjects.setItems(null);
            tableProjects.setItems(data1);
           //  System.out.println("4");
        } catch (SQLException ex) {
            // System.out.println("9");
            Logger.getLogger(BusesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // delete and edit
        
        
        
        
    }

    private void populateCombooxes() {
         
        try {
            conn = handler.getConnection();
            PreparedStatement pst = conn.prepareStatement("SELECT projects.pro_name FROM projects");
            rs = pst.executeQuery();
            while (rs.next()) {
                comboProjectName.getItems().addAll(rs.getString(1));
            }
        } catch (SQLException ex) {
           // System.out.println(ex.getMessage());
        }
        
    }

    private void checkempty() {
        if (txtPriceOfLand.getText().isEmpty()) {
            if (txtPriceOfLand.getStyleClass().size() < 4) {
                txtPriceOfLand.getStyleClass().add("empty");
            }
        } else {
            txtPriceOfLand.getStyleClass().remove("empty");
        }

        if (txtprojectName.getText().equals("")) {
            if (txtprojectName.getStyleClass().size() < 4) {
                txtprojectName.getStyleClass().add("empty");
            }
        } else {
            txtprojectName.getStyleClass().remove("empty");
        }

        if (txtEdmoney.getText().isEmpty()) {
            if (txtEdmoney.getStyleClass().size() < 4) {
                txtEdmoney.getStyleClass().add("empty");
            }
        } else {
            txtEdmoney.getStyleClass().remove("empty");
        }

        if (txtaddress.getText().isEmpty()) {
            if (txtaddress.getStyleClass().size() < 4) {
                txtaddress.getStyleClass().add("empty");
            }
        } else {
            txtaddress.getStyleClass().remove("empty");
        }

        if (txtbuildingMo.getText().isEmpty()) {
            if (txtbuildingMo.getStyleClass().size() < 4) {
                txtbuildingMo.getStyleClass().add("empty");
            }
        } else {
            txtbuildingMo.getStyleClass().remove("empty");
        }

        if (txtyear.getText().isEmpty()) {
            if (txtyear.getStyleClass().size() < 4) {
                txtyear.getStyleClass().add("empty");
            }
        } else {
            txtyear.getStyleClass().remove("empty");
        }

        if (projectType.getSelectionModel().isEmpty()) {
            if (projectType.getStyleClass().size() < 5) {
                projectType.getStyleClass().add("empty");
            }
        } else {
            projectType.getStyleClass().remove("empty");
        }

        if (projectIns.getSelectionModel().isEmpty()) {
            if (projectIns.getStyleClass().size() < 5) {
                projectIns.getStyleClass().add("empty");
            }
        } else {
            projectIns.getStyleClass().remove("empty");
        }
    }

    //بغير الوان الحاجه الفيلد ال مملهاش لما يبدا يملاها تاني 
    private void write() {
        txtPriceOfLand.getStyleClass().remove("empty");
        txtprojectName.getStyleClass().remove("empty");
        txtEdmoney.getStyleClass().remove("empty");
        txtaddress.getStyleClass().remove("empty");
        txtbuildingMo.getStyleClass().remove("empty");
        txtyear.getStyleClass().remove("empty");
        projectType.getStyleClass().remove("empty");
        projectIns.getStyleClass().remove("empty");

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
        if (!isInt(txtyear)) {
            if (txtyear.getStyleClass().size() < 4) {
                txtyear.getStyleClass().add("empty");
            }
            counter++;
        }
        if (!isInt(txtEdmoney)) {
            if (txtEdmoney.getStyleClass().size() < 4) {
                txtEdmoney.getStyleClass().add("empty");
            }
            counter++;
        }
        if (!isInt(txtPriceOfLand)) {
            if (txtPriceOfLand.getStyleClass().size() < 4) {
                txtPriceOfLand.getStyleClass().add("empty");
            }

            counter++;
        }
        if (!isInt(txtbuildingMo)) {
            if (txtbuildingMo.getStyleClass().size() < 4) {
                txtbuildingMo.getStyleClass().add("empty");
            }

            counter++;
        }

        if (counter > 0) {
            return true;
        } else {
            return false;
        }

    }

    private String toString(String edmoney) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
     private void combo(ActionEvent event )
    {
           //System.out.println(tabparent.getSelectionModel().getSelectedItem());
       //  SingleSelectionModel<Tab> selectionModel = tabparent.getSelectionModel();
        //selectionModel.select(tabshow);
    buildDriversTable();
        //System.out.println(comboProjectName.getSelectionModel().getSelectedItem());
    }
   // private void combo(MouseEvent event )
    //{
          
    //buildDriversTable();
      //  System.out.println("goood job");
    //}
    
   
    //اظهار الجدول 
    
    
    private void buildDriversTable() {
        try {
            
             String ins_money="";
            String query = "select partners.par_id, par_name, job, par_phone1, par_address, fin_Share, paid, total_of_ins, ins_paid from  partners inner join enrollment on partners.par_id = enrollment.par_id inner join projects  on projects.pro_id = enrollment.pro_id where projects.pro_name=\""+comboProjectName.getSelectionModel().getSelectedItem()+"\""; 
                          
            conn = handler.getConnection();
            data2 = FXCollections.observableArrayList();
            ResultSet set = conn.createStatement().executeQuery(query);
            
          //   System.out.println(set.next());
            while (set.next()) {
                  
                int id = set.getInt(1);
                
                String names = set.getString(2);
                
                String job = set.getString(3);
                
                String phone= set.getString(4);
                
                String address = set.getString(5);
                
                String fin_share = set.getString(6);
              
                String paid = set.getString(7);
                
                int total_of_ins = set.getInt(8);
                int ins_paid = set.getInt(9);
                int ins_notpaid=total_of_ins-ins_paid; 
                String sql2= "SELECT money from total_ins where par_id="+id+" and number_of_ins= 1";
                ResultSet set3=conn.createStatement().executeQuery(sql2);
                while(set3.next()){
                 ins_money= set3.getString(1);
                
                }
  
                data2.add(new Partner2(names, phone, address, job, formatt(Float.parseFloat(fin_share)), formatt(Float.parseFloat(paid)), total_of_ins,ins_paid,formatt(Float.parseFloat(ins_money)),ins_notpaid));
               
            }
         
            col_Name.setCellValueFactory(new PropertyValueFactory<>("names"));
     
            colphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
            coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));

            coljob.setCellValueFactory(new PropertyValueFactory<>("job"));
          
           // colpro_name.setCellValueFactory(new PropertyValueFactory<>("pro_name"));
         
            coltotal_fin.setCellValueFactory(new PropertyValueFactory<>("total_fin"));
           
            coltotal_paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
           
            coltotal_number.setCellValueFactory(new PropertyValueFactory<>("total_numberOfins"));
           
            colnumber_ofpaid.setCellValueFactory(new PropertyValueFactory<>("paid_ins"));
           colnumber_ofnotpaid.setCellValueFactory(new PropertyValueFactory<>("nopaid_ins"));
            colmoneyof_ins.setCellValueFactory(new PropertyValueFactory<>("moneyof_ins"));

            tablepartnersInfo.setItems(null);
            tablepartnersInfo.setItems(data2);

        } catch (SQLException ex) {
            Logger.getLogger(DriversController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    @FXML
     private void co(Event event )
    {
       // if(txtprojectName.getText().length()==3 ||txtprojectName.getText().length()==7 )
        //{
          //  txtprojectName.setText(txtprojectName.getText()+",");
            
        //}
        //System.out.println(txtprojectName.getText());
    }
    
     
     // delete and edit project 
      @FXML
    private void deleteproject(ActionEvent event) throws SQLException  {
       
                 Alert alert = new Alert(AlertType.CONFIRMATION);
                 
                alert.setTitle("Confirmation Dialog");
               // alert.setHeaderText("Look, a Confirmation Dialog");
                alert.setContentText("هل تريد حذف المشروع ؟");        
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    try {
                        
                             conn = handler.getConnection();
            String query = "delete  FROM projects where pro_name=\""+deleteselector+"\"";
                   // System.out.println(query);
           PreparedStatement rst = conn.prepareStatement(query);
           rst.executeUpdate(); 
            buildproTable();
                    } catch (SQLException ex) {
                         Logger.getLogger(DriversController.class.getName()).log(Level.SEVERE, null, ex);
                    }
             
                } else {
                      // System.out.println("you choose no"); 
            }
            }
      @FXML
    private void Selectedtable(Event event)  {
       //  System.out.println("Selected Value" );
        PROJECTS s= new PROJECTS();
        s=tableProjects.getSelectionModel().getSelectedItem();
        deleteselector=s.getNames();
         //System.out.println(s.getNames());
         
     
    }
    
    
    //print to excal
    
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
             XSSFSheet sheet=wb.createSheet("project sheet");
             XSSFRow header=sheet.createRow(0);
             header.createCell(0).setCellValue("رقم المشروع");
             header.createCell(1).setCellValue("اسم المشروع");
             header.createCell(2).setCellValue("عنوان المشروع");
             header.createCell(3).setCellValue("نوع المشروع");
             header.createCell(4).setCellValue("تكلفةالارض");
             header.createCell(5).setCellValue("تكلفة الانشاء");
                  header.createCell(6).setCellValue("المصاريف الادرايه (%)");
             header.createCell(7).setCellValue("المصاريف الاداريه");
             header.createCell(8).setCellValue("التكلفه الكليه");
             header.createCell(9).setCellValue("مدة المشروع");
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
             for( int i = 0; i< tableProjects.getItems().size(); i++) {
                 XSSFRow row= sheet.createRow(i+1);
                 row.createCell(0).setCellValue(data1.get(i).getId());
                 row.createCell(1).setCellValue(data1.get(i).getNames());
                 row.createCell(2).setCellValue(data1.get(i).getAddress());
                 row.createCell(3).setCellValue(data1.get(i).getType());
                 row.createCell(4).setCellValue(data1.get(i).getPrice_OF_Land());
                 row.createCell(5).setCellValue(data1.get(i).getPrice_OF_Build());
                 row.createCell(6).setCellValue(data1.get(i).getedprecent());
                 row.createCell(7).setCellValue(data1.get(i).getEd_Money());
                 row.createCell(8).setCellValue(data1.get(i).getTotal_Money());
                  row.createCell(9).setCellValue(data1.get(i).getNumber());
                 
                 
            }
     
// The Java 8 way to get the response value (with lambda expression).
            
             FileOutputStream fileout=new FileOutputStream(result.get()+".xlsx");
             wb.write(fileout);
             fileout.close();
                }
            }
    //edit project 
       @FXML
    private void editRequest(ActionEvent event) {
        
    }

    
}
