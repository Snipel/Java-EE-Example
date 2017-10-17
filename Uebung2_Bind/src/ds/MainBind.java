package ds;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MainBind {
	/*
	 * Vorher MySQL Driver �ber Add Library einbinden im Projekt 
	 * Setzen �ber Preferences-Window
	 * 
	 * <<<<<<<<<>>>>>>>>>
	 * ACHTUNG: Bind darf nicht terminiert werden, da sonst der Lookup nicht l�uft!
	 * <<<<<<<<<>>>>>>>>>
	 */
	public static void main(String[] args) throws NamingException {
		/*
		 *  DataSource erstellen
		 */
		MysqlDataSource mysqlds = new MysqlDataSource();
		
		/*
		 *  Parameter setzen f�r Server
		 *  Verbindungsparameter zum MySQL-Server
		 *  Wichtig: URL-Parameter muss richtig sein -> sonst NullPointer
		 */
		mysqlds.setUser("root");
		mysqlds.setPassword("root");
		mysqlds.setDatabaseName("flugbuchung");
		mysqlds.setPortNumber(3306);
		mysqlds.setServerName("localhost");
		
		/*
		 * Properties f�llen (es gibt keine Default-Werte!)
		 * M�glichkeiten: 
		 *    - Systemumgebungsparameter 	--> System.getParameter(...)
		 *    - Properties-File 			--> jndi.properties
		 */
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
		props.put(Context.PROVIDER_URL, "rmi://localhost:1099");
		
		/*
		 *  InitialContext holen
		 *  Context ist Verweis auf einen konkreten Namensdienst
		 *  Properties von oben �bergeben
		 */
		Context ctx = new InitialContext(props);
		
		/*
		 * Binden der DataSource an den Namensdienst (hier RMI)
		 *    -> Name wird auf dem Namensdienst registriert
		 *    
		 * HINWEIS: 
		 *    - RMI Dienst muss laufen (Start �ber "start rmiregistry 1099" oder im Java-folder/bin
		 */
		ctx.unbind("myFBrmi");
		ctx.bind("myFBrmi", mysqlds);
		
		
		
		
		System.out.println("Fertig ...");
	}
}
