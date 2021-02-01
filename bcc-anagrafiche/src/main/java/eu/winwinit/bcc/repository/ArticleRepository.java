package eu.winwinit.bcc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import eu.winwinit.bcc.entities.Articolo;

@Repository("articleRepository")
public interface ArticleRepository extends JpaRepository<Articolo,Integer>{
	
}
