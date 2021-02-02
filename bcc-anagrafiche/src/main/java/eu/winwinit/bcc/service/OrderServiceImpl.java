package eu.winwinit.bcc.service;
import java.math.BigDecimal;
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
		//Creo un oggetto ordine
		Ordine ordine = new Ordine ();

		//Setto di nuovo i suoi campi visto che sto facendo una update
		//Il numero d'ordine deve essere una stringa random univoca (generata con una libreria?)
		ordine.setNumeroOrdine(orderRequest.getNumeroOrdine());

		//salvo le modifiche
		ordine = orderRepo.save(ordine);

		double totale = 0;
		//accedo alla lista fornita dalla orderRequest per andare a modificare i dettagli dell'ordine
		for(DettaglioArticoli dettaglio : orderRequest.getDettagliArticolo()) {
			OrdineArticolo ordineArticolo = new OrdineArticolo();
			ordineArticolo.setQuantity(dettaglio.getQuantita());
			ordineArticolo.setArticolo(articleRepo.getOne(dettaglio.getIdArticolo()));
			ordineArticolo.setOrdine(ordine);
			articleOrderRepo.save(ordineArticolo);
			totale = totale  + (ordineArticolo.getQuantity() * articleRepo.getOne(dettaglio.getIdArticolo()).getPrezzo());
		}
		ordine.setTotale(totale);
		orderRepo.save(ordine);
		return ordine.getIdOrdine();
	}

	@Override
	public Integer modificaOrdine(OrderRequest orderRequest) {
		Ordine ordine = orderRepo.getOne(orderRequest.getIdOrdine());
		
		double totale = 0;
		//accedo alla lista fornita dalla orderRequest per andare a modificare i dettagli dell'ordine
		for(DettaglioArticoli dettaglio : orderRequest.getDettagliArticolo()) {
			OrdineArticolo ordineArticolo = articleOrderRepo.findByArticoloAndOrdine(articleRepo.getOne(dettaglio.getIdArticolo()), ordine);
			ordineArticolo.setQuantity(dettaglio.getQuantita());
			articleOrderRepo.save(ordineArticolo);
			
			
			totale = totale  + (ordineArticolo.getQuantity() * articleRepo.getOne(dettaglio.getIdArticolo()).getPrezzo());
		}
		ordine.setTotale(totale);
		orderRepo.save(ordine);
		return ordine.getIdOrdine();
	}

	@Override
	public void cancellaOrdineTramiteId(OrderRequest orderRequest) {

		//cancello prima la foreign key dell'Ordine presente nella tavola di relazione OrdineArticolo

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
		List<OrdineArticolo> ordini_articoli = articleOrderRepo.findByOrdine(ordine);

		//creo un ArrayList di tipo DettaglioArticoli
		List<DettaglioArticoli> list = new ArrayList<>();

		//creo un oggetto di tipo DettaglioArticoli
		DettaglioArticoli dettagli;

		//accedo alla lista articoli
		for(OrdineArticolo o : ordini_articoli) {
			//popolo l'oggetto di tipo DettaglioArticoli con gli attributi richiesti per poi aggiungerlo alla lista "dettagli", e poi passo la lista 
			//al metodo "setDettagliArticoli"
			//una volta chiamato questo metodo con la response,possiamo visualizzare un determinato ordine con i propri articoli e i loro dettagli
			dettagli = new DettaglioArticoli(o.getArticolo().getIdArticolo(), o.getQuantity(),o.getArticolo().getDescrizione());
			list.add(dettagli);
		}

		//Visto che l'oggetto OrderRespone è un model e possiede i campi 'idOrdine' 'numeroOrdine' e una lista di 'dettaglioArticoli', setto tutti i campi
		response.setIdOrdine(ordine.getIdOrdine());
		response.setNumeroOrdine(ordine.getNumeroOrdine());
		response.setDettagliArticoli(list);
		response.setTotale(ordine.getTotale());
		//una volta popolato l'oggetto OrderResponse, faccio return dello stesso, cosi quando chiamero' questo metodo mi visualizzerà, l'ordine e l'articolo che è associato ad esso
		return response;		
	}

	

}
