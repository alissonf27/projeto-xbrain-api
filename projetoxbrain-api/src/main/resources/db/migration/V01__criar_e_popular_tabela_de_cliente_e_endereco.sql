CREATE TABLE cliente (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	logradouro VARCHAR(50),
	numero VARCHAR(10),
	cep VARCHAR(9)
);

INSERT INTO cliente (id, nome, logradouro, numero, cep) VALUES (1, 'Bruno Alberto', 'Rua 01', '161', '86020-000');
INSERT INTO cliente (id, nome, logradouro, numero, cep) VALUES (2, 'Ricardo Arantes', 'Rua 02', '1990', '86020-063');
INSERT INTO cliente (id, nome, logradouro, numero, cep) VALUES (3, 'Maria Silva', 'Rua 03', '22', '86020-333');