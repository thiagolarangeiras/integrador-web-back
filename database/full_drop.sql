-- Drop Constraints
ALTER TABLE cliente DROP CONSTRAINT fk_cliente1;
ALTER TABLE pedido_entrada DROP CONSTRAINT fk_pedido_entrada1;
ALTER TABLE pedido_entrada_parcela DROP CONSTRAINT fk_pedido_entrada_parcela1;
ALTER TABLE pedido_entrada_produto DROP CONSTRAINT fk_pedido_entrada_produto1;
ALTER TABLE pedido_entrada_produto DROP CONSTRAINT fk_pedido_entrada_produto2;
ALTER TABLE pedido_saida DROP CONSTRAINT fk_pedido_saida1;
ALTER TABLE pedido_saida DROP CONSTRAINT fk_pedido_saida2;
ALTER TABLE pedido_saida_parcela DROP CONSTRAINT fk_pedido_saida_parcela1;
ALTER TABLE pedido_saida_produto DROP CONSTRAINT fk_pedido_saida_produto1;
ALTER TABLE pedido_saida_produto DROP CONSTRAINT fk_pedido_saida_produto2;
ALTER TABLE produto DROP CONSTRAINT fk_produto1;
ALTER TABLE produto DROP CONSTRAINT fk_produto2;

-- Drop table
DROP TABLE cliente;
DROP TABLE fornecedor;
DROP TABLE marca;
DROP TABLE pedido_entrada;
DROP TABLE pedido_entrada_parcela;
DROP TABLE pedido_entrada_produto;
DROP TABLE pedido_saida;
DROP TABLE pedido_saida_parcela;
DROP TABLE pedido_saida_produto;
DROP TABLE produto;
DROP TABLE usuario;
DROP TABLE vendedor;

-- Drop sequence
DROP SEQUENCE cliente_id_seq;
DROP SEQUENCE fornecedor_id_seq;
DROP SEQUENCE marca_id_seq;
DROP SEQUENCE pedido_entrada_id_seq;
DROP SEQUENCE pedido_entrada_parcela_id_seq;
DROP SEQUENCE pedido_entrada_produto_id_seq;
DROP SEQUENCE pedido_saida_id_seq;
DROP SEQUENCE pedido_saida_parcela_id_seq;
DROP SEQUENCE pedido_saida_produto_id_seq;
DROP SEQUENCE produto_id_seq;
DROP SEQUENCE usuario_id_seq;
DROP SEQUENCE vendedor_id_seq;

-- Drop type
DROP TYPE cliente;
DROP TYPE fornecedor;
DROP TYPE marca;
DROP TYPE pedido_entrada;
DROP TYPE pedido_entrada_parcela;
DROP TYPE pedido_entrada_produto;
DROP TYPE pedido_saida;
DROP TYPE pedido_saida_parcela;
DROP TYPE pedido_saida_produto;
DROP TYPE produto;
DROP TYPE usuario;
DROP TYPE vendedor;
DROP TYPE "_cliente";
DROP TYPE "_fornecedor";
DROP TYPE "_marca";
DROP TYPE "_pedido_entrada";
DROP TYPE "_pedido_entrada_parcela";
DROP TYPE "_pedido_entrada_produto";
DROP TYPE "_pedido_saida";
DROP TYPE "_pedido_saida_parcela";
DROP TYPE "_pedido_saida_produto";
DROP TYPE "_produto";
DROP TYPE "_usuario";
DROP TYPE "_vendedor";

DROP SCHEMA public;