package kapitalwert;

import java.util.List;

import javax.ejb.Stateful;

/**
 * Session Bean implementation class KapitalwertEJB
 */
@Stateful
public class KapitalwertEJB implements KapitalwertEJBRemote {

	Double barwert;
	Double endwert;
	
	public KapitalwertEJB() {
		
	}
	
    /**
     * Default constructor. 
     */
    public KapitalwertEJB(List<Double> einzahlungen, double zinssatz) {
    	// ENDWERT
    	//Anfangsausgaben für Investition auslesen; muss immer positiv sein
    	endwert = Math.abs((double) einzahlungen.get(0));
    	
    	for (int i = 1; i < einzahlungen.size(); i++) {
			endwert = endwert + ( (double) einzahlungen.get(i) * Math.pow( 1 + zinssatz, i ) );
		}
    	
    	//BARWERT
    	//Anfangsausgaben für Investition auslesen; muss immer negativ sein
    	barwert = - Math.abs((double) einzahlungen.get(0));
    	
    	for (int i = 1; i < einzahlungen.size(); i++) {
			barwert = barwert + ( (double) einzahlungen.get(0) * Math.pow( 1 + zinssatz, -i ) );
		}
    }

	@Override
	public Double getEndwert() {
		return endwert;
	}

	@Override
	public Double getBarwert() {
		return barwert;
	}

}
