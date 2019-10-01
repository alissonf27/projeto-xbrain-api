package projetoxbrainapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projetoxbrainapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
