package eu.winwinit.bcc.service;

import java.util.List;
import java.util.Optional;

import eu.winwinit.bcc.entities.Articolo;
import eu.winwinit.bcc.entities.Ordine;
import eu.winwinit.bcc.entities.OrdineArticolo;
import eu.winwinit.bcc.model.OrderRequest;
import eu.winwinit.bcc.model.OrderResponse;

public interface OrderService {

	public Integer creaOrdine(OrderRequest orderRequest);
	public Integer modificaOrdine(OrderRequest orderRequest);
	public void cancellaOrdineTramiteId(OrderRequest orderRequest);
	public OrderResponse view(Integer idOrdine);
}


