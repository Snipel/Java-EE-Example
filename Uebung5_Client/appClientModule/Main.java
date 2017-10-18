import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import data.FlugBeanRemote;

public class Main {
	public static void main(String[] args) throws NamingException {
		System.out.println("Einstieg Main");
		
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
		props.put("org.omg.CORBA.ORBInitialHost", "localhost");
		props.put("org.omg.CORBA.ORBInitialPort", "3700");
		Context ctx = new InitialContext(props);
		
		// Nutzen der Flugbean
		FlugBeanRemote flugbean = 
				(FlugBeanRemote) ctx.lookup
				("java:global/Uebung5_EJB/FlugBean!data.FlugBeanRemote");
		flugbean.showFlug();
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}