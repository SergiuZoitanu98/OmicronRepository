package eu.winwinit.bcc.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ordini_articoli")
public class OrdineArticolo {
	
	private Integer idOrdineArticolo;
	private Integer quantita;
	
	private Articolo articolo;
	private Ordine ordine;

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_ordine_articolo", unique = true, nullable = false)
	public Integer getId() {
		return idOrdineArticolo;
	}
	
	public void setId(Integer id) {
		this.idOrdineArticolo = id;
	}


	

	@ManyToOne
	@JoinColumn(name = "id_articolo")
	public Articolo getArticolo() {
		return articolo;
	}



	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}


	@ManyToOne
	@JoinColumn(name = "id_ordine")
	public Ordine getOrdine() {
		return ordine;
	}



	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}




}
