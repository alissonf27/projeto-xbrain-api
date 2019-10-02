CREATE TABLE entrega (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	endereco_entrega VARCHAR(100) NOT NULL,
	id_pedido BIGINT(20) NOT NULL,
	FOREIGN KEY (id_pedido) REFERENCES pedido(id)
);