package eu.winwinit.bcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.winwinit.bcc.entities.Ordine;

public interface OrderRepository extends JpaRepository<Ordine,Integer> {
	
}
