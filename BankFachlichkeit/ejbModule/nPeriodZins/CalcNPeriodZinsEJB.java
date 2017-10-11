package nPeriodZins;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class CalcNPeriodZinsEJB
 */
@Stateless
public class CalcNPeriodZinsEJB implements CalcNPeriodZinsEJBRemote {

	double tempBetrag;
	int anzPerioden;
	double endbetrag;
	
	double zins;
	
	public CalcNPeriodZinsEJB() {
		// TODO Auto-generated constructor stub
	}
	
    /**
     * Default constructor. 
     */
    public CalcNPeriodZinsEJB(double einzahlung, int anzPerioden, double zins) {
        this.tempBetrag = einzahlung;
        this.anzPerioden = anzPerioden;
        this.zins = zins;
        
        this.calcEndbetrag(einzahlung, anzPerioden);
        
    }
    
    private void calcEndbetrag(double einzahlung, int anzPerioden) {
    	zins = 0.1;
    	for (int i = 0; i <= anzPerioden; i++) {
			tempBetrag = tempBetrag * (1 + zins);
		}
		endbetrag = tempBetrag;
	}

	public double getEndbetrag() {
    	return endbetrag;
    }

}
