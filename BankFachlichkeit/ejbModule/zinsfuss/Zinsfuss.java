package zinsfuss;

import javax.ejb.Stateful;

/**
 * Session Bean implementation class Zinsfuss
 */
@Stateful
public class Zinsfuss implements ZinsfussRemote {

	double zinssatz;
	
    /**
     * Default constructor. 
     */
    public Zinsfuss() {
        zinssatz = 0.1;
    }
    
    public double getZinssatz() {
		return zinssatz;
	}
    
    public void setZinssatz(Double zinssatz) {
		this.zinssatz = zinssatz;
	}

}
