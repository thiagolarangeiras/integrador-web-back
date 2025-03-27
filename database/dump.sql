-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION pg_database_owner;

-- DROP TYPE public.cliente;

CREATE TYPE public.cliente AS (
	id int4,
	cnpj varchar(255),
	cpf varchar(255),
	descricao varchar(255),
	email varchar(255),
	endereco varchar(255),
	id_vendedor int4,
	nome_empresa varchar(255),
	nome_pessoa varchar(255),
	telefone varchar(255));

-- DROP TYPE public.fornecedor;

CREATE TYPE public.fornecedor AS (
	id int4,
	cpf_cnpj varchar(255),
	email varchar(255),
	endereco varchar(255),
	nome varchar(255),
	telefone varchar(255));

-- DROP TYPE public.marca;

CREATE TYPE public.marca AS (
	id int4,
	nome varchar(255));

-- DROP TYPE public.pedido_entrada;

CREATE TYPE public.pedido_entrada AS (
	id int4,
	valor_frete float8,
	valor_total float8,
	id_fornecedor int4);

-- DROP TYPE public.pedido_entrada_produto;

CREATE TYPE public.pedido_entrada_produto AS (
	id int4,
	qtde int4,
	valor_total float8,
	valor_unitario float8,
	id_pedido_entrada int4,
	id_produto int4);

-- DROP TYPE public.produto;

CREATE TYPE public.produto AS (
	id int4,
	categoria int4,
	descricao varchar(255),
	nome varchar(255),
	qt_estoque int4,
	valor_compra float8,
	valor_venda float8,
	id_fornecedor int4,
	id_marca int4);

-- DROP TYPE public.usuario_teste;

CREATE TYPE public.usuario_teste AS (
	id int4,
	email varchar(255),
	"password" varchar(255),
	roles _varchar,
	username varchar(255));

-- DROP TYPE public.vendedor;

CREATE TYPE public.vendedor AS (
	id int4,
	cpf varchar(255),
	descricao varchar(255),
	email varchar(255),
	nome varchar(255),
	telefone varchar(255));

-- DROP TYPE public."_cliente";

CREATE TYPE public."_cliente" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public.cliente,
	DELIMITER = ',');

-- DROP TYPE public."_fornecedor";

CREATE TYPE public."_fornecedor" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public.fornecedor,
	DELIMITER = ',');

-- DROP TYPE public."_marca";

CREATE TYPE public."_marca" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public.marca,
	DELIMITER = ',');

-- DROP TYPE public."_pedido_entrada";

CREATE TYPE public."_pedido_entrada" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public.pedido_entrada,
	DELIMITER = ',');

-- DROP TYPE public."_pedido_entrada_produto";

CREATE TYPE public."_pedido_entrada_produto" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public.pedido_entrada_produto,
	DELIMITER = ',');

-- DROP TYPE public."_produto";

CREATE TYPE public."_produto" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public.produto,
	DELIMITER = ',');

-- DROP TYPE public."_usuario_teste";

CREATE TYPE public."_usuario_teste" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public.usuario_teste,
	DELIMITER = ',');

-- DROP TYPE public."_vendedor";

CREATE TYPE public."_vendedor" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public.vendedor,
	DELIMITER = ',');

-- DROP SEQUENCE public.cliente_id_seq;

CREATE SEQUENCE public.cliente_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.fornecedor_id_seq;

CREATE SEQUENCE public.fornecedor_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.marca_id_seq;

CREATE SEQUENCE public.marca_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pedido_entrada_id_seq;

CREATE SEQUENCE public.pedido_entrada_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pedido_entrada_produto_id_seq;

CREATE SEQUENCE public.pedido_entrada_produto_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.produto_id_seq;

CREATE SEQUENCE public.produto_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.usuario_teste_id_seq;

CREATE SEQUENCE public.usuario_teste_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.vendedor_id_seq;

CREATE SEQUENCE public.vendedor_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;-- public.fornecedor definition

-- Drop table

-- DROP TABLE public.fornecedor;

CREATE TABLE public.fornecedor (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	cpf_cnpj varchar(255) NULL,
	email varchar(255) NULL,
	endereco varchar(255) NULL,
	nome varchar(255) NULL,
	telefone varchar(255) NULL,
	CONSTRAINT fornecedor_pkey PRIMARY KEY (id)
);


-- public.marca definition

-- Drop table

-- DROP TABLE public.marca;

CREATE TABLE public.marca (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	nome varchar(255) NULL,
	CONSTRAINT marca_pkey PRIMARY KEY (id)
);


-- public.usuario_teste definition

-- Drop table

-- DROP TABLE public.usuario_teste;

CREATE TABLE public.usuario_teste (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	email varchar(255) NULL,
	"password" varchar(255) NULL,
	roles _varchar NULL,
	username varchar(255) NULL,
	CONSTRAINT ukef0yvc046p98poppd662jswdv UNIQUE (email),
	CONSTRAINT ukn27d7l9uql9fn4rm82eswy0of UNIQUE (username),
	CONSTRAINT usuario_teste_pkey PRIMARY KEY (id)
);


-- public.vendedor definition

-- Drop table

-- DROP TABLE public.vendedor;

CREATE TABLE public.vendedor (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	cpf varchar(255) NULL,
	descricao varchar(255) NULL,
	email varchar(255) NULL,
	nome varchar(255) NULL,
	telefone varchar(255) NULL,
	CONSTRAINT vendedor_pkey PRIMARY KEY (id)
);


-- public.cliente definition

-- Drop table

-- DROP TABLE public.cliente;

CREATE TABLE public.cliente (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	cnpj varchar(255) NULL,
	cpf varchar(255) NULL,
	descricao varchar(255) NULL,
	email varchar(255) NULL,
	endereco varchar(255) NULL,
	id_vendedor int4 NULL,
	nome_empresa varchar(255) NULL,
	nome_pessoa varchar(255) NULL,
	telefone varchar(255) NULL,
	CONSTRAINT cliente_pkey PRIMARY KEY (id),
	CONSTRAINT fkle6w4ybb4cj4bq8yoq89fs9pq FOREIGN KEY (id_vendedor) REFERENCES public.vendedor(id)
);


-- public.pedido_entrada definition

-- Drop table

-- DROP TABLE public.pedido_entrada;

CREATE TABLE public.pedido_entrada (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	valor_frete float8 NULL,
	valor_total float8 NULL,
	id_fornecedor int4 NULL,
	CONSTRAINT pedido_entrada_pkey PRIMARY KEY (id),
	CONSTRAINT fkqjuu0m6m9jw3ixowi5bokkoap FOREIGN KEY (id_fornecedor) REFERENCES public.fornecedor(id)
);


-- public.produto definition

-- Drop table

-- DROP TABLE public.produto;

CREATE TABLE public.produto (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	categoria int4 NULL,
	descricao varchar(255) NULL,
	nome varchar(255) NULL,
	qt_estoque int4 NULL,
	valor_compra float8 NULL,
	valor_venda float8 NULL,
	id_fornecedor int4 NULL,
	id_marca int4 NULL,
	CONSTRAINT produto_pkey PRIMARY KEY (id),
	CONSTRAINT fk5vd7utkg8j7kr26vls5h1gqrg FOREIGN KEY (id_marca) REFERENCES public.marca(id),
	CONSTRAINT fkg0kbs9pp5getbcfp892wf3y1c FOREIGN KEY (id_fornecedor) REFERENCES public.fornecedor(id)
);


-- public.pedido_entrada_produto definition

-- Drop table

-- DROP TABLE public.pedido_entrada_produto;

CREATE TABLE public.pedido_entrada_produto (
	id int4 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE) NOT NULL,
	qtde int4 NULL,
	valor_total float8 NULL,
	valor_unitario float8 NULL,
	id_pedido_entrada int4 NULL,
	id_produto int4 NULL,
	CONSTRAINT pedido_entrada_produto_pkey PRIMARY KEY (id),
	CONSTRAINT fkcrrmr4wvd1d2mid3py6qlgi3i FOREIGN KEY (id_produto) REFERENCES public.produto(id),
	CONSTRAINT fkp3e4p4fpsrdd2tjsprqh5fpu FOREIGN KEY (id_pedido_entrada) REFERENCES public.pedido_entrada(id)
);