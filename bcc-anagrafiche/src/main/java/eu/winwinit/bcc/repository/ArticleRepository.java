package eu.winwinit.bcc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import eu.winwinit.bcc.entities.Articolo;

public interface ArticleRepository extends JpaRepository<Articolo,Integer>{
	
}
