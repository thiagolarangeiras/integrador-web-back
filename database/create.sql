CREATE TABLE usuario(
    id SERIAL NOT NULL PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    email VARCHAR
);

CREATE TABLE vendedor(
    id SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR NOT NULL,
    descricao VARCHAR,
    cpf VARCHAR,
    telefone VARCHAR,
    email VARCHAR
);

CREATE TABLE fornecedor(
    id SERIAL NOT NULL PRIMARY KEY, 
    nome VARCHAR NOT NULL,
    cpf_cnpj VARCHAR NOT NULL,
    endereco VARCHAR NOT NULL,
    telefone VARCHAR NOT NULL,
    email VARCHAR NOT NULL
);

CREATE TABLE cliente(
    id SERIAL NOT NULL PRIMARY KEY,
    id_vendedor INT NOT NULL,
    nome_pessoa VARCHAR,
    nome_empresa VARCHAR,
    cpf_cnpj VARCHAR,
    endereco VARCHAR,
    telefone VARCHAR,
    email VARCHAR,
    descricao VARCHAR
);

ALTER TABLE cliente ADD CONSTRAINT fk_cliente1 FOREIGN KEY (id_vendedor) REFERENCES vendedor(id);

CREATE TABLE marca(
    id SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR NOT NULL
);

CREATE TABLE produto(
    id SERIAL NOT NULL PRIMARY KEY,
    id_fornecedor INT NOT NULL,
    id_marca INT NOT NULL,
    nome VARCHAR, 
    descricao VARCHAR,  
    valor_compra DECIMAL(15, 4),
    valor_venda DECIMAL(15, 4),
    qt_estoque INT
);

ALTER TABLE produto ADD CONSTRAINT fk_produto1 FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id);
ALTER TABLE produto ADD CONSTRAINT fk_produto2 FOREIGN KEY (id_marca) REFERENCES marca(id);

-- aaaaaaaa
CREATE TABLE pedido_entrada(
    id SERIAL NOT NULL PRIMARY KEY,
    id_fornecedor INT NOT NULL,
    valor_total DECIMAL(15, 4),
    valor_frete DECIMAL(15, 4)
);

ALTER TABLE pedido_entrada ADD CONSTRAINT fk_pedido_entrada1 FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id);

CREATE TABLE pedido_entrada_produto(
    id SERIAL NOT NULL PRIMARY KEY,
    id_pedido_entrada INT NOT NULL,
    id_produto INT NOT NULL,
    qt INT,
    valor_unitario DECIMAL(15, 4), 
    valor_total DECIMAL(15, 4)
);

ALTER TABLE pedido_entrada_produtos ADD CONSTRAINT fk_pedido_entrada_produtos1 FOREIGN KEY (id_pedido_entrada) REFERENCES pedido_entrada(id);
ALTER TABLE pedido_entrada_produtos ADD CONSTRAINT fk_pedido_entrada_produtos2 FOREIGN KEY (id_produto) REFERENCES produto(id);

CREATE TABLE pedido_saida(
    id SERIAL NOT NULL PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_vendedor INT NOT NULL,
    data_cricao DATE,
    data_entrega_prevista DATE,
    data_entrega_real DATE,
    status INT,
    descricao VARCHAR,
    tipo_pagamento INT,
    valor_total DECIMAL(15, 4),
    valor_frete DECIMAL(15, 4),
    endereco VARCHAR, --caso o do cliente seja mudado na criacao
    parcelas INT
);

ALTER TABLE pedido_saida ADD CONSTRAINT fk_pedido_saida1 FOREIGN KEY (id_cliente) REFERENCES cliente(id);
ALTER TABLE pedido_saida ADD CONSTRAINT fk_pedido_saida2 FOREIGN KEY (id_vendedor) REFERENCES vendedor(id);

CREATE TABLE pedido_saida_produtos(
    id SERIAL NOT NULL PRIMARY KEY,
    id_pedido_saida INT NOT NULL,
    id_produto INT NOT NULL,
    qt INT,
    valor_unitario DECIMAL(15, 4),
    valor_total DECIMAL(15, 4)
);

ALTER TABLE pedido_saida_produtos ADD CONSTRAINT fk_pedido_saida_produtos1 FOREIGN KEY (id_pedido_saida) REFERENCES pedido_saida(id);
ALTER TABLE pedido_saida_produtos ADD CONSTRAINT fk_pedido_saida_produtos2 FOREIGN KEY (id_produto) REFERENCES produto(id);
