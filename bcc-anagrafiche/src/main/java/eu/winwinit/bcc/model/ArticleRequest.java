package eu.winwinit.bcc.model;



public class ArticleRequest {

	private Integer articoloId;
	private String nomeArticolo;
	private String descrizione;
	private Integer quantita;
	private String colore;
	private String taglia;
	private double prezzo;

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

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getTaglia() {
		return taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}
}
