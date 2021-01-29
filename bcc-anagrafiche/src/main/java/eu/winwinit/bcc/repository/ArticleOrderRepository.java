package eu.winwinit.bcc.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.winwinit.bcc.entities.Ordine;
import eu.winwinit.bcc.entities.OrdineArticolo;
import eu.winwinit.bcc.model.OrderRequest;
public interface ArticleOrderRepository extends JpaRepository<OrdineArticolo,Integer> {
	public List<OrdineArticolo> findByOrdine(Ordine ordine);
	
}
