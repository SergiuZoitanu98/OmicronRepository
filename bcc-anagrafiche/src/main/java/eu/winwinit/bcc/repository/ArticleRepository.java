package eu.winwinit.bcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.winwinit.bcc.entities.Articolo;
import eu.winwinit.bcc.model.ArticleRequest;

public interface ArticleRepository extends JpaRepository<Articolo,Integer>{
	public Articolo save(Articolo articolo);
	
}
