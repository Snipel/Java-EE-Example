package kapitalwert;

import javax.ejb.Remote;

@Remote
public interface KapitalwertEJBRemote {
	public Double getEndwert();
	public Double getBarwert();
}
