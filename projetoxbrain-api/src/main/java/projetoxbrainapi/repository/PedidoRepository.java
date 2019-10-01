package projetoxbrainapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projetoxbrainapi.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
