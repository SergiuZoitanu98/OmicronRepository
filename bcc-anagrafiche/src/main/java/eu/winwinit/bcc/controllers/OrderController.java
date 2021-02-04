package eu.winwinit.bcc.controllers;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import eu.winwinit.bcc.model.DettaglioArticoli;
import eu.winwinit.bcc.model.OrderRequest;
import eu.winwinit.bcc.model.OrderResponse;
import eu.winwinit.bcc.repository.ArticleRepository;
import eu.winwinit.bcc.repository.OrderRepository;
import eu.winwinit.bcc.service.OrderService;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private ArticleRepository articleRepo;


	@RequestMapping(value="/creaOrdine",method = RequestMethod.POST)
	public ResponseEntity<?> creaOrdine(@RequestBody OrderRequest orderRequest){
		List<DettaglioArticoli> dettagli = orderRequest.getDettagliArticolo();
		for(DettaglioArticoli dettaglio : dettagli) {
			if(articleRepo.existsById(dettaglio.getIdArticolo())) {
				orderService.creaOrdine(orderRequest);
				return  ResponseEntity.status(HttpStatus.OK).body("ordine creato");
			}
		}

		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("non esiste un articolo con questo id");
	}

	@RequestMapping(value="/modificaOrdine",method = RequestMethod.PUT)
	public ResponseEntity<?> modificaOrdine(@RequestBody OrderRequest orderRequest){
		List<DettaglioArticoli> dettagli = orderRequest.getDettagliArticolo();
		for(DettaglioArticoli dettaglio : dettagli) {
			if(dettaglio.getIdArticolo() == null ) {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("l'id dell articolo non puo'essere null");

			}else if(orderRequest.getIdOrdine() == null) {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("l'id dell' ordine non puo' essere null");

			}
			if(!orderRepo.existsById(orderRequest.getIdOrdine())) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("non esiste  un ordine con questo id");
			}else if(!articleRepo.existsById(dettaglio.getIdArticolo())) {
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("non esiste un articolo con questo id");
			}
		}

		orderService.modificaOrdine(orderRequest);
		return  ResponseEntity.status(HttpStatus.OK).body("ordine modificato");
	}

	@RequestMapping(value="/eliminaOrdineTramiteId",method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminaOrdine(@RequestBody OrderRequest orderRequest){
		if(orderRequest.getIdOrdine() == null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("l'id specificato non puo' essere null");
		}
		if(orderRepo.existsById(orderRequest.getIdOrdine())) {
			orderService.cancellaOrdineTramiteId(orderRequest);
			return ResponseEntity.status(HttpStatus.OK).body("Ordine cancellato");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("non esiste un ordine con questo id");
	}

	@RequestMapping(value="/visualizzaOrdineTramiteId",method = RequestMethod.GET)
	public ResponseEntity<?> view(@RequestBody OrderRequest orderRequest){
		if(orderRequest.getIdOrdine() == null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("l'id specificato non puo' essere null");
		}
		if(orderRepo.existsById(orderRequest.getIdOrdine())) {
			OrderResponse response = orderService.view(orderRequest.getIdOrdine());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("non esiste un ordine con questo id");
	}
}
