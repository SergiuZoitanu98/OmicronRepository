package eu.winwinit.bcc.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ordini")
public class Ordine {
private Integer idOrdine;
private String numeroOrdine;


private OrdineArticolo ordineArticolo;

public Ordine() {
	
}

public Ordine(Integer idOrdine,String numeroOrdine) {
	this.idOrdine = idOrdine;
	this.numeroOrdine = numeroOrdine;
}

@OneToMany(mappedBy = "ordine")
public void setOrdineArticolo(OrdineArticolo ordineArticolo) {
	this.ordineArticolo = ordineArticolo;
}
@Id
@GeneratedValue(strategy = IDENTITY)

@Column(name = "id_ordine", unique = true, nullable = false)
public Integer getIdOrdine() {
	return idOrdine;
}
public void setIdOrdine(Integer id) {
	this.idOrdine = id;
}


@Column(name = "numero_ordine",nullable = false, length = 255)
public String getNumeroOrdine() {
	return numeroOrdine;
}
public void setNumeroOrdine(String numeroOrdine) {
	this.numeroOrdine = numeroOrdine;
}

@Override
public String toString() {
	return "Ordine [idOrdine=" + idOrdine + ", numeroOrdine=" + numeroOrdine 
			+ ", ordineArticolo=" + ordineArticolo + "]";
}


}
