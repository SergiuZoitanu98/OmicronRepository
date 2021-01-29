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
private Integer id_articolo_fk;
private Integer id_ordine_fk;



public OrdineArticolo(Integer idOrdineArticolo,Integer idArticoloFk,Integer idOrdineFk,Ordine ordine,Articolo articolo) {
	this.idOrdineArticolo = idOrdineArticolo;
	this.id_articolo_fk = idArticoloFk;
	this.id_ordine_fk = idOrdineFk;
	this.ordine = ordine;
	this.articolo = articolo;
}



public OrdineArticolo() {
	
}


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


public Integer getId_articolo_fk() {
	return id_articolo_fk;
}
public void setId_articolo_fk(Integer id_articolo_fk) {
	this.id_articolo_fk = id_articolo_fk;
}

public Integer getId_ordine_fk() {
	return id_ordine_fk;
}
public void setId_ordine_fk(Integer id_ordine_fk) {
	this.id_ordine_fk = id_ordine_fk;
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



@Override
public String toString() {
	return "OrdineArticolo [idOrdineArticolo=" + idOrdineArticolo + ", id_articolo_fk=" + id_articolo_fk
			+ ", id_ordine_fk=" + id_ordine_fk + ", articolo=" + articolo + ", ordine=" + ordine + "]";
}

}
