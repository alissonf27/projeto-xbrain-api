package projetoxbrainapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import projetoxbrainapi.event.RecursoCriadoEvent;
import projetoxbrainapi.model.Pedido;
import projetoxbrainapi.queue.OrderQueueSender;
import projetoxbrainapi.repository.PedidoRepository;
import projetoxbrainapi.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private OrderQueueSender sender;
	
	@GetMapping
	public List<Pedido> listar() {
		return this.pedidoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> listarPorId(@PathVariable Long id) {
		Optional<Pedido> pedido = this.pedidoRepository.findById(id);
		return pedido.isPresent() ? ResponseEntity.ok(pedido.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pedido> novo(@Valid @RequestBody Pedido pedido, HttpServletResponse response) throws JsonProcessingException {
		Pedido pedidoSalvo = this.pedidoRepository.save(this.pedidoService.atribuir(pedido));
		this.publisher.publishEvent(new RecursoCriadoEvent(this, response, pedidoSalvo.getId()));
		ObjectMapper objectMapper = new ObjectMapper();
		this.sender.send(objectMapper.writeValueAsString(pedido));
		return ResponseEntity.ok(pedidoSalvo);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @Valid @RequestBody Pedido pedido) {
		Pedido pedidoSalvo = this.pedidoService.atualizar(id, pedido);
		return ResponseEntity.ok(pedidoSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		this.pedidoRepository.delete(this.pedidoRepository.findById(id).get());
	}
	
}
