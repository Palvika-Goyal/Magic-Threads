/**
 * Sample Skeleton for 'WorkerConsoleView.fxml' Controller Class
 */

package workerconsole;

import java.io.File;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class WorkerConsoleViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;


    @FXML // fx:id="txtName"
    private TextField txtName; // Value injected by FXMLLoader
    @FXML // fx:id="txtMob"
    private TextField txtMob; // Value injected by FXMLLoader

    @FXML // fx:id="txtAddress"
    private TextField txtAddress; // Value injected by FXMLLoader

    @FXML // fx:id="comboSpl"
    private ComboBox<String> comboSpl; // Value injected by FXMLLoader

    @FXML // fx:id="txtSpl"
    private TextField txtSpl; // Value injected by FXMLLoader

   
    PreparedStatement pst;

    @FXML // fx:id="img"
    private ImageView img; // Value injected by FXMLLoader

  
    String defaultImg="D:\\My PC (DESKTOP-2TJTN45)\\Downloads\\tailor3.png";
    String pathsInfo="";
    
    @FXML
    void doBrowse(ActionEvent event) {
    	FileChooser filechooser=new FileChooser();
    	File selectedFile=filechooser.showOpenDialog(new Stage());
    	
    	pathsInfo+=selectedFile.getPath();
    	
    	setImage(pathsInfo);
    	
    }
    void setImage(String imgg)
    {
    	File file=new File(imgg);
    	img.setImage(new Image(file.toURI().toString()));
    }

    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Its Result");
    	alert.setContentText(msg);
    	alert.show();
    }
    
    String spl="";
    @FXML
    void doFill(ActionEvent event) {
    	if(comboSpl.getSelectionModel().getSelectedItem()=="Select")
    		return;
		spl+=comboSpl.getSelectionModel().getSelectedItem()+",";
		txtSpl.setText(spl);
    }
    
    @FXML
    void doSave(ActionEvent event) {
    	try {
			//In Parameters
    		pst=con.prepareStatement("insert into workers values (?,?,?,?,?,current_date())");
    		pst.setString(1, txtName.getText());
    		pst.setString(2, txtMob.getText());
    		pst.setString(3, pathsInfo);
    		pst.setString(4, txtAddress.getText());
    		pst.setString(5, txtSpl.getText());
    		pst.executeUpdate();
    		showMsg("Saved!!!!!");

    	} catch (SQLException e) {

    		e.printStackTrace();
    	}
    	
    }


    
    @FXML
    void doDelete(ActionEvent event) {
    	try{
    		pst=con.prepareStatement("delete  from workers where wname=?");
    		pst.setString(1, txtName.getText());
    		int count=pst.executeUpdate();
    		if(count==0)
    				showMsg("Invaild!!");
    		else
    			showMsg("Record Deleted Successfully");
    	}
    	catch(Exception exp)
    	{
    	 exp.printStackTrace();	
    	}
    }

    @FXML
    void doFetch(ActionEvent event) {
    	try{
			pst=con.prepareStatement("select * from workers where wname=?"); //0 or 1
			pst.setString(1, txtName.getText());
			ResultSet table=pst.executeQuery();
			boolean jasus=false;
			while(table.next())
			{
				jasus=true;
				
    			String name= table.getString("wname");
    			String mobile=table.getString("mobile");
    			String path=table.getString("picpath");
    			String add=table.getString("address");
    			String spl=table.getString("spl");
    			Date dos=table.getDate("dor");
    			txtMob.setText(mobile);
    			txtAddress.setText(add);
    			txtSpl.setText(spl);
    			if(path==null)
    			{
    				
    				setImage(defaultImg);
    			}
    			else
    			{	
    				
    				setImage(path);
    			}
    			
    			System.out.println(name+"   "+mobile+"   "+dos+"      "+add);
			}
			
			if(jasus==false)
				showMsg("Invalid !!");
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}

    }

    

    @FXML
    void doNew(ActionEvent event) {
    	txtName.setText(null);
    	txtAddress.setText(null);
    	spl="";
    	txtSpl.setText(spl);
    	txtMob.setText(null);
    	comboSpl.getSelectionModel().select(0);
    	setImage(defaultImg);
    }

       @FXML
    void doUpdate(ActionEvent event) {
    		try {
    			//In Parameters
    				pst=con.prepareStatement("update workers set mobile=?,picpath=?,address=?,spl=?,dor=current_date() where wname=?");
    				
    				pst.setString(5,txtName.getText() );
    				pst.setString(1, txtMob.getText());
    				pst.setString(2, pathsInfo);
    				pst.setString(3, txtAddress.getText());
    				pst.setString(4,txtSpl.getText());
    				setImage(pathsInfo);
    				int count=pst.executeUpdate();//fire the query
    				if(count==0)
    				showMsg("invalid!!");
    				else
    					showMsg("Updated!!!!!");

        		}
        	catch (SQLException e) {
        			e.printStackTrace();
        		}
    }
       
           Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'WorkerConsoleView.fxml'.";
        assert txtMob != null : "fx:id=\"txtMob\" was not injected: check your FXML file 'WorkerConsoleView.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'WorkerConsoleView.fxml'.";
        assert comboSpl != null : "fx:id=\"comboSpl\" was not injected: check your FXML file 'WorkerConsoleView.fxml'.";
        assert txtSpl != null : "fx:id=\"txtSpl\" was not injected: check your FXML file 'WorkerConsoleView.fxml'.";
       
        con=MysqlConnection.doConnect();
    	ArrayList<String> aryList=new ArrayList<String>(Arrays.asList("Select","Suit","Pant","Shirt"));

		
		comboSpl.getItems().addAll(aryList);
		comboSpl.getSelectionModel().select(0);
		setImage(defaultImg);
		
    }
}
