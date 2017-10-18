package data;

import javax.ejb.Remote;

@Remote
public interface FlugBeanRemote {

	String showFlug();

}
