/**
 * Sample Skeleton for 'MeasurementView.fxml' Controller Class
 */

package measurement;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.ResourceBundle;

import com.mysql.jdbc.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import workerconsole.MysqlConnection;
import java.time.temporal.ChronoUnit;
public class MeasurementViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtName"
    private TextField txtName; // Value injected by FXMLLoader

    @FXML // fx:id="txtMob"
    private TextField txtMob; // Value injected by FXMLLoader

   
    @FXML // fx:id="comboDay"
    private ComboBox<Integer> comboDay; // Value injected by FXMLLoader

    @FXML // fx:id="txtMeasure"
    private TextArea txtMeasure2; // Value injected by FXMLLoader

    @FXML // fx:id="comboCategory"
    private ComboBox<String> comboCategory; // Value injected by FXMLLoader

    @FXML // fx:id="comboSpl"
    private ComboBox<String> comboSpl; // Value injected by FXMLLoader

    @FXML // fx:id="datee"
    private DatePicker datee; // Value injected by FXMLLoader

    @FXML
    private TextField txtPrice;
    
    @FXML // fx:id="txtSpl"
    private TextField txtSpl; // Value injected by FXMLLoader

    @FXML // fx:id="img"
    private ImageView img; // Value injected by FXMLLoader
    PreparedStatement pst;

    
    @FXML
    void doFillOther(ActionEvent event) {
  	String category=comboCategory.getSelectionModel().getSelectedItem();   //Category selected
  	comboSpl.getSelectionModel().clearSelection();					//workers combobox clear
	comboSpl.getItems().clear();
  	try {
  		
//  		//Using split
   	pst=con.prepareStatement("select spl,wname from workers");   
  		ResultSet table= pst.executeQuery();
  		while(table.next())  
   		{
   			String special=table.getString("spl");				//fetch  special textField
   			String[] sa=special.split(",");					   //split
   			for(String k: sa)									
   			{
   				if(k.equals(category))                        //if matched fill workers name in combobox
   				{
   						String wnam=table.getString("wname");
   						comboSpl.getItems().add(wnam);
   				}
   			} 			
   		}
  		//using like query
//  	  	String category=comboCategory.getSelectionModel().getSelectedItem();   //Category selected
//
//  		pst=con.prepareStatement("select wname from workers where spl like ?");
//  			pst.setString(1, "%"+category+"%");
//  			ResultSet table=pst.executeQuery();
//  			while(table.next())
//  			{
//  				String wnam=table.getString("wname");
//  				comboSpl.getItems().add(wnam);
//  				
//  			}
  		//To fill Price textbox
  		if(category.equals("Pant"))
  			txtPrice.setText(400+"");
  		else if(category.equals("Shirt"))
  			txtPrice.setText(500+"");
  		else if(category.equals("Suit"))
  			txtPrice.setText(1000+"");
  		else
  			txtPrice.setText(0+"");
  	}
  	catch (Exception e) {
		// TODO: handle exception
  		e.printStackTrace();
	}
  
    	
    }
 
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //Format specify
	String data = df.format(new java.util.Date());  //current date
	LocalDate date = LocalDate.parse(data); //convert string to Localdate

    @FXML
    void doSelectDays(ActionEvent event) {
	    LocalDate dateNew=datee.getValue(); //datee = id of datepicker
	    if(dateNew==null)
	    	return;
		long noOfDaysBetween = ChronoUnit.DAYS.between(date, dateNew);
		int days=Integer.parseInt(noOfDaysBetween+"");
		comboDay.getSelectionModel().select(days);
    }
    
    
    
    
        @FXML
    void doSelectDate(ActionEvent event) {
        int n=comboDay.getSelectionModel().getSelectedItem();  //No. of days selected
    	//add 5 days
    	LocalDate date2 = date.plusDays(n);   //add no. of days
    	System.out.println("Date "+date+" plus 5 days is "+date2);
    	datee.setValue(date2);      //show in datepicker
      
    }
    
    @FXML
    void doFind(ActionEvent event) {
    	try{
			pst=con.prepareStatement("select * from measurements where custname=? and dress=?"); //0 or 1
			pst.setString(1, txtName.getText());
			pst.setString(2, comboCategory.getSelectionModel().getSelectedItem());
			ResultSet table=pst.executeQuery();
			boolean jasus=false;
			while(table.next())
			{
				jasus=true;
				
				String mob=table.getString("custmobile");
			
				String workspl=table.getString("spl");
				String measure=table.getString("measurement");
				int amt=table.getInt("amount");
				String wishh=table.getString("wish");
				txtMob.setText(mob);
				comboSpl.getSelectionModel().select(workspl);
				txtMeasure2.setText(measure);
				txtPrice.setText(amt+"");
				txtSpl.setText(wishh);		
				datee.setValue(null);
    			}		
			if(jasus==false)
				showMsg("Invalid");
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}



    }

    @FXML
    void doNew(ActionEvent event) {
    	txtName.setText(null);
    	txtMeasure2.setText(null);
    	txtMob.setText(null);
    	txtPrice.setText(null);
    	txtSpl.setText(null);
    	comboSpl.getSelectionModel().clearSelection();
    	comboSpl.getItems().clear();
     	
     	comboCategory.getSelectionModel().select(0);
     	comboDay.getSelectionModel().select(0);
     	datee.setValue(null);
    }

    @FXML
    void doSave(ActionEvent event) {
    	try {
    		
			//In Parameters
    		pst=con.prepareStatement("insert into measurements values (?,?,?,?,?,?,current_date(),?,?,?,1)",Statement.RETURN_GENERATED_KEYS);
    		pst.setString(1, null);
    		pst.setString(2, txtName.getText());
    		pst.setString(3, txtMob.getText());
    		pst.setString(4,comboCategory.getSelectionModel().getSelectedItem());
    		pst.setString(5, comboSpl.getSelectionModel().getSelectedItem());
    		pst.setString(6,txtMeasure2.getText());
    		pst.setDate(7, Date.valueOf(datee.getValue()));
			pst.setInt(8, Integer.parseInt(txtPrice.getText()));
    		pst.setString(9,txtSpl.getText());
    		pst.executeUpdate();
    		showMsg("Saved!!!!!");
    		ResultSet res = pst.getGeneratedKeys();
    	      while (res.next()) {
    	         showMsg("Order ID : "+res.getString(1));
    	      }
    	} 
    	catch (NullPointerException e) {
			// TODO: handle exception
    		showMsg("All fields required");
		}
    	catch (SQLException e) {

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
    
void fillCategoryCombo()
{
	ArrayList<String> aryList=new ArrayList<String>(Arrays.asList("Select","Suit","Pant","Shirt"));

	
	comboCategory.getItems().addAll(aryList);
	comboCategory.getSelectionModel().select(0);	
}

void fillDaysCombo()
{
	ArrayList<Integer> aryList=new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,23,24,25,26,27,28,29,30,31));

	
	comboDay.getItems().addAll(aryList);
	comboDay.getSelectionModel().select(0);
}

Connection con;
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert txtMob != null : "fx:id=\"txtMob\" was not injected: check your FXML file 'MeasurementView.fxml'.";
           assert comboDay != null : "fx:id=\"comboDay\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert datee != null : "fx:id=\"datee\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert txtPrice != null : "fx:id=\"txtPrice\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert txtSpl != null : "fx:id=\"txtSpl\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert img != null : "fx:id=\"img\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert comboCategory != null : "fx:id=\"comboCategory\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert comboSpl != null : "fx:id=\"comboSpl\" was not injected: check your FXML file 'MeasurementView.fxml'.";
        assert txtMeasure2 != null : "fx:id=\"txtMeasure2\" was not injected: check your FXML file 'MeasurementView.fxml'.";

        con=MysqlConnection.doConnect();
    	File file=new File("D:\\My PC (DESKTOP-2TJTN45)\\Downloads\\tailor1.png");
    	img.setImage(new Image(file.toURI().toString()));
    	fillCategoryCombo();
    	fillDaysCombo();

    }
}
