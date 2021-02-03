package eu.winwinit.bcc.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.winwinit.bcc.entities.Articolo;
import eu.winwinit.bcc.model.ArticleRequest;
import eu.winwinit.bcc.model.ArticleResponse;
import eu.winwinit.bcc.model.DettaglioArticoli;
import eu.winwinit.bcc.repository.ArticleRepository;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	ArticleRepository articleRepo;

	@Override
	public Articolo creaArticolo(ArticleRequest articleRequest) {
		//Creo un oggetto articolo
		Articolo articolo = new Articolo();
		
		//Uso i setter per popolare l'oggetto
		articolo.setNome(articleRequest.getNomeArticolo());
		articolo.setDescrizione(articleRequest.getDescrizione());
		articolo.setColore(articleRequest.getColore());
		articolo.setPrezzo(articleRequest.getPrezzo());
		articolo.setTaglia(articleRequest.getTaglia());
		return articleRepo.save(articolo);
	}

	@Override
	public Articolo updateArticle(ArticleRequest articleRequest) {
		//Creo un oggetto articolo
		Articolo articolo = new Articolo();
		
		//setto di nuovo tutti i campi con i setter perchè sto facendo una update
		articolo.setNome(articleRequest.getNomeArticolo());
		articolo.setDescrizione(articleRequest.getDescrizione());
		articolo.setIdArticolo(articleRequest.getArticoloId());
		articolo.setColore(articleRequest.getColore());
		articolo.setPrezzo(articleRequest.getPrezzo());
		articolo.setTaglia(articleRequest.getTaglia());
		
		//Salvo le modifiche 
		articleRepo.save(articolo);
		return articolo;
	}

	@Override
	public void cancellaArticoloTramiteId(ArticleRequest articleRequest) {
		
		//cancello l'articolo che ha l id che passo al metodo
		articleRepo.deleteById(articleRequest.getArticoloId());
	}

	@Override
	public List<ArticleResponse> visualizzaTuttiGliArticoli() {
		List<ArticleResponse>responseArticle = new ArrayList<>();
		for(Articolo a : articleRepo.findAll()) {
			ArticleResponse response = new ArticleResponse();
			response.setArticoloId(a.getIdArticolo());
			response.setColore(a.getColore());
			response.setDescrizione(a.getDescrizione());
			response.setNomeArticolo(a.getNome());
			response.setPrezzo(a.getPrezzo());
			response.setTaglia(a.getTaglia());
			responseArticle.add(response);
		}
		return responseArticle;
	}
}
