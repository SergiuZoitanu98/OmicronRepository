package eu.winwinit.bcc.entities;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ordini database table.
 * 
 */
@Entity
public class Ordine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOrdine;

	private String numeroOrdine;

	private double totale;

	//bi-directional many-to-one association to OrdiniArticoli
	@OneToMany(mappedBy="ordine", cascade = CascadeType.REMOVE)
	private List<OrdineArticolo> ordineArticolo;

	public Ordine() {
	}

	public int getIdOrdine() {
		return this.idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public String getNumeroOrdine() {
		return this.numeroOrdine;
	}

	public void setNumeroOrdine(String numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public double getTotale() {
		return this.totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public List<OrdineArticolo> getOrdiniArticoli() {
		return this.ordineArticolo;
	}

	public void setOrdiniArticoli(List<OrdineArticolo> ordineArticolo) {
		this.ordineArticolo = ordineArticolo;
	}

	public OrdineArticolo addOrdiniArticoli(OrdineArticolo ordineArticolo) {
		getOrdiniArticoli().add(ordineArticolo);
		ordineArticolo.setOrdine(this);

		return ordineArticolo;
	}

	public OrdineArticolo removeOrdiniArticoli(OrdineArticolo ordineArticolo) {
		getOrdiniArticoli().remove(ordineArticolo);
		ordineArticolo.setOrdine(null);

		return ordineArticolo;
	}
}