package eu.winwinit.bcc.service;
import eu.winwinit.bcc.model.OrderRequest;
import eu.winwinit.bcc.model.OrderResponse;

public interface OrderService {
	
//creo i metodi nell' interfaccia OrderService  che saranno implementati nella classe OrderServiceImpl
	public Integer creaOrdine(OrderRequest orderRequest);
	public Integer modificaOrdine(OrderRequest orderRequest);
	public void cancellaOrdineTramiteId(OrderRequest orderRequest);
	public OrderResponse view(Integer idOrdine);
}


