package eu.winwinit.bcc.model;

public class DettaglioArticoli {
	private Integer idArticolo;
	private Integer quantita;
	private String descrizione;
	public DettaglioArticoli() {
		
	}
	public DettaglioArticoli(Integer idArticolo,Integer quantita,String descrizione) {
		this.idArticolo = idArticolo;
		this.quantita = quantita;
		this.descrizione = descrizione;
	}
	public Integer getIdArticolo() {
		return idArticolo;
	}
	public void setIdArticolo(Integer idArticolo) {
		this.idArticolo = idArticolo;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
}
