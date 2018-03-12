package main;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import banknoten.BanknotenPruefungBeanRemote;
import kapitalwert.KapitalwertEJBRemote;
import nPeriodZins.CalcNPeriodZinsEJBRemote;
import zinsfuss.ZinsfussRemote;

public class Main {
	
	public static void main(String[] args) {
		Main main = new Main();
	}
	
	public Main() {
		try {
			System.out.println("Einstieg Main");
			Context ctx = new InitialContext();
			System.out.println(ctx.getEnvironment());
			 
			/*
			 * HINWEISE:
			 * - Name im JNDI-Lookup muss voll qualifiziert sein 
			 * - Remote-Interface muss im Client in gleicher Package-Struktur liegen wie im EJB-Projekt!
			 *     - sonst: Lookup failed -> Exception
			 */
			
			// Zinsfuss
			ZinsfussRemote zf = 
					(ZinsfussRemote) ctx.lookup
					("java:global/myEAR/BankFachlichkeit/Zinsfuss!zinsfuss.ZinsfussRemote");
			System.out.println(zf.getZinssatz());
			
			
			// Kapitalwert
			KapitalwertEJBRemote kw = 
					(KapitalwertEJBRemote) ctx.lookup
					("java:global/myEAR/BankFachlichkeit/KapitalwertEJB!kapitalwert.KapitalwertEJBRemote");
			System.out.println(kw.getBarwert());
			
			
			//Zinsfuss berechnen
			CalcNPeriodZinsEJBRemote cn = 
					(CalcNPeriodZinsEJBRemote) ctx.lookup
					("java:global/myEAR/BankFachlichkeit/CalcNPeriodZinsEJB!nPeriodZins.CalcNPeriodZinsEJBRemote");
			System.out.println(cn.getEndbetrag());
			
			
			// Banknotenpruefung
			BanknotenPruefungBeanRemote bf = 
					(BanknotenPruefungBeanRemote) ctx.lookup
					("java:global/myEAR/BankFachlichkeit/BanknotenPruefungBean!banknoten.BanknotenPruefungBeanRemote");
			System.out.println(bf.checkBanknote("V12345678912"));
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}