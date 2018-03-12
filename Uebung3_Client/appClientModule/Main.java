import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Main {
	
	public static void main(String[] args) throws NamingException, SQLException {
		/*
		 * Name aus dem Server-Log kriegen beim Deployment:
		 * java:global/Uebung3/DataSourceProviderBean!ds.DataSourceProviderBeanRemote
		 * 
		 * -> JNDI-Name vom Interface nicht von der Bean nehmen!
		 */

		/*
		 * ACHTUNG: Bei Glassfish wird Provider_URL nicht beachtet stattdessen, die
		 * beiden Properties unten...
		 */
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
		props.put("org.omg.CORBA.ORBInitialHost", "localhost");
		props.put("org.omg.CORBA.ORBInitialPort", "3700");

		Context ctx = new InitialContext(props);

		/*
		 * WICHTIG: Remote-Interface (Business-Interface) aus dem EJB in den Client
		 * kopieren Über Lookup die Bean holen - Casten nicht vergessen
		 */
		ds.DataSourceProviderBeanRemote dsbean = (ds.DataSourceProviderBeanRemote) ctx
				.lookup("java:global/Uebung3/DataSourceProviderBean!ds.DataSourceProviderBeanRemote");

		/*
		 * Nutzung der Bean-Methode ping()
		 */
		System.out.println(dsbean.ping());

		/*
		 * Nutzung der DataSource aus dem EJB
		 * 
		 * Lookup über Programmatisch eingestellte DataSource: 
		 *    - DataSource ds = (DataSource) ctx.lookup("java:global/env/myFBgf");
		 *    
		 * Lookup über administrativ eingestellte DataSource: 
		 *    - DataSource ds = (DataSource) ctx.lookup("JDBC/flugbuchung");
		 */
		
		DataSource ds = (DataSource) ctx.lookup("JDBC/flugbuchung");
		Connection c = ds.getConnection();

		// ----------------------------------------------------------------------------------
		
		/*
		 * Implementation aus Aufgabe 1 (s. Uebung1 Projekt)
		 */
		PreparedStatement s = c.prepareStatement("SELECT * FROM flug WHERE flugnr = ?");
		s.setString(1, "LH222");
		ResultSet rs = s.executeQuery();
		rs.next();
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			System.out.println(rs.getMetaData().getColumnLabel(i) + ": " + rs.getString(i));
		}

		System.out.println("Fertig ...");
	}

	public Main() {
		super();
	}

}