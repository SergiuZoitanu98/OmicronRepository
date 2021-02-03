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
import eu.winwinit.bcc.model.ArticleResponse;
import eu.winwinit.bcc.repository.ArticleRepository;
import eu.winwinit.bcc.service.ArticleService;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	ArticleRepository articleRepo;

	@RequestMapping(value="/creaArticolo",method = RequestMethod.POST)
	public ResponseEntity<?> creaArticolo(@RequestBody ArticleRequest articoloRequest){
		if(articoloRequest.getPrezzo() == 0) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("il prezzo deve essere compreso tra 0.1 e 999+");
		}
		Articolo articolo =	articleService.creaArticolo(articoloRequest);
		return ResponseEntity.status(HttpStatus.OK).body("articolo creato con id:"+" "+articolo.getIdArticolo());
	}


	@RequestMapping(value="/modificaArticolo",method = RequestMethod.PUT)
	public ResponseEntity<?> modificaArticolo(@RequestBody ArticleRequest articoloRequest){
		if(articoloRequest.getArticoloId() == null ) {
			return	ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("l'id non deve essere null");
		}
		if(articleRepo.existsById(articoloRequest.getArticoloId())) {
			articleService.updateArticle(articoloRequest);
			return ResponseEntity.status(HttpStatus.OK).body("articolo modificato");
		}
		return	ResponseEntity.status(HttpStatus.NOT_FOUND).body("non esiste un articolo con quell'id ");

	}


	@RequestMapping(value="/eliminaArticolo",method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminaArticoloTramiteId(@RequestBody ArticleRequest articoloRequest){

		if(articoloRequest.getArticoloId() == null ) {
			return	ResponseEntity.status(HttpStatus.NOT_FOUND).body("l'id non deve essere null ");
		}
		if(articleRepo.existsById(articoloRequest.getArticoloId())) {
			articleService.cancellaArticoloTramiteId(articoloRequest);
			return ResponseEntity.status(HttpStatus.OK).body("articolo eliminato");
		}
		return	ResponseEntity.status(HttpStatus.NOT_FOUND).body("non esiste un articolo con quell'id ");

	}

	@RequestMapping(value="visualizzaArticoli",method = RequestMethod.GET)
	public ResponseEntity<?> visualizzaTuttiGliArticoli(){
		List<Articolo> articoli = articleRepo.findAll();
		if(articoli.isEmpty()) {
			return	ResponseEntity.status(HttpStatus.NOT_FOUND).body("non ci sono articoli");

		}
		List<ArticleResponse> response = articleService.visualizzaTuttiGliArticoli();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}


