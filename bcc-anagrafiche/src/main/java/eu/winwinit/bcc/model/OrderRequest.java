package eu.winwinit.bcc.model;

import java.util.List;

public class OrderRequest {
	
	private Integer idOrdine;
	private String numeroOrdine;
	private double totale;
	private List<DettaglioArticoli> dettagliArticolo;
	
	public Integer getIdOrdine() {
		return idOrdine;
	}
	
	public void setIdOrdine(Integer idOrdine) {
		this.idOrdine = idOrdine;
	}
	
	public String getNumeroOrdine() {
		return numeroOrdine;
	}
	
	public void setNumeroOrdine(String numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}
	
	public List<DettaglioArticoli> getDettagliArticolo() {
		return dettagliArticolo;
	}
	
	public void setDettagliArticolo(List<DettaglioArticoli> dettagliArticolo) {
		this.dettagliArticolo = dettagliArticolo;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}
}
