/**
 * Sample Skeleton for 'LoginView.fxml' Controller Class
 */

package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class LoginViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="pwd"
    private PasswordField pwd; // Value injected by FXMLLoader

    @FXML
    void doLogin(ActionEvent event) {
    	if(pwd.getText().equals("Palvika@1"))
    	{
    		try{
        		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("dashboard/DashboardView.fxml")); 
    			Scene scene = new Scene(root);
    			Stage stage=new Stage();
    			stage.setScene(scene);
    			stage.show();
    		
    		}        
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert pwd != null : "fx:id=\"pwd\" was not injected: check your FXML file 'LoginView.fxml'.";

    }
}
