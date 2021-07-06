package workerconsole;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnection {

	public static Connection doConnect()
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/tailordb","root","bce");
			System.out.println("Connecteeeedddddd");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			doConnect();
	}

}
