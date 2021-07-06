package workertableview;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application 
{
	
 public void start(Stage primaryStage) 
   {
		try {
				Parent root=(Parent)FXMLLoader.load(getClass().getResource("WorkerTableView.fxml")); 
				Scene scene = new Scene(root,1380,698);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Worker Table View");
				primaryStage.show();
		    } 
		catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}

