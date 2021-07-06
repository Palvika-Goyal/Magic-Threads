/**
 * Sample Skeleton for 'DressesReceivedView.fxml' Controller Class
 */

package dressescompleted;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class DressesReceivedViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtid"
    private TextField txtid; // Value injected by FXMLLoader

    @FXML // fx:id="img"
    private ImageView img; // Value injected by FXMLLoader

    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Its Result");
    	alert.setContentText(msg);
    	alert.show();
    }

    PreparedStatement pst;
    @FXML
    void doComplete(ActionEvent event) {
    	try {
			//In Parameters
    		if(txtid.getText()=="")
    			{ 
    			showMsg("Order id is required ");
    			return;
    			}
				pst=con.prepareStatement("update measurements set status=? where oid=?");
				pst.setInt(1,2);
				pst.setInt(2, Integer.parseInt(txtid.getText()));
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
        assert txtid != null : "fx:id=\"txtid\" was not injected: check your FXML file 'DressesReceivedView.fxml'.";
        assert img != null : "fx:id=\"img\" was not injected: check your FXML file 'DressesReceivedView.fxml'.";
        con=MysqlConnection.doConnect();
        File file=new File("D:\\My PC (DESKTOP-2TJTN45)\\Downloads\\new4.jpg");
    	img.setImage(new Image(file.toURI().toString()));

    }
}
