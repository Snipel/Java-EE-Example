import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Main {

	public static void main(String[] args) throws SQLException {
		System.out.println("Start Main ...");
		
		
		
		MysqlDataSource ds = new MysqlDataSource();
		/*
		 * HINWEISE: 
		 * Wenn die URL falsch angegeben wird, gibt es eine NullPointerException
		 * z.B.:
		 *     ds.setURL("localhost");
		 */
		ds.setUser("root");
		ds.setPassword("root");
		ds.setDatabaseName("flugbuchung");
		ds.setPortNumber(3306);
		ds.setServerName("localhost");
	
		Connection c = ds.getConnection();
		
		c.prepareStatement("SELECT * FROM flugbuchung");
		
		
		
		
		System.out.println("Ende Main ...");
	}

}
