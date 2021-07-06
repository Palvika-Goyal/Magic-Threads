/**
 * Sample Skeleton for 'MeasureGooglerView.fxml' Controller Class
 */

package measurementgoogler;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import workerconsole.MysqlConnection;

public class MeasureGooglerViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboWorker"
    private ComboBox<String> comboWorker; // Value injected by FXMLLoader

    @FXML // fx:id="comboStatus"
    private ComboBox<String> comboStatus; // Value injected by FXMLLoader

    @FXML // fx:id="txtMob"
    private TextField txtMob; // Value injected by FXMLLoader

    @FXML // fx:id="datee"
    private DatePicker datee; // Value injected by FXMLLoader


    @FXML // fx:id="tblShow"
    private TableView<MeasurementBean> tblShow; // Value injected by FXMLLoader
    PreparedStatement pst;
    
    void fillColumns()
    {
    	
    	TableColumn<MeasurementBean, Integer> idCol=new TableColumn<MeasurementBean, Integer>("Order id");
       	idCol.setCellValueFactory(new PropertyValueFactory<MeasurementBean,Integer>("oid"));
    	idCol.setMinWidth(80);
    	
    	TableColumn<MeasurementBean, String> cnameCol=new TableColumn<MeasurementBean, String>("Customer Name");
       	cnameCol.setCellValueFactory(new PropertyValueFactory<MeasurementBean,String>("custname"));
    	cnameCol.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, String> mobCol=new TableColumn<MeasurementBean, String>("Customer Mobile");
       	mobCol.setCellValueFactory(new PropertyValueFactory<MeasurementBean,String>("custmobile"));
    	mobCol.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, String> dressCol=new TableColumn<MeasurementBean, String>("Dress");
    	dressCol.setCellValueFactory(new PropertyValueFactory<MeasurementBean,String>("dress"));
    	dressCol.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, String> splCol=new TableColumn<MeasurementBean, String>("Worker");
       	splCol.setCellValueFactory(new PropertyValueFactory<MeasurementBean,String>("spl"));
    	splCol.setMinWidth(100);
    	    	
    	TableColumn<MeasurementBean, Date> dateCol=new TableColumn<MeasurementBean, Date>("Date of Order");
    	dateCol.setCellValueFactory(new PropertyValueFactory<MeasurementBean,Date>("doo"));
    	dateCol.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, Date> dateCol1=new TableColumn<MeasurementBean, Date>("Date of delivery");
    	dateCol1.setCellValueFactory(new PropertyValueFactory<MeasurementBean,Date>("dor"));
    	dateCol1.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, Integer> amtCol=new TableColumn<MeasurementBean, Integer>("Amount");
       	amtCol.setCellValueFactory(new PropertyValueFactory<MeasurementBean,Integer>("amount"));
    	amtCol.setMinWidth(100);
    	
    	tblShow.getColumns().clear();
    	tblShow.getColumns().addAll(idCol,cnameCol,mobCol,dressCol,splCol,dateCol,dateCol1,amtCol);
    	
    }
    
    ObservableList<MeasurementBean> fetchAllRecords(PreparedStatement pst)
    {
    ObservableList<MeasurementBean> ary=FXCollections.observableArrayList();
    	try{
    		
    		ResultSet table= pst.executeQuery();
    		
    		while(table.next())
    		{
    			int id=table.getInt("oid");
    			String name= table.getString("custname");
    			String mob=table.getString("custmobile");
    			String dresss=table.getString("dress");
    			String spll=table.getString("spl");
    			
    			Date dos=table.getDate("doo");
    			Date dod=table.getDate("dor");
    			int amt=table.getInt("amount");
    			
    			MeasurementBean obj=new MeasurementBean(id, name, mob,dresss,spll, dos,dod,amt);
    			ary.add(obj);
    	
    		}
    		
    		
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    	return ary;

    }

    
    

    @FXML
    void doCust(ActionEvent event) {
    	fillColumns();
    	PreparedStatement pst=null;
    	try{
    		pst=con.prepareStatement("select * from measurements where custmobile=?");//0   -   >1
    		pst.setString(1, txtMob.getText());
    		
    	}
    	catch(Exception exp){}
    	ObservableList<MeasurementBean> ary=fetchAllRecords(pst);
    	tblShow.setItems(ary);

    }

    @FXML
    void doFindWorker(ActionEvent event) {  	
    	fillColumns();
    	PreparedStatement pst=null;
    	try{
    		pst=con.prepareStatement("select * from measurements where spl=? and status=? ");//0   -   >1
    		pst.setString(1, comboWorker.getSelectionModel().getSelectedItem());
    		pst.setInt(2, comboStatus.getSelectionModel().getSelectedIndex()-1);
    	}
    	catch(Exception exp){
    		exp.printStackTrace();
    	}
    	ObservableList<MeasurementBean> ary=fetchAllRecords(pst);
    	tblShow.setItems(ary);

    }

    @FXML
    void doGetAll(ActionEvent event) {
    	fillColumns();
    	PreparedStatement pst=null;
    	try{
    		pst=con.prepareStatement("select * from measurements where dor<=? and status=?");//0   -   >1
    		pst.setDate(1, Date.valueOf(datee.getValue()));
    		pst.setInt(2,1);

    	}
    	catch(Exception exp){}
    	ObservableList<MeasurementBean> ary=fetchAllRecords(pst);
    	tblShow.setItems(ary);
    }

    @FXML
    void doShowAll(ActionEvent event) {
    	fillColumns();
    	PreparedStatement pst=null;
    	try{
    		pst=con.prepareStatement("select * from measurements");//0   -   >1
    	
    	}
    	catch(Exception exp){}
    	ObservableList<MeasurementBean> ary=fetchAllRecords(pst);
    	tblShow.setItems(ary);
    }

  
    void fillCombo()
    {
    	ArrayList<String> aryList=new ArrayList<String>(Arrays.asList("Select","Delivered","In Progress","Completed"));
    	comboStatus.getItems().addAll(aryList);
    	comboStatus.getSelectionModel().select(0);
    	try
    	{
    		pst=con.prepareStatement("select distinct spl from measurements");
        	ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
    			String workspl=table.getString("spl");
    			comboWorker.getItems().add(workspl);
    		}
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    	
    }
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Its Result");
    	alert.setContentText(msg);
    	alert.show();
    }
    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboWorker != null : "fx:id=\"comboWorker\" was not injected: check your FXML file 'MeasureGooglerView.fxml'.";
        assert comboStatus != null : "fx:id=\"comboStatus\" was not injected: check your FXML file 'MeasureGooglerView.fxml'.";
        assert txtMob != null : "fx:id=\"txtMob\" was not injected: check your FXML file 'MeasureGooglerView.fxml'.";
        assert datee != null : "fx:id=\"datee\" was not injected: check your FXML file 'MeasureGooglerView.fxml'.";
        assert tblShow != null : "fx:id=\"tblShow\" was not injected: check your FXML file 'MeasureGooglerView.fxml'.";
        con=MysqlConnection.doConnect();
        fillCombo();
    }
}

