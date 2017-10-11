package banknoten;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class BanknotenPruefungBean
 */
@Stateless
public class BanknotenPruefungBean implements BanknotenPruefungBeanRemote {

	private boolean formatGueltig;
	
	public BanknotenPruefungBean() {
		// TODO Auto-generated constructor stub
	}

	private boolean checkFormat(String banknotennummer) {
		//Gesamtlänge prüfen
		if (banknotennummer.length() != 12) {
			formatGueltig = false;
		}
		
		//folgende 10 Ziffern prüfen auf numerisch prüfen (inkl. Prüfziffer)
		int i = 2;
		while(formatGueltig == true) {
			if(!(banknotennummer.substring(i, i+1).matches("[0-9]"))){
				formatGueltig = false;
			}
		}
		
		return formatGueltig;
	}
	
	private boolean checkPruefziffer(String banknotennummer) {

		// Druckereicodes filtern
		char druck1 = banknotennummer.substring(0, 2).charAt(0);
		char druck2 = banknotennummer.substring(0, 2).charAt(1);

		// Druckereicodes in Zahlen umwandeln! Funktioniert mit -64 nur bei
		// Großbuchstaben!
		int druck1Int = (int) druck1 - 64;
		int druck2Int = (int) druck2 - 64;

		// Neue Seriennummer bilden ohne Druckerei-Buchstaben und Prüfziffer
		String neueBNN = Integer.toString(druck1Int) + Integer.toString(druck2Int)
				+ banknotennummer.substring(2, banknotennummer.length() - 1);

		// Quersumme bilden bisauf Prüfziffer
		int qSumme = quersumme(neueBNN);

		// Prüfziffer filtern
		int pruefziffer = Integer.parseInt(banknotennummer.substring(banknotennummer.length() - 1));

		// Berechnung des Rests
		int rest = qSumme % 9;
		rest = 7 - rest;

		// Check Up
		if (rest == 0)
			rest = 9;

		if (rest == pruefziffer) {
			return true;
		} else {
			return false;
		}
	}

	private int quersumme(String banknotennummer) {
		int qsumme = 0;
		for (int i = 0; i < banknotennummer.length(); i++) {
			qsumme += Integer.parseInt(banknotennummer.substring(i, i + 1));
		}
		return qsumme;
	}

	@Override
	public boolean checkBanknote(String banknotennummer) {
		if (checkFormat(banknotennummer) && checkPruefziffer(banknotennummer)) {
			return true;
		}
		
		return false;
	}

}
