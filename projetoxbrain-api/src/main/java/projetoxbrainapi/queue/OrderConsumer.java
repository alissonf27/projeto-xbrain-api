package projetoxbrainapi.queue;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import projetoxbrainapi.model.Entrega;
import projetoxbrainapi.model.Pedido;
import projetoxbrainapi.repository.EntregaRepository;

@Component
public class OrderConsumer {

	@Autowired
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@RabbitListener(queues = {"${queue.order.name}"})
    public void receive(@Payload String body) throws JsonParseException, JsonMappingException, IOException {
       Pedido pedido = mapper.readValue(body, Pedido.class);
       
       Entrega entrega = new Entrega();
       entrega.setEndereco_entrega(pedido.getEndereco_entrega());
       entrega.setPedido(pedido);
       this.entregaRepository.save(entrega);
    }
	
}
