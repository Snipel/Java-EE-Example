package ds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MainLookup {

	/*
	 * Man muss die JDBC Treiber mitliefern. -> Code der Implementation muss vorliegen
	 * In Praxis: Man liefert im Client einfach alle Treiber mit, damit man im Falle eines
	 * Datenbankwechsels nichts machen muss. 
	 * 
	 * Vorteil von DataSources bleibt, aber man kann bei Java nicht beliebig Code über das Netz schicken
	 * 
	 */
	public static void main(String[] args) throws NamingException, SQLException {
		/*
		 * Properties und Context werden analog zum MainBind.java erstellt
		 */
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
		props.put(Context.PROVIDER_URL, "rmi://localhost:1099");
		
		Context ctx = new InitialContext(props);
		
		/*
		 * Hier wird normale DataSource verwendet, da man nicht weiß, 
		 * welche Implementation es ist.
		 */
		DataSource ds = (DataSource) ctx.lookup("myFBrmi");
		
		// -----------------------------------------------------------------------------
		
		/*
		 * Implementation aus Aufgabe 1 (s. Uebung1 Projekt)
		 */
		Connection c = ds.getConnection();
		PreparedStatement s = c.prepareStatement("SELECT * FROM flug WHERE flugnr = ?");
		s.setString(1, "LH222");
		ResultSet rs = s.executeQuery();
		rs.next();
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.println(rs.getMetaData().getColumnLabel(i) + ": " + rs.getString(i));
		}
		
		
		
		System.out.println("Fertig ...");
	}

}
