package eu.winwinit.bcc.service;
import java.util.List;

import eu.winwinit.bcc.entities.Articolo;
import eu.winwinit.bcc.model.ArticleRequest;
import eu.winwinit.bcc.model.ArticleResponse;

public interface ArticleService {

	public Articolo creaArticolo(ArticleRequest articleRequest);
	public Articolo updateArticle(ArticleRequest articleRequest);
	public void cancellaArticoloTramiteId(ArticleRequest articleRequest);
	public List<ArticleResponse> visualizzaTuttiGliArticoli();
}
