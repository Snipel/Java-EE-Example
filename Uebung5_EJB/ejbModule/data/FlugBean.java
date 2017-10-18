package data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Flug;

/**
 * Session Bean implementation class FlugBean
 */
@Stateless
@LocalBean
public class FlugBean implements FlugBeanRemote {
	/*
	 * HINWEISE:
	 *    - persistence XML bearbeiten 
	 *      => Connection über JTA über die Ressource die vorher angelegt wurde
	 *    - JPA Kram über Projekt Properties -> Project Facets anstellen
	 */

	/*
	 * Hier PersistenceContext über Annotation holen. 
	 */
	@PersistenceContext(name="Uebung5_EJB")
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public FlugBean() {
        // TODO Auto-generated constructor stub
    }
    
	@SuppressWarnings("unchecked")
    @Override
    public void showFlug() {
    	/*
		 * WICHTIG: Um Transaktion kümmern, wenn Application Managed gearbeitet wird. 
		 */
		em.getTransaction().begin();
		
		/*
		 * Named Query holen und absetzen
		 * getResultList führt Query aus. 
		 */
		Query q = em.createNamedQuery("Flug.findAll");
		List<Flug> list= q.getResultList();
		
		for (Flug flug : list) {
			System.out.println("FlugNr " + flug.getFlugnr() + " dauert " + flug.getFlugzeit() + " Std.");
		}
		
		/*
		 * s.o.
		 */
		em.getTransaction().commit();
	}

}
