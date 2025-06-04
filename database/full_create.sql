CREATE SCHEMA public AUTHORIZATION pg_database_owner;
COMMENT ON SCHEMA public IS 'standard public schema';
SET search_path TO public;

-- Create type
CREATE TYPE cliente AS (
	id int4,
	bairro varchar(255),
	cep varchar(255),
	cidade varchar(255),
	complemento varchar(255),
	cpf_cnpj varchar(255),
	descricao varchar(255),
	email varchar(255),
	estado int2,
	id_vendedor int4,
	nome_empresa varchar(255),
	nome_fantasia varchar(255),
	nome_pessoa varchar(255),
	numero varchar(255),
	rua varchar(255),
	telefone varchar(255),
	tipo int2);

CREATE TYPE fornecedor AS (
	id int4,
	cpf_cnpj varchar(255),
	email varchar(255),
	endereco varchar(255),
	nome varchar(255),
	telefone varchar(255));

CREATE TYPE marca AS (
	id int4,
	nome varchar(255));

CREATE TYPE pedido_entrada AS (
	id int4,
	data_criacao timestamp,
	data_entrega_prevista timestamp,
	data_entrega_real timestamp,
	data_vigencia timestamp,
	id_fornecedor int4,
	status int2,
	status_entrega int2,
	status_pagamento int2,
	valor_frete float8,
	valor_total float8);

CREATE TYPE pedido_entrada_parcela AS (
	id int4,
	data_vencimento timestamp,
	id_pedido_entrada int4,
	status int2,
	valor float8);

CREATE TYPE pedido_entrada_produto AS (
	id int4,
	id_pedido_entrada int4,
	id_produto int4,
	qtde int4,
	valor_total float8,
	valor_unitario float8);

CREATE TYPE pedido_saida AS (
	id int4,
	data_criacao timestamp,
	data_entrega_prevista timestamp,
	data_entrega_real timestamp,
	data_vigencia timestamp,
	id_cliente int4,
	id_vendedor int4,
	status int2,
	status_entrega int2,
	status_pagamento int2,
	valor_frete float8,
	valor_total float8);

CREATE TYPE pedido_saida_parcela AS (
	id int4,
	data_vencimento timestamp,
	id_pedido_saida int4,
	status int2,
	valor float8);

CREATE TYPE pedido_saida_produto AS (
	id int4,
	id_pedido_saida int4,
	id_produto int4,
	qtde int4,
	valor_total float8,
	valor_unitario float8);

CREATE TYPE produto AS (
	id int4,
	categoria int4,
	descricao varchar(255),
	id_fornecedor int4,
	id_marca int4,
	nome varchar(255),
	qt_estoque int4,
	valor_compra float8,
	valor_venda float8);

CREATE TYPE usuario AS (
	id int4,
	cargo int2,
	email varchar(255),
	"password" varchar(255),
	username varchar(255));

CREATE TYPE vendedor AS (
	id int4,
	cpf varchar(255),
	descricao varchar(255),
	email varchar(255),
	nome varchar(255),
	telefone varchar(255));

CREATE TYPE "_cliente" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = cliente,
	DELIMITER = ',');

CREATE TYPE "_fornecedor" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = fornecedor,
	DELIMITER = ',');

CREATE TYPE "_marca" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = marca,
	DELIMITER = ',');

CREATE TYPE "_pedido_entrada" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = pedido_entrada,
	DELIMITER = ',');

CREATE TYPE "_pedido_entrada_parcela" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = pedido_entrada_parcela,
	DELIMITER = ',');

CREATE TYPE "_pedido_entrada_produto" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = pedido_entrada_produto,
	DELIMITER = ',');

CREATE TYPE "_pedido_saida" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = pedido_saida,
	DELIMITER = ',');

CREATE TYPE "_pedido_saida_parcela" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = pedido_saida_parcela,
	DELIMITER = ',');

CREATE TYPE "_pedido_saida_produto" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = pedido_saida_produto,
	DELIMITER = ',');

CREATE TYPE "_produto" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = produto,
	DELIMITER = ',');

CREATE TYPE "_usuario" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = usuario,
	DELIMITER = ',');

CREATE TYPE "_vendedor" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = vendedor,
	DELIMITER = ',');

-- Create sequence
CREATE SEQUENCE cliente_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE fornecedor_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE marca_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE pedido_entrada_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE pedido_entrada_parcela_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE pedido_entrada_produto_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE pedido_saida_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE pedido_saida_parcela_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE pedido_saida_produto_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE produto_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE usuario_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE vendedor_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Create table
CREATE TABLE cliente (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	bairro varchar(255) NULL,
	cep varchar(255) NULL,
	cidade varchar(255) NULL,
	complemento varchar(255) NULL,
	cpf_cnpj varchar(255) NULL,
	descricao varchar(255) NULL,
	email varchar(255) NULL,
	estado int2 NULL,
	id_vendedor int4 NULL,
	nome_empresa varchar(255) NULL,
	nome_fantasia varchar(255) NULL,
	nome_pessoa varchar(255) NULL,
	numero varchar(255) NULL,
	rua varchar(255) NULL,
	telefone varchar(255) NULL,
	tipo int2 NULL,
	CONSTRAINT cliente_estado_check CHECK (((estado >= 0) AND (estado <= 26))),
	CONSTRAINT cliente_pkey PRIMARY KEY (id),
	CONSTRAINT cliente_tipo_check CHECK (((tipo >= 0) AND (tipo <= 1)))
);

CREATE TABLE fornecedor (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	cpf_cnpj varchar(255) NULL,
	email varchar(255) NULL,
	endereco varchar(255) NULL,
	nome varchar(255) NULL,
	telefone varchar(255) NULL,
	CONSTRAINT fornecedor_pkey PRIMARY KEY (id)
);

CREATE TABLE marca (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	nome varchar(255) NULL,
	CONSTRAINT marca_pkey PRIMARY KEY (id)
);

CREATE TABLE pedido_entrada (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	data_criacao timestamp(6) NULL,
	data_entrega_prevista timestamp(6) NULL,
	data_entrega_real timestamp(6) NULL,
	data_vigencia timestamp(6) NULL,
	id_fornecedor int4 NULL,
	status int2 NULL,
	status_entrega int2 NULL,
	status_pagamento int2 NULL,
	valor_frete float8 NULL,
	valor_total float8 NULL,
	CONSTRAINT pedido_entrada_pkey PRIMARY KEY (id),
	CONSTRAINT pedido_entrada_status_check CHECK (((status >= 0) AND (status <= 5))),
	CONSTRAINT pedido_entrada_status_entrega_check CHECK (((status_entrega >= 0) AND (status_entrega <= 2))),
	CONSTRAINT pedido_entrada_status_pagamento_check CHECK (((status_pagamento >= 0) AND (status_pagamento <= 1)))
);

CREATE TABLE pedido_entrada_parcela (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	data_vencimento timestamp(6) NULL,
	id_pedido_entrada int4 NULL,
	status int2 NULL,
	valor float8 NULL,
	CONSTRAINT pedido_entrada_parcela_pkey PRIMARY KEY (id),
	CONSTRAINT pedido_entrada_parcela_status_check CHECK (((status >= 0) AND (status <= 4)))
);

CREATE TABLE pedido_entrada_produto (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	id_pedido_entrada int4 NULL,
	id_produto int4 NULL,
	qtde int4 NULL,
	valor_total float8 NULL,
	valor_unitario float8 NULL,
	CONSTRAINT pedido_entrada_produto_pkey PRIMARY KEY (id)
);

CREATE TABLE pedido_saida (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	data_criacao timestamp(6) NULL,
	data_entrega_prevista timestamp(6) NULL,
	data_entrega_real timestamp(6) NULL,
	data_vigencia timestamp(6) NULL,
	id_cliente int4 NOT NULL,
	id_vendedor int4 NULL,
	status int2 NULL,
	status_entrega int2 NULL,
	status_pagamento int2 NULL,
	valor_frete float8 NULL,
	valor_total float8 NULL,
	CONSTRAINT pedido_saida_pkey PRIMARY KEY (id),
	CONSTRAINT pedido_saida_status_check CHECK (((status >= 0) AND (status <= 5))),
	CONSTRAINT pedido_saida_status_entrega_check CHECK (((status_entrega >= 0) AND (status_entrega <= 2))),
	CONSTRAINT pedido_saida_status_pagamento_check CHECK (((status_pagamento >= 0) AND (status_pagamento <= 1)))
);

CREATE TABLE pedido_saida_parcela (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	data_vencimento timestamp(6) NULL,
	id_pedido_saida int4 NULL,
	status int2 NULL,
	valor float8 NULL,
	CONSTRAINT pedido_saida_parcela_pkey PRIMARY KEY (id),
	CONSTRAINT pedido_saida_parcela_status_check CHECK (((status >= 0) AND (status <= 4)))
);

CREATE TABLE pedido_saida_produto (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	id_pedido_saida int4 NULL,
	id_produto int4 NULL,
	qtde int4 NULL,
	valor_total float8 NULL,
	valor_unitario float8 NULL,
	CONSTRAINT pedido_saida_produto_pkey PRIMARY KEY (id)
);

CREATE TABLE produto (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	categoria int4 NULL,
	descricao varchar(255) NULL,
	id_fornecedor int4 NULL,
	id_marca int4 NULL,
	nome varchar(255) NULL,
	qt_estoque int4 NULL,
	valor_compra float8 NULL,
	valor_venda float8 NULL,
	CONSTRAINT produto_pkey PRIMARY KEY (id)
);

CREATE TABLE usuario (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	cargo int2 NULL,
	email varchar(255) NULL,
	"password" varchar(255) NULL,
	username varchar(255) NULL,
	CONSTRAINT uk5171l57faosmj8myawaucatdw UNIQUE (email),
	CONSTRAINT uk863n1y3x0jalatoir4325ehal UNIQUE (username),
	CONSTRAINT usuario_cargo_check CHECK (((cargo >= 0) AND (cargo <= 2))),
	CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

CREATE TABLE vendedor (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	cpf varchar(255) NULL,
	descricao varchar(255) NULL,
	email varchar(255) NULL,
	nome varchar(255) NULL,
	telefone varchar(255) NULL,
	CONSTRAINT vendedor_pkey PRIMARY KEY (id)
);

ALTER TABLE cliente ADD CONSTRAINT fk_cliente1 FOREIGN KEY (id_vendedor) REFERENCES vendedor(id);

ALTER TABLE pedido_entrada ADD CONSTRAINT fk_pedido_entrada1 FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id);
ALTER TABLE pedido_entrada_parcela ADD CONSTRAINT fk_pedido_entrada_parcela1 FOREIGN KEY (id_pedido_entrada) REFERENCES pedido_entrada(id);
ALTER TABLE pedido_entrada_produto ADD CONSTRAINT fk_pedido_entrada_produto1 FOREIGN KEY (id_pedido_entrada) REFERENCES pedido_entrada(id);
ALTER TABLE pedido_entrada_produto ADD CONSTRAINT fk_pedido_entrada_produto2 FOREIGN KEY (id_produto) REFERENCES produto(id);

ALTER TABLE pedido_saida ADD CONSTRAINT fk_pedido_saida1 FOREIGN KEY (id_cliente) REFERENCES cliente(id);
ALTER TABLE pedido_saida ADD CONSTRAINT fk_pedido_saida2 FOREIGN KEY (id_vendedor) REFERENCES vendedor(id);
ALTER TABLE pedido_saida_parcela ADD CONSTRAINT fk_pedido_saida_parcela1 FOREIGN KEY (id_pedido_saida) REFERENCES pedido_saida(id);
ALTER TABLE pedido_saida_produto ADD CONSTRAINT fk_pedido_saida_produto1 FOREIGN KEY (id_pedido_saida) REFERENCES pedido_saida(id);
ALTER TABLE pedido_saida_produto ADD CONSTRAINT fk_pedido_saida_produto2 FOREIGN KEY (id_produto) REFERENCES produto(id);

ALTER TABLE produto ADD CONSTRAINT fk_produto1 FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id);
ALTER TABLE produto ADD CONSTRAINT fk_produto2 FOREIGN KEY (id_marca) REFERENCES marca(id);
