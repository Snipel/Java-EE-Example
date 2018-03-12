package main;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Flug;

public class Main {

	public static void main(String[] args) {
		/*
		 * persistenceUnit steht in der persistence.xml (default = Projektname)
		 * 
		 * EntityManager muss erzeugt werden über Factory, da kein Container vorhanden
		 * wo man sich ihn holen könnte. 
		 */
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Uebung4");
		
		EntityManager em = emf.createEntityManager();
		
		/*
		 * WICHTIG: Um Transaktion kümmern, wenn Application Managed gearbeitet wird. 
		 */
		em.getTransaction().begin();
		
		/*
		 * Named Query holen und absetzen
		 * getResultList führt Query aus. 
		 */
		Query q = em.createNamedQuery("Flug.findAll");
		@SuppressWarnings("unchecked")
		List<Flug> list= q.getResultList();
		
		for (Flug flug : list) {
			System.out.println("FlugNr " + flug.getFlugnr() + " dauert " + flug.getFlugzeit() + " Std.");
		}
		
		/*
		 * s.o.
		 */
		em.getTransaction().commit();
		
		/*
		 * Neues Flugobjekt bauen
		 */
		Flug flug = new Flug();
		flug.setFlugnr("AB312");
		flug.setFlugzeit(new BigDecimal(13.45));
		flug.setKm(new BigDecimal(2000));
		flug.setStart("Hameln");
		flug.setZiel("Münster");
		
		em.getTransaction().begin();
		
		/*
		 * EntityManager den neuen Flug zum persistieren geben. 
		 * Transaktion wieder selber behandeln. 
		 */
		em.persist(flug);
		
		em.getTransaction().commit();

	}

}
