package eu.winwinit.bcc.model;

public class ArticleRequest {
	
	private Integer articoloId;
	private String nomeArticolo;
	private String descrizione;
	private Integer quantita;
	
	public Integer getArticoloId() {
		return articoloId;
	}
	
	public void setArticoloId(Integer articoloId) {
		this.articoloId = articoloId;
	}
	
	public String getNomeArticolo() {
		return nomeArticolo;
	}
	
	public void setNomeArticolo(String nomeArticolo) {
		this.nomeArticolo = nomeArticolo;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public Integer getQuantita() {
		return quantita;
	}
	
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
}
