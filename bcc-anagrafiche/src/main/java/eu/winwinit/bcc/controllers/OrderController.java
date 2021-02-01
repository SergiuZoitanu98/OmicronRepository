package eu.winwinit.bcc.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import eu.winwinit.bcc.model.OrderRequest;
import eu.winwinit.bcc.model.OrderResponse;
import eu.winwinit.bcc.service.OrderService;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value="/creaOrdine",method = RequestMethod.POST)
	public ResponseEntity<?> creaOrdine(@RequestBody OrderRequest orderRequest){
		orderService.creaOrdine(orderRequest);
		return ResponseEntity.status(HttpStatus.OK).body("OK");
	}

	@RequestMapping(value="/modificaOrdine",method = RequestMethod.PUT)
	public ResponseEntity<?> modificaOrdine(@RequestBody OrderRequest orderRequest){
		orderService.modificaOrdine(orderRequest);
		return ResponseEntity.status(HttpStatus.OK).body("OK");
	}

	@RequestMapping(value="/eliminaOrdineTramiteId",method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminaOrdine(@RequestBody OrderRequest orderRequest){
		orderService.cancellaOrdineTramiteId(orderRequest);
		return ResponseEntity.status(HttpStatus.OK).body("OK");
	}

	@RequestMapping(value="/view",method = RequestMethod.GET)
	public ResponseEntity<?> view(@RequestBody OrderRequest orderRequest){
		OrderResponse response = orderService.view(orderRequest.getIdOrdine());
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}
}
