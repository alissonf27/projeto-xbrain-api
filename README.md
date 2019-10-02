# projeto-xbrain-api

- Projeto desenvolvido no Spring Tool Suite.
- Java 8.
- Banco H2.
- Padrão MVC.
- Migrações com Flyway.
- Toda a parte de envio e consumo da fila do RabbitMQ é feita no PedidoResource/pacote 'queue'.
- Json para teste de Pedido:
{
	"produtos": "1,2,3",
	"cliente": {
		"id": 1
	}
}
