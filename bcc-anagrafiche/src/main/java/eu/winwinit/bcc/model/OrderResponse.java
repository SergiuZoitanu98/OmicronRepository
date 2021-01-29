package eu.winwinit.bcc.model;

import java.util.List;

public class OrderResponse {
private List<DettaglioArticoli> dettagliArticoli;
private Integer idOrdine;
private String numeroOrdine;

public List<DettaglioArticoli> getDettagliArticoli() {
	return dettagliArticoli;
}
public void setDettagliArticoli(List<DettaglioArticoli> dettagliArticoli) {
	this.dettagliArticoli = dettagliArticoli;
}
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




}
