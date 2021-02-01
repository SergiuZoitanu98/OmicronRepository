package eu.winwinit.bcc.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import eu.winwinit.bcc.entities.Ordine;
import eu.winwinit.bcc.entities.OrdineArticolo;
import eu.winwinit.bcc.model.DettaglioArticoli;
import eu.winwinit.bcc.model.OrderRequest;
import eu.winwinit.bcc.model.OrderResponse;
import eu.winwinit.bcc.repository.ArticleOrderRepository;
import eu.winwinit.bcc.repository.ArticleRepository;
import eu.winwinit.bcc.repository.OrderRepository;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private ArticleOrderRepository articleOrderRepo;

	@Autowired
	private ArticleRepository articleRepo;

	@Override
	public Integer creaOrdine(OrderRequest orderRequest) {
		Ordine ordine = new Ordine ();
		ordine.setNumeroOrdine(orderRequest.getNumeroOrdine());
		ordine = orderRepo.save(ordine);
		// accedo alla lista di tipo DettaglioArticoli per prendere i dettagli dell'articolo
		//e popolo l'oggetto ordineArticolo con essi, e allo stesso modo prendo i dettagli dell'ordine e
		//popolo con essi lo stesso oggetto ordineArticolo, cosi da avere i dettagli dell ordine + i dettagli
		//dell'articolo che ho ordinato
		for(DettaglioArticoli dettaglio :  orderRequest.getDettagliArticolo()) {
			OrdineArticolo ordineArticolo = new OrdineArticolo();
			ordineArticolo.setId(dettaglio.getIdArticolo());
			ordineArticolo.setId(ordine.getIdOrdine());
			ordineArticolo.setOrdine(ordine);
			ordineArticolo.setQuantita(dettaglio.getQuantita());

			ordineArticolo.setArticolo(articleRepo.getOne(dettaglio.getIdArticolo()));
			articleOrderRepo.save(ordineArticolo);
		}	
		return ordine.getIdOrdine()	;
	}

	@Override
	public Integer modificaOrdine(OrderRequest orderRequest) {
		Ordine ordine = new Ordine ();
		ordine.setNumeroOrdine(orderRequest.getNumeroOrdine());
		ordine.setIdOrdine(orderRequest.getIdOrdine());
		ordine = orderRepo.save(ordine);
		//accedo alla lista fornita dalla orderRequest per andare a modificare i campi dell'ordine
		for(DettaglioArticoli dettaglio : orderRequest.getDettagliArticolo()) {
			OrdineArticolo ordineArticolo = new OrdineArticolo();
			ordineArticolo.setId(dettaglio.getIdArticolo());
			ordineArticolo.setId(ordine.getIdOrdine());
			ordineArticolo.setQuantita(dettaglio.getQuantita());
			ordineArticolo.setOrdine(ordine);
			ordineArticolo.setArticolo(articleRepo.getOne(dettaglio.getIdArticolo()) );
			articleOrderRepo.save(ordineArticolo);
		}
		return ordine.getIdOrdine()	;
	}

	@Override
	public void cancellaOrdineTramiteId(OrderRequest orderRequest) {
		//cancello prima la foreign key dell'Ordine presente nella tavola di relazione OrdineArticolo
		articleOrderRepo.deleteById(orderRequest.getIdOrdine());
		// stessa cosa per la foreign Key di Articolo
		orderRepo.deleteById(orderRequest.getIdOrdine());

	}

	@Override
	public OrderResponse view(Integer idOrdine) {
		OrderResponse response = new OrderResponse();
		Ordine ordine = orderRepo.getOne(idOrdine);
		//creo una lista di tipo OrdineArticolo e la popolo con gli articoli che fanno parte di un determinato ordine
		List<OrdineArticolo> articoli = articleOrderRepo.findByOrdine(ordine);
		//creo un ArrayList di tipo DettaglioArticoli
		List<DettaglioArticoli> list = new ArrayList<>();
		//creo un oggetto di tipo DettaglioArticoli
		DettaglioArticoli dettagli;
		//accedo alla lista articoli
		for(OrdineArticolo o : articoli) {
			//popolo l'oggetto di tipo DettaglioArticoli con gli attributi richiesti per poi aggiungerlo alla lista "dettagli", e poi passo la lista 
			//al metodo "setDettagliArticoli"
			//una volta chiamato questo metodo con la response,possiamo visualizzare un determinato ordine con i propri articoli e i loro dettagli
			dettagli = new DettaglioArticoli(o.getArticolo().getArticoloId(),o.getQuantita(),o.getArticolo().getDescrizione());
			list.add(dettagli);
		}
		response.setIdOrdine(ordine.getIdOrdine());
		response.setNumeroOrdine(ordine.getNumeroOrdine());
		response.setDettagliArticoli(list);
		return response;		
	}

}
