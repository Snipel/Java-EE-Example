package banknoten;

import javax.ejb.Remote;

@Remote
public interface BanknotenPruefungBeanRemote {
	public boolean checkBanknote(String banknotennummer);
}
