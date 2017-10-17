package ds;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class DataSourceProviderBean
 */
/*
 * env ist Subnamensraum -> Benutzt man als Konvention
 * 
 * -> Hier alles einstellen, was die DataSource braucht
 * 
 * 
 * Möglichkeit über Annotation:
 *    - Beim Deployment wird die DataSource automatisch erstellt im Namensdienst
 *    - Von EJB erledigt
 *    - Anstatt Binding per Programm durchzuführen
 *    
 *    
 * Alternative:
 *    - java:module/env/myFBgf als Namen
 *    - DataSource ist nur im Namensraum der EJB verfügbar
 *    - kann man von Client nicht drauf zugreifen (Zugriffsbeschränkung)
 *    
 *    -> Lookup failed, wenn man den gleichen String im Client einträgt
 * 
 * 
 */
@DataSourceDefinition
(
	className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource", 
	name = "java:global/env/myFBgf", 
	databaseName = "flugbuchung",
	portNumber = 3306,
	serverName = "localhost",
	user = "root",
	password = "root"
)
@Stateless
@LocalBean
public class DataSourceProviderBean implements DataSourceProviderBeanRemote {

	/**
	 * Default constructor.
	 */
	public DataSourceProviderBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String ping() {
		return "Alive!";

	}

}
