package eu.winwinit.bcc.model;

public class DettaglioArticoli {
	private Integer idArticolo;
	private Integer quantita;
	
	public DettaglioArticoli() {
		
	}
	public DettaglioArticoli(Integer idArticolo,Integer quantita) {
		this.idArticolo = idArticolo;
		this.quantita = quantita;
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
	
}
