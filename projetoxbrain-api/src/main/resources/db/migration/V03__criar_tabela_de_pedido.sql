CREATE TABLE pedido (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	id_cliente BIGINT(20),
	produtos VARCHAR(100) NOT NULL,
	valor_total DECIMAL(10,2) NOT NULL,
	endereco_entrega VARCHAR(50) NOT NULL,
	FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);