import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Driver_Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("driver");
		Properties conPro = new Properties();
		conPro.put("user", "root");
		conPro.put("password", "");
		DriverManager.getConnection("jdbc:mysql://localhost:3306", conPro);
	}

}
