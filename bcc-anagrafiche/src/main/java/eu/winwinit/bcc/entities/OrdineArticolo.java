package eu.winwinit.bcc.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ordini_articoli database table.
 * 
 */
@Entity
@Table(name="ordini_articoli")
public class OrdineArticolo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOrdineProdotti;

	private int quantity;

	//bi-directional many-to-one association to Ordini
	@ManyToOne
	@JoinColumn(name="idOrdineFk")
	private Ordine ordine;

	//bi-directional many-to-one association to Articoli
	@ManyToOne
	@JoinColumn(name="idArticoloFk")
	private Articolo articolo;

	public OrdineArticolo() {
	}

	public int getIdOrdineProdotti() {
		return this.idOrdineProdotti;
	}

	public void setIdOrdineProdotti(int idOrdineProdotti) {
		this.idOrdineProdotti = idOrdineProdotti;
	}

	public Ordine getOrdine() {
		return this.ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public Articolo getArticolo() {
		return this.articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}