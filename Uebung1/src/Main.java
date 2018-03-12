import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		
		// Ausgeben der Flugdaten
		PreparedStatement s = c.prepareStatement("SELECT * FROM flug WHERE flugnr = ?");
		s.setString(1, "LH222");
		ResultSet rs = s.executeQuery();
		rs.next();
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.println(rs.getMetaData().getColumnLabel(i) + ": " + rs.getString(i));
		}
		
		// Ausgeben der gebuchten Flüge eines Passagiers
		System.out.println("\nGEBUCHTE FLUEGE:\n---------------------");
		PreparedStatement s2 = c.prepareStatement("SELECT * FROM buchung WHERE name = ?");
		s2.setString(1, "Weber");
		ResultSet rs2 = s2.executeQuery();
		while (rs2.next()) {
			System.out.println("\nBUCHUNG: ");
			for (int i = 1; i <= rs2.getMetaData().getColumnCount(); i++) {
				System.out.println(rs2.getMetaData().getColumnLabel(i) + ": " + rs2.getString(i));
			}
		}
		
		// Passagierliste eines FLugs
		
		
		
		
		System.out.println("Ende Main ...");
	}

}
