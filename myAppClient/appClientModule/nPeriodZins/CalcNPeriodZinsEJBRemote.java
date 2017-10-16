package nPeriodZins;

import javax.ejb.Remote;

@Remote
public interface CalcNPeriodZinsEJBRemote {
	public double getEndbetrag();
}
