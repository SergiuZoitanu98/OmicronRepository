package eu.winwinit.bcc.service;
import java.util.List;

import eu.winwinit.bcc.entities.Articolo;
import eu.winwinit.bcc.model.ArticleRequest;

public interface ArticleService {
	
//Creo i metodi dell'interfaccia ArticleService che saranno implementati dalla classe ArticleServiceImpl
	public Articolo creaArticolo(ArticleRequest articleRequest);
	public Articolo updateArticle(ArticleRequest articleRequest);
	public void cancellaArticoloTramiteId(ArticleRequest articleRequest);
	public List<Articolo> visualizzaTuttiGliArticoli();
	
}
