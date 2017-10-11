package remoteInterfaces;

import javax.ejb.Remote;

@Remote
public interface CalcNPeriodZinsEJBRemote {
	public double getEndbetrag();
}
