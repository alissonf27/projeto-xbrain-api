package projetoxbrainapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import projetoxbrainapi.model.Pedido;
import projetoxbrainapi.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido atualizar(Long id, Pedido pedido) {
        Optional<Pedido> pedidoSalvo = this.pedidoRepository.findById(id);

        if (pedidoSalvo.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        BeanUtils.copyProperties(pedido, pedidoSalvo, "id");

        return this.pedidoRepository.save(pedidoSalvo.get());
    }
}
