package ds;

import javax.ejb.Remote;

@Remote
public interface DataSourceProviderBeanRemote {
	public String ping();
}
