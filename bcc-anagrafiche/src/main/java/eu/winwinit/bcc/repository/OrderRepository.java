package eu.winwinit.bcc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import eu.winwinit.bcc.entities.Ordine;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Ordine,Integer> {
	
}
