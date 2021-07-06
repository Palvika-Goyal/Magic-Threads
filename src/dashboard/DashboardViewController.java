
/**
 * Sample Skeleton for 'DashboardView.fxml' Controller Class
 */

package dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class DashboardViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    void dobtnAll(String urll)
    {
    	playSong();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource(urll)); 
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
    URL url;
	Media media;
	MediaPlayer mediaplayer;
	
	AudioClip audio;
    void playSong()
    {
    	url=getClass().getResource("a.wav");
		audio=new AudioClip(url.toString());
		audio.play();
   
    	    }
    void playSound()
    {
    	
    	url=getClass().getResource("click.mp3");
		media=new Media(url.toString());
		mediaplayer=new MediaPlayer(media);
		mediaplayer.play();
    }
    @FXML
    void doCompleted(MouseEvent event) {
    	dobtnAll("dressescompleted/DressesReceivedView.fxml");
    	    }

    @FXML
    void doDelivered(MouseEvent event) {
    	dobtnAll("orderdelivery/OrderDeliveryView.fxml");
    }

    @FXML
    void doMeasureRecord(MouseEvent event) {
    	dobtnAll("measurement/MeasurementView.fxml");
    }

    @FXML
    void doOrderGoogler(MouseEvent event) {
    	dobtnAll("measurementgoogler/MeasureGooglerView.fxml");
    }

    @FXML
    void doWorkerConsole(MouseEvent event) {
    	dobtnAll("workerconsole/WorkerConsoleView.fxml");
    }

    @FXML
    void doWorkerGoogler(MouseEvent event) {
    	dobtnAll("workertableview/WorkerTableView.fxml");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
