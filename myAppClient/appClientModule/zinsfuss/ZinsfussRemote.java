package zinsfuss;

import javax.ejb.Remote;

@Remote
public interface ZinsfussRemote {
	public double getZinssatz() ;
	public void setZinssatz(Double zinssatz) ;
}
