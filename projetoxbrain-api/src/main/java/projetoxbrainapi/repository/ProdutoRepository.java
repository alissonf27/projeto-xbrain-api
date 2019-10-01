package projetoxbrainapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projetoxbrainapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
}
