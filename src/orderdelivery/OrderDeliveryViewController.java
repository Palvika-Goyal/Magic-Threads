/**
 * Sample Skeleton for 'OrderDeliveryView.fxml' Controller Class
 */

package orderdelivery;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import workerconsole.MysqlConnection;

public class OrderDeliveryViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtOrder"
    private TextField txtOrder; // Value injected by FXMLLoader

    @FXML // fx:id="txtoid"
    private TextField txtoid; // Value injected by FXMLLoader

    @FXML // fx:id="txtAmt"
    private TextField txtAmt; // Value injected by FXMLLoader

 
    PreparedStatement pst;
    @FXML
    void doAccess(ActionEvent event) {
    	try{
    		if(txtoid.getText()=="")
    		{
    			showMsg("Enter Order Id");
    			return;
    		}
			pst=con.prepareStatement("select dress,amount,status from measurements where oid=?"); //0 or 1
			pst.setInt(1, Integer.parseInt(txtoid.getText()));
			ResultSet table=pst.executeQuery();
			boolean jasus=false;
			while(table.next())
			{
				jasus=true;
				int stat=table.getInt("status");
				if(stat==1)
				{
					showMsg("Order is not Ready");
					return;
				}
    			String dresss= table.getString("dress");
    			String amt=table.getString("amount");
    			txtOrder.setText(dresss);
    			txtAmt.setText(amt);
    				
			}
			
			if(jasus==false)
				showMsg("Invalid !!");
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}


    }
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Its Result");
    	alert.setContentText(msg);
    	alert.show();
    }

    
    @FXML
    void doDeliver(ActionEvent event) {
    	try {
			//In Parameters
    		
				pst=con.prepareStatement("update measurements set status=? where oid=?");
				pst.setInt(1,0);
				pst.setInt(2, Integer.parseInt(txtoid.getText()));
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
        assert txtOrder != null : "fx:id=\"txtOrder\" was not injected: check your FXML file 'OrderDeliveryView.fxml'.";
        assert txtoid != null : "fx:id=\"txtoid\" was not injected: check your FXML file 'OrderDeliveryView.fxml'.";
        assert txtAmt != null : "fx:id=\"txtAmt\" was not injected: check your FXML file 'OrderDeliveryView.fxml'.";
             con=MysqlConnection.doConnect();
       

    }
}
