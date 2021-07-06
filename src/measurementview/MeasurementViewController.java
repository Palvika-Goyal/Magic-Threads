/**
 * Sample Skeleton for 'MeasurementView.fxml' Controller Class
 */

package measurementview;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import workerconsole.MysqlConnection;

public class MeasurementViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;


    @FXML // fx:id="img"
    private ImageView img; // Value injected by FXMLLoader
    
    @FXML // fx:id="tblMeasure"
    private TableView<MeasurementBean> tblMeasure; // Value injected by FXMLLoader

    @FXML
    void doShowAll(ActionEvent event) {
    	
    	TableColumn<MeasurementBean, Integer> idCol=new TableColumn<MeasurementBean, Integer>("Order id");
       	idCol.setCellValueFactory(new PropertyValueFactory<MeasurementBean,Integer>("oid"));
    	idCol.setMinWidth(100);
    	
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
    	
    	TableColumn<MeasurementBean, String> measureCol=new TableColumn<MeasurementBean, String>("Measurement");
    	measureCol.setCellValueFactory(new PropertyValueFactory<MeasurementBean,String>("measurement"));
    	measureCol.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, Date> dateCol=new TableColumn<MeasurementBean, Date>("Date of Order");
    	dateCol.setCellValueFactory(new PropertyValueFactory<MeasurementBean,Date>("doo"));
    	dateCol.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, Date> dateCol1=new TableColumn<MeasurementBean, Date>("Date of delivery");
    	dateCol1.setCellValueFactory(new PropertyValueFactory<MeasurementBean,Date>("dor"));
    	dateCol1.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, Integer> amtCol=new TableColumn<MeasurementBean, Integer>("Amount");
       	amtCol.setCellValueFactory(new PropertyValueFactory<MeasurementBean,Integer>("amount"));
    	amtCol.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, String> wishCol=new TableColumn<MeasurementBean, String>("Wish");
       	wishCol.setCellValueFactory(new PropertyValueFactory<MeasurementBean,String>("wish"));
    	wishCol.setMinWidth(100);
    	
    	TableColumn<MeasurementBean, Integer> statCol=new TableColumn<MeasurementBean, Integer>("Status");
       	statCol.setCellValueFactory(new PropertyValueFactory<MeasurementBean,Integer>("status"));
    	statCol.setMinWidth(100);
    	
    	
    	tblMeasure.getColumns().clear();
    	tblMeasure.getColumns().addAll(idCol,cnameCol,mobCol,dressCol,splCol,measureCol,dateCol,dateCol1,amtCol,wishCol,statCol);
    	
    	ObservableList<MeasurementBean> ary=fetchAllRecords();
    	tblMeasure.setItems(ary);

    }

    
    Connection con;    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tblMeasure != null : "fx:id=\"tblMeasure\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert img != null : "fx:id=\"img\" was not injected: check your FXML file 'MeasurementView.fxml'.";

        con=MysqlConnection.doConnect();
        File file=new File("D:\\My PC (DESKTOP-2TJTN45)\\Downloads\\tailor3.png");
    	img.setImage(new Image(file.toURI().toString()));

    }
    
    PreparedStatement pst;
    ObservableList<MeasurementBean> fetchAllRecords()
    {
    ObservableList<MeasurementBean> ary=FXCollections.observableArrayList();
    	try{
    		pst=con.prepareStatement("select * from measurements");//0   -   >1
    		ResultSet table= pst.executeQuery();
    		
    		while(table.next())
    		{
    			int id=table.getInt("oid");
    			String name= table.getString("custname");
    			String mob=table.getString("custmobile");
    			String dresss=table.getString("dress");
    			String spll=table.getString("spl");
    			String measure=table.getString("measurement");
    			Date dos=table.getDate("doo");
    			Date dod=table.getDate("dor");
    			int amt=table.getInt("amount");
    			String wishh=table.getString("wish");
    			int stat=table.getInt("status");
    	
    			MeasurementBean obj=new MeasurementBean(id, name, mob,dresss,spll,measure, dos,dod,amt,wishh,stat);
    			ary.add(obj);
    		}
    		
    		
    	}
    	catch(Exception exp)
    	{
    		exp.printStackTrace();
    	}
    	return ary;
    }
}
