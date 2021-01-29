package eu.winwinit.bcc.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import eu.winwinit.bcc.entities.Ordine;
@Entity
@Table(name = "articoli")
public class Articolo {
	private Integer articoloId;
	private String nomeArticolo;
	private String descrizione;
	
	private List<OrdineArticolo> ordineArticolo;




	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_articolo", unique = true, nullable = false)
	public Integer getArticoloId() {
		return articoloId;
	}

	public void setArticoloId(Integer articoloId) {
		this.articoloId = articoloId;
	}
	@Column(name = "nome_articolo",length = 255)
	public String getNomeArticolo() {
		return nomeArticolo;
	}
	public void setNomeArticolo(String nomeArticolo) {
		this.nomeArticolo = nomeArticolo;
	}

	@OneToMany(mappedBy = "articolo")
	public void setOrdineArticolo(List<OrdineArticolo> ordineArticolo) {
		this.ordineArticolo = ordineArticolo;
	}
	@Column(name="descrizione_articolo",length = 255)
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	
	







}
