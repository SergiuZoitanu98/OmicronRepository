package eu.winwinit.bcc.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import eu.winwinit.bcc.entities.Articolo;
import eu.winwinit.bcc.entities.Cliente;
import eu.winwinit.bcc.entities.Ordine;
import eu.winwinit.bcc.entities.OrdineArticolo;

@Repository("articleOrderRepository")
public interface ArticleOrderRepository extends JpaRepository<OrdineArticolo,Integer> {
	
	//creo un metodo che mi cerca per ordine un determinato articolo,metodo che mi serve nel Service di Ordine per visualizzare gli articoli di quel determinato ordine che passo al metodo
	public List<OrdineArticolo> findByOrdine(Ordine ordine);
	
	 OrdineArticolo findByArticoloAndOrdine(Articolo articolo, Ordine ordine );
	
}
