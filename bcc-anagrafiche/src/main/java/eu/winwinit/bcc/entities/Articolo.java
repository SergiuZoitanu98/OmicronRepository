package eu.winwinit.bcc.entities;


import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the articoli database table.
 * 
 */
@Entity
public class Articolo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private int idArticolo;

	private String colore;

	private String descrizione;

	private String nome;

	private double prezzo;

	private String taglia;

	//bi-directional many-to-one association to OrdiniArticoli
	@OneToMany(mappedBy="articolo")
	private List<OrdineArticolo> ordineArticolo;

	public Articolo() {
	}

	public int getIdArticolo() {
		return this.idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}

	public String getColore() {
		return this.colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getTaglia() {
		return this.taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public List<OrdineArticolo> getOrdiniArticoli() {
		return this.ordineArticolo;
	}

	public void setOrdiniArticoli(List<OrdineArticolo> ordineArticolo) {
		this.ordineArticolo = ordineArticolo;
	}

	public OrdineArticolo addOrdiniArticoli(OrdineArticolo ordineArticolo) {
		getOrdiniArticoli().add(ordineArticolo);
		ordineArticolo.setArticolo(this);

		return ordineArticolo;
	}

	public OrdineArticolo removeOrdiniArticoli(OrdineArticolo ordineArticolo) {
		getOrdiniArticoli().remove(ordineArticolo);
		ordineArticolo.setArticolo(null);

		return ordineArticolo;
	}

}