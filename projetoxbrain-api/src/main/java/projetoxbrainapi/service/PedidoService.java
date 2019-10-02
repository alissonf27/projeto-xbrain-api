package projetoxbrainapi.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import projetoxbrainapi.model.Cliente;
import projetoxbrainapi.model.Pedido;
import projetoxbrainapi.model.Produto;
import projetoxbrainapi.repository.ClienteRepository;
import projetoxbrainapi.repository.PedidoRepository;
import projetoxbrainapi.repository.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Pedido atualizar(Long id, Pedido pedido) {
        Optional<Pedido> pedidoSalvo = this.pedidoRepository.findById(id);

        if (pedidoSalvo.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        BeanUtils.copyProperties(pedido, pedidoSalvo, "id");

        return this.pedidoRepository.save(pedidoSalvo.get());
    }
	
	/**
	 * Metodo que atribui endereço de entrega e valor total dos produtos ao pedido a ser persistido no BD
	 * 
	 * @param pedido
	 * @return pedido
	 */
	public Pedido atribuir(Pedido pedido) {
		String id_produtos[] = pedido.getProdutos().split(Pattern.quote(","));
		
		BigDecimal valor_total = this.atribuirValorTotal(id_produtos);
		String endereco_entrega = this.atribuirEnderecoFinal(pedido.getCliente().getId());
		pedido.setValor_total(valor_total);
		pedido.setEndereco_entrega(endereco_entrega);
		
		return pedido;
	}
	
	private String atribuirEnderecoFinal(Long id_cliente) {
		Optional<Cliente> cliente = this.clienteRepository.findById(id_cliente);
		return cliente.get().getEndereco().toString();
	}

	public BigDecimal atribuirValorTotal(String[] id_produtos) {
		BigDecimal valor_total = BigDecimal.ZERO;
		
		for(int i = 0; i < id_produtos.length; i++) {
			Optional<Produto> produto = this.produtoRepository.findById(Long.parseLong(id_produtos[i]));
			valor_total = valor_total.add(produto.get().getValor());
		}
		
		return valor_total;
	}
}
