package eu.winwinit.bcc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
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
		//popolo il nuovo oggetto Ordine con i dati che mi arrivanao dal FE,ovvero da orderRequest
		ordine.setNumeroOrdine(generateNumeroOrdine());
		//salvo l'ordine appena creato
		ordine = orderRepo.save(ordine);
		//creo una variabile di supporto per calcolare il totale del prezzo dell' ordine in base a quanti articoli sono presenti
		Double totale = 0.0;
		for(DettaglioArticoli dettaglio : orderRequest.getDettagliArticolo()) {
			OrdineArticolo ordineArticolo = new OrdineArticolo();
			//popolo l'oggetto ordineArticolo con i dati che mi arrivano dal FE, in questo caso una lista di dettagliArticolo
			ordineArticolo.setQuantity(dettaglio.getQuantita());
			ordineArticolo.setArticolo(articleRepo.getOne(dettaglio.getIdArticolo()));
			ordineArticolo.setOrdine(ordine);
			//salvo i dettagli dell'articolo presente nell'ordine
			articleOrderRepo.save(ordineArticolo);
			//calcolo il  prezzo totale dell'ordine
			totale = totale  + (ordineArticolo.getQuantity() * articleRepo.getOne(dettaglio.getIdArticolo()).getPrezzo());
		}
		ordine.setTotale(totale);
		orderRepo.save(ordine);
		return ordine.getIdOrdine();
	}

	@Override
	public Integer modificaOrdine(OrderRequest orderRequest) {
		//prendo tramite id un ordine esistente nel database
		Ordine ordine = orderRepo.getOne(orderRequest.getIdOrdine());
		//dichiaro sempre una variabile di supporto per calcolare il prezzo totale
		double totale = 0;
		for(DettaglioArticoli dettaglio : orderRequest.getDettagliArticolo()) {
			//cerco tramite foreign key di articolo e ordine, l'ordine_articolo da modificare
			OrdineArticolo ordineArticolo = articleOrderRepo.findByArticoloAndOrdine(articleRepo.getOne(dettaglio.getIdArticolo()), ordine);
			//modifico la quantità di quel ordine_articolo
			ordineArticolo.setQuantity(dettaglio.getQuantita());
			
			//salvo l'ordine_articolo
			articleOrderRepo.save(ordineArticolo);
			//ricalcolo il prezzo totale , se la quantità è cambiata, il prezzo totale cambia, al contrario, rimane invariato
			totale = totale  + (ordineArticolo.getQuantity() * articleRepo.getOne(dettaglio.getIdArticolo()).getPrezzo());
		}
		ordine.setTotale(totale);
		orderRepo.save(ordine);
		return ordine.getIdOrdine();
	}

	@Override
	public void cancellaOrdineTramiteId(OrderRequest orderRequest) {
		//elimino l'ordine tramite id
		//in automatico elimina tutti i campi presente nella tabella ordine_articolo con quell'id
		//questo perchè le entity ordine e articolo hanno " cascade = CascadeType.REMOVE"
		orderRepo.deleteById(orderRequest.getIdOrdine());
	}

	@Override
	public OrderResponse view(Integer idOrdine) {

		OrderResponse response = new OrderResponse();
		//prendo un ordine tramite id esistente nel database
		Ordine ordine = orderRepo.getOne(idOrdine);
		//prendo una lista di ordini_articoli presenti nel database,ricercando per ordine
		List<OrdineArticolo> ordini_articoli = articleOrderRepo.findByOrdine(ordine);
		//creo una nuova lista di DettaglioArticoli
		List<DettaglioArticoli> list = new ArrayList<>();
		//creo un oggetto di DettaglioArticoli
		DettaglioArticoli dettagli;
		for(OrdineArticolo o : ordini_articoli) {
			//accedo alla lista di ordini_articoli, e popolo l oggetto DettaglioArticoli
			dettagli = new DettaglioArticoli(o.getArticolo().getIdArticolo(), o.getQuantity(),o.getArticolo().getDescrizione());
			//aggiungo l'oggetto alla lista
			list.add(dettagli);
		}
		//popolo l'oggetto response
		response.setIdOrdine(ordine.getIdOrdine());
		response.setNumeroOrdine(ordine.getNumeroOrdine());
		response.setDettagliArticoli(list);
		response.setTotale(ordine.getTotale());
		return response;		
	}
	
	//creo un metodo per autogenerarmi il numero dell' ordine
	public String generateNumeroOrdine() {
		int length = 10;
	    boolean useLetters = true;
	    boolean useNumbers = false;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

	  return  generatedString;
	}
}
