package eu.winwinit.bcc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import eu.winwinit.bcc.entities.Articolo;
import eu.winwinit.bcc.model.ArticleRequest;
import eu.winwinit.bcc.model.DettaglioArticoli;
import eu.winwinit.bcc.service.ArticleService;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;

	@RequestMapping(value="/creaArticolo",method = RequestMethod.POST)
	public ResponseEntity<?> creaArticolo(@RequestBody ArticleRequest articoloRequest){
		DettaglioArticoli dettagli = new DettaglioArticoli();
		Articolo articolo = new Articolo();
		articolo =	articleService.creaArticolo(articoloRequest);
		dettagli.setIdArticolo(articoloRequest.getArticoloId());
		dettagli.setQuantita(articoloRequest.getQuantita());
		return ResponseEntity.status(HttpStatus.OK).body(articolo);
	}

	@RequestMapping(value="/modificaArticolo",method = RequestMethod.PUT)
	public ResponseEntity<?> modificaArticolo(@RequestBody ArticleRequest articoloRequest){
		articleService.updateArticle(articoloRequest);
		return ResponseEntity.status(HttpStatus.OK).body("articolo modificato");
	}

	@RequestMapping(value="/eliminaArticolo",method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminaArticoloTramiteId(@RequestBody ArticleRequest articoloRequest){
		articleService.cancellaArticoloTramiteId(articoloRequest);
		return ResponseEntity.status(HttpStatus.OK).body("articolo eliminato");
	}

	@RequestMapping(value="visualizzaArticoli",method = RequestMethod.GET)
	public ResponseEntity<?> visualizzaTuttiGliArticoli(){
		List<Articolo> articoli = articleService.visualizzaTuttiGliArticoli();
		return ResponseEntity.status(HttpStatus.OK).body(articoli);

	}
}
