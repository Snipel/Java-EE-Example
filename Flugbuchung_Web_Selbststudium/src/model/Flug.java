package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the flug database table.
 * 
 */
@Entity
/*
 * SQL-ähnlich (Java Persistence Query Language , JPQL)
 * 
 * 	 - über Treiber des DB-Hersteller in SQL umgesetzt
 * 	 - somit keine Dialekte wie in SQL; universell einsetzbare Abfragesprache
 * 
 * 	 - Abfrage geht auf die Menge aller Entities, nicht auf DB
 * 	 - Zugriff auf Attribute aber eher über Getter einfacher
 */
@NamedQuery(name="Flug.findAll", query="SELECT f FROM Flug f")
public class Flug implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String flugnr;

	private BigDecimal flugzeit;

	private BigDecimal km;

	private String start;

	private String ziel;

	public Flug() {
	}

	public String getFlugnr() {
		return this.flugnr;
	}

	public void setFlugnr(String flugnr) {
		this.flugnr = flugnr;
	}

	public BigDecimal getFlugzeit() {
		return this.flugzeit;
	}

	public void setFlugzeit(BigDecimal flugzeit) {
		this.flugzeit = flugzeit;
	}

	public BigDecimal getKm() {
		return this.km;
	}

	public void setKm(BigDecimal km) {
		this.km = km;
	}

	public String getStart() {
		return this.start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getZiel() {
		return this.ziel;
	}

	public void setZiel(String ziel) {
		this.ziel = ziel;
	}

}