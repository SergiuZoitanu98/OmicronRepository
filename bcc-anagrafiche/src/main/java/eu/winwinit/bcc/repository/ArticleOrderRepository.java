package eu.winwinit.bcc.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import eu.winwinit.bcc.entities.Articolo;
import eu.winwinit.bcc.entities.Ordine;
import eu.winwinit.bcc.entities.OrdineArticolo;

@Repository("articleOrderRepository")
public interface ArticleOrderRepository extends JpaRepository<OrdineArticolo,Integer> {
	
	public List<OrdineArticolo> findByOrdine(Ordine ordine);
	OrdineArticolo findByArticoloAndOrdine(Articolo articolo, Ordine ordine );
}
