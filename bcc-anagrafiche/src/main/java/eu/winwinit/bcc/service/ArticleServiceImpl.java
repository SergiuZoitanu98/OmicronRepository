package eu.winwinit.bcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.winwinit.bcc.entities.Articolo;
import eu.winwinit.bcc.model.ArticleRequest;
import eu.winwinit.bcc.repository.ArticleRepository;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	ArticleRepository articleRepo;
	
	@Override
	public Articolo creaArticolo(ArticleRequest articleRequest) {
		Articolo articolo = new Articolo();
		articolo.setNomeArticolo(articleRequest.getNomeArticolo());
		articolo.setDescrizione(articleRequest.getDescrizione());
		articolo.setQuantita(articleRequest.getQuantita());
			return articleRepo.save(articolo);
		
	}

	@Override
	public Articolo updateArticle(ArticleRequest articleRequest) {
		Articolo articolo = new Articolo();
		articolo.setNomeArticolo(articleRequest.getNomeArticolo());
		articolo.setDescrizione(articleRequest.getDescrizione());
		articolo.setQuantita(articleRequest.getQuantita());
		articolo.setArticoloId(articleRequest.getArticoloId());
			articleRepo.save(articolo);
		return articolo;
	}

	@Override
	public Integer cancellaArticoloTramiteId(ArticleRequest articleRequest) {
		articleRepo.deleteById(articleRequest.getArticoloId());
		return 1;
	}

	@Override
	public List<Articolo> visualizzaTuttiGliArticoli() {
		return articleRepo.findAll();
		
	}

}