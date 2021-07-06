/**
 * Sample Skeleton for 'WorkerTableView.fxml' Controller Class
 */

package workertableview;

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

import workerconsole.MysqlConnection;

public class WorkerTableViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;


    @FXML // fx:id="tblWorkers"
    private TableView<WorkerBean> tblWorkers; // Value injected by FXMLLoader

    @FXML
    void doShowAll(ActionEvent event) {
    	TableColumn<WorkerBean, String> wnameCol=new TableColumn<WorkerBean, String>("Worker Name");
       	wnameCol.setCellValueFactory(new PropertyValueFactory<WorkerBean,String>("wname"));
    	wnameCol.setMinWidth(100);
    	
    	TableColumn<WorkerBean, String> mobCol=new TableColumn<WorkerBean, String>("Mobile");
    	mobCol.setCellValueFactory(new PropertyValueFactory<WorkerBean,String>("mobile"));//same as bean
    	mobCol.setMinWidth(100);
    	
    	TableColumn<WorkerBean, String> splCol=new TableColumn<WorkerBean, String>("Speciality");
    	splCol.setCellValueFactory(new PropertyValueFactory<WorkerBean,String>("spl"));
    	splCol.setMinWidth(100);
    	
    	TableColumn<WorkerBean, String> addCol=new TableColumn<WorkerBean, String>("Address");
    	addCol.setCellValueFactory(new PropertyValueFactory<WorkerBean,String>("address"));
    	addCol.setMinWidth(100);
    	
    	TableColumn<WorkerBean, String> pathCol=new TableColumn<WorkerBean, String>("Pic Path");
    	pathCol.setCellValueFactory(new PropertyValueFactory<WorkerBean,String>("picpath"));
    	pathCol.setMinWidth(100);
    	
    	
    	TableColumn<WorkerBean, Date> dateCol=new TableColumn<WorkerBean, Date>("Date");
    	dateCol.setCellValueFactory(new PropertyValueFactory<WorkerBean,Date>("dor"));
    	dateCol.setMinWidth(100);
    	tblWorkers.getColumns().clear();
    	tblWorkers.getColumns().addAll(wnameCol,mobCol,pathCol,addCol,splCol,dateCol);
    	
    	ObservableList<WorkerBean> ary=fetchAllRecords();
    	tblWorkers.setItems(ary);
    	
    }

    Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tblWorkers != null : "fx:id=\"tblWorkers\" was not injected: check your FXML file 'WorkerTableView.fxml'.";
        con=MysqlConnection.doConnect();
        
    }
    
    PreparedStatement pst;
    ObservableList<WorkerBean> fetchAllRecords()
    {
    ObservableList<WorkerBean> ary=FXCollections.observableArrayList();
    	try{
    		pst=con.prepareStatement("select * from workers");//0   -   >1
    		ResultSet table= pst.executeQuery();
    		
    		while(table.next())
    		{
    			String name=table.getString("wname");
    			String mob= table.getString("mobile");
    			Date dos=table.getDate("dor");
    			String path=table.getString("picpath");
    			
    			String add=table.getString("address");
    			String spll=table.getString("spl");
    			System.out.println(mob+"  "+name+"   "+dos+"   "+path+"   "+add+"   "+spll);
    			WorkerBean obj=new WorkerBean(name, mob,path,add, spll,dos);
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
