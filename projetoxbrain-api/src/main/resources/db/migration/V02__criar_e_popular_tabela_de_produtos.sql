CREATE TABLE produto (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	valor DECIMAL(10,2) NOT NULL
);

INSERT INTO produto (id, descricao, valor) VALUES (1, 'Produto 01', 6500.00);
INSERT INTO produto (id, descricao, valor) VALUES (2, 'Produto 02', 120.00);
INSERT INTO produto (id, descricao, valor) VALUES (3, 'Produto 03', 300.00);
INSERT INTO produto (id, descricao, valor) VALUES (4, 'Produto 04', 1500.00);
INSERT INTO produto (id, descricao, valor) VALUES (5, 'Produto 05', 500.00);
INSERT INTO produto (id, descricao, valor) VALUES (6, 'Produto 06', 220.00);
INSERT INTO produto (id, descricao, valor) VALUES (7, 'Produto 07', 15.00);
INSERT INTO produto (id, descricao, valor) VALUES (8, 'Produto 08', 65.00);