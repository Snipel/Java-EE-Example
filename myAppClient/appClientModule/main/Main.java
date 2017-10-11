package main;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import remoteInterfaces.BanknotenPruefungBeanRemote;
import remoteInterfaces.CalcNPeriodZinsEJBRemote;
import remoteInterfaces.KapitalwertEJBRemote;
import remoteInterfaces.ZinsfussRemote;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
	}
	
	public Main() {
		try {
			System.out.println("Einstieg Main");
			Context ctx = new InitialContext();
			 
			// Zinsfuss
			ZinsfussRemote zf = 
					(ZinsfussRemote) ctx.lookup
					("java:global/myEAR/BankFachlichkeit/Zinsfuss!zinsfuss.Zinsfuss");
			System.out.println(zf.getZinssatz());
			
			
			KapitalwertEJBRemote kw = 
					(KapitalwertEJBRemote) ctx.lookup
					("java:global/myEAR/BankFachlichkeit/Kapitalwert!kapitalwert.KapitalwertEJB");
			System.out.println(kw.getBarwert());
			
			
			CalcNPeriodZinsEJBRemote cn = 
					(CalcNPeriodZinsEJBRemote) ctx.lookup
					("java:global/myEAR/BankFachlichkeit/CalcNPeriodZinsEJB!nPeriodZins.CalcNPeriodZinsEJB");
			System.out.println(cn.getEndbetrag());
			
			
			BanknotenPruefungBeanRemote bf = 
					(BanknotenPruefungBeanRemote) ctx.lookup
					("java:global/myEAR/BankFachlichkeit/BanknotenPruefungBean!banknoten.BanknotenPruefungBean");
			System.out.println(bf.checkBanknote("V12345678912"));
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}