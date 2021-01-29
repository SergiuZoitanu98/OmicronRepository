package eu.winwinit.bcc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.winwinit.bcc.entities.Articolo;
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
		articleOrderRepo.deleteById(orderRequest.getIdOrdine());
		orderRepo.deleteById(orderRequest.getIdOrdine());
		
	}


	@Override
	public OrderResponse view(Integer idOrdine) {
		OrderResponse response = new OrderResponse();
		Ordine ordine = orderRepo.getOne(idOrdine);
		List<OrdineArticolo> articoli = articleOrderRepo.findByOrdine(ordine);
		Articolo articolo ;
		List<DettaglioArticoli> list = new ArrayList<>();
		DettaglioArticoli dettagli;
		for(OrdineArticolo o : articoli) {
			
			 dettagli = new DettaglioArticoli(o.getArticolo().getArticoloId(),o.getQuantita());
			 list.add(dettagli);
		}
		response.setIdOrdine(ordine.getIdOrdine());
		response.setNumeroOrdine(ordine.getNumeroOrdine());
		response.setDettagliArticoli(list);
		return response;		
	}


	



	

	
	
	
}
