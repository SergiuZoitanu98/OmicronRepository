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
		//creo un oggetto Ordine
		Ordine ordine = new Ordine ();
		//setto il numero dell'ordine,unico campo da settare perchè  l'id  è autoincrementato
		ordine.setNumeroOrdine(orderRequest.getNumeroOrdine());
		//creo l'ordine
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
			
			//creo l'ordine con i suoi dettagli
			articleOrderRepo.save(ordineArticolo);
		}	
		return ordine.getIdOrdine()	;
	}

	@Override
	public Integer modificaOrdine(OrderRequest orderRequest) {
		//Creo un oggetto ordine
		Ordine ordine = new Ordine ();
		
		//Setto di nuovo i suoi campi visto che sto facendo una update
		ordine.setNumeroOrdine(orderRequest.getNumeroOrdine());
		ordine.setIdOrdine(orderRequest.getIdOrdine());
		
		//salvo le modifiche
		ordine = orderRepo.save(ordine);
		
		//accedo alla lista fornita dalla orderRequest per andare a modificare i dettagli dell'ordine
		for(DettaglioArticoli dettaglio : orderRequest.getDettagliArticolo()) {
			OrdineArticolo ordineArticolo = new OrdineArticolo();
			ordineArticolo.setId(dettaglio.getIdArticolo());
			ordineArticolo.setId(ordine.getIdOrdine());
			ordineArticolo.setQuantita(dettaglio.getQuantita());
			ordineArticolo.setOrdine(ordine);
			ordineArticolo.setArticolo(articleRepo.getOne(dettaglio.getIdArticolo()) );
			
			//salvo le modifiche
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
		//creo un oggetto OrderResponse
		OrderResponse response = new OrderResponse();
		//crep un oggetto ordine e gli passo un id 
		//quindi se gli passo id '1', mi visualizzerà l'ordine con il numero dell'ordine e il suo id
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
		
		//Visto che l'oggetto OrderRespone è un model e possiede i campi 'idOrdine' 'numeroOrdine' e una lista di 'dettaglioArticoli', setto tutti i campi
		response.setIdOrdine(ordine.getIdOrdine());
		response.setNumeroOrdine(ordine.getNumeroOrdine());
		response.setDettagliArticoli(list);
		//una volta popolato l'oggetto OrderResponse, faccio return dello stesso, cosi quando chiamero' questo metodo mi visualizzerà, l'ordine e l'articolo che è associato ad esso
		return response;		
	}

}
