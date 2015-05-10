--<ScriptOptions statementTerminator=";"/>

CREATE TABLE item_ordem (
		id_ordem INT8,
		CANCELADO BOOL DEFAULT false,
		ID_COMBO INT4,
		ID_ITEM_ORDEM BIGSERIAL DEFAULT nextval('milkshakecia."ITEM_ORDEM_ID_ITEM_ORDEM_seq"'::regclass) NOT NULL
	);

CREATE TABLE PEDIDO (
		ID_PEDIDO BIGSERIAL DEFAULT nextval('milkshakecia."PEDIDO_ID_PEDIDO_seq"'::regclass) NOT NULL,
		DATA TIMESTAMP DEFAULT now()
	);

CREATE TABLE MESA (
		ID_MESA SERIAL DEFAULT nextval('milkshakecia."MESA_ID_MESA_seq"'::regclass) NOT NULL,
		STATUS BOOL DEFAULT false NOT NULL,
		DATA_ABERTURA TIMESTAMP DEFAULT now(),
		DESCONTO NUMERIC(3 , 2) DEFAULT 0.0
	);

CREATE TABLE SABOR (
		id_sabor SERIAL DEFAULT nextval('milkshakecia."SABOR_idSabor_seq"'::regclass) NOT NULL,
		nome VARCHAR(30) NOT NULL
	);

CREATE TABLE PRODUTO_ADICIONAL (
		ID_PRODUTO_ADICIONAL SERIAL DEFAULT nextval('milkshakecia."PRODUTO_ADICIONAL_ID_PRODUTO_ADICIONAL_seq"'::regclass) NOT NULL,
		NOME VARCHAR(20)
	);

CREATE TABLE MESA_HISTORICO (
		ID_MESA INT4 NOT NULL,
		DATA_ABERTURA TIMESTAMP,
		DESCONTO NUMERIC(3 , 2),
		dt_fechamento TIMESTAMP DEFAULT now(),
		index INT8 NOT NULL
	);

CREATE TABLE PRODUTO_SABOR (
		id_produto INT4 NOT NULL,
		id_sabor INT4 NOT NULL,
		ativo BOOL DEFAULT true NOT NULL,
		preco_adicional NUMERIC(2 , 2)
	);

CREATE TABLE ORDEM (
		id_order BIGSERIAL DEFAULT nextval('milkshakecia."ORDEM_ID_ORDER_seq"'::regclass) NOT NULL,
		IND_VIAGEM BOOL DEFAULT false,
		DOCUMENTO_NOTA VARCHAR(14),
		TIPO_PAGAMENTO INT4,
		STATUS INT2,
		DATA_ORDEM TIMESTAMP
	);

CREATE TABLE ITEM_ORDEM_DET (
		ID_ITEM_ORDEM INT8,
		id_produto INT4,
		ID_TAMANHO INT2,
		ID_SABOR INT4,
		DETALHE VARCHAR(50),
		QUANTIDADE INT4 DEFAULT 1,
		ID_ITEM_ORDEM_DET BIGSERIAL DEFAULT nextval('milkshakecia."ITEM_ORDEM_DET_ID_ITEM_ORDEM_DET_seq"'::regclass) NOT NULL
	);

CREATE TABLE PRODUTO_TAMANHO (
		id_produto INT4 NOT NULL,
		id_tamanho INT4 NOT NULL,
		preco NUMERIC(3 , 2),
		medida NUMERIC(4 , 0)
	);

CREATE TABLE produto (
		id_produto SERIAL DEFAULT nextval('milkshakecia."PRODUTO_id_produto_seq"'::regclass) NOT NULL,
		nome VARCHAR(30) NOT NULL,
		is_tamanho BOOL DEFAULT false NOT NULL,
		id_categoria INT4 NOT NULL,
		preco_padrao NUMERIC(3 , 2),
		qtd_adicional_incluso INT2 DEFAULT 0,
		preco_adicional NUMERIC(3 , 2) DEFAULT 0.0,
		imprime_cozinha BOOL DEFAULT false
	);

CREATE TABLE CATEGORIA (
		id_categoria SERIAL DEFAULT nextval('milkshakecia."CATEGORIA_id_categoria_seq"'::regclass) NOT NULL,
		nome VARCHAR(30) NOT NULL,
		mostra_tela BOOL DEFAULT false NOT NULL
	);

CREATE TABLE PRODUTO_PRODUTOS_ADICIONAIS (
		id_produto INT4 NOT NULL,
		ID_PRODUTO_ADICIONAL INT4 NOT NULL
	);

CREATE TABLE ITEM_ORDEM_DET_ADIC (
		ID_ITEM_ORDEM_DET INT8,
		ID_PRODUTO_ADIC INT4,
		ID_ITEM_ORDEM_DET_ADIC BIGSERIAL DEFAULT nextval('milkshakecia."ITEM_ORDEM_DET_ADIC_ID_ITEM_ORDEM_DET_ADIC_seq"'::regclass) NOT NULL
	);

CREATE TABLE TAMANHO (
		id_tamanho SERIAL DEFAULT nextval('milkshakecia."Tamanho_idTamanho_seq"'::regclass) NOT NULL,
		nome VARCHAR(15) NOT NULL,
		sigla VARCHAR(2) NOT NULL
	);

CREATE UNIQUE INDEX Produto_Sabor_pkey ON PRODUTO_SABOR (id_produto ASC, id_sabor ASC);

CREATE UNIQUE INDEX ITEM_ORDEM_DET_ADIC_PKEY ON ITEM_ORDEM_DET_ADIC (ID_ITEM_ORDEM_DET_ADIC ASC);

CREATE UNIQUE INDEX Tamanho_pkey ON TAMANHO (id_tamanho ASC);

CREATE UNIQUE INDEX Produto_Tamanho_pkey ON PRODUTO_TAMANHO (id_produto ASC, id_tamanho ASC);

CREATE UNIQUE INDEX MESA_pkey ON MESA (ID_MESA ASC);

CREATE UNIQUE INDEX ORDEM_PKEY ON ORDEM (id_order ASC);

CREATE UNIQUE INDEX PEDIDO_PKEY ON PEDIDO (ID_PEDIDO ASC);

CREATE UNIQUE INDEX MESA_HISTORICO_PKEY ON MESA_HISTORICO (ID_MESA ASC, index ASC);

CREATE UNIQUE INDEX Categoria_pkey ON CATEGORIA (id_categoria ASC);

CREATE UNIQUE INDEX Produto_pkey ON produto (id_produto ASC);

CREATE UNIQUE INDEX Sabor_pkey ON SABOR (id_sabor ASC);

CREATE UNIQUE INDEX MESA_HISTORICO_UKEY ON MESA_HISTORICO (ID_MESA ASC, index ASC);

CREATE UNIQUE INDEX PRODUTO_ADICIONAL_PK ON PRODUTO_ADICIONAL (ID_PRODUTO_ADICIONAL ASC);

CREATE UNIQUE INDEX ITEM_ORDER_DET_PKEY ON ITEM_ORDEM_DET (ID_ITEM_ORDEM_DET ASC);

CREATE UNIQUE INDEX PROD_PROD_ADIC_PKEY ON PRODUTO_PRODUTOS_ADICIONAIS (id_produto ASC, ID_PRODUTO_ADICIONAL ASC);

CREATE UNIQUE INDEX ITEM_ORDEM_PKEY ON item_ordem (ID_ITEM_ORDEM ASC);

ALTER TABLE produto ADD CONSTRAINT Produto_pkey PRIMARY KEY (id_produto);

ALTER TABLE PRODUTO_TAMANHO ADD CONSTRAINT Produto_Tamanho_pkey PRIMARY KEY (id_produto, id_tamanho);

ALTER TABLE MESA ADD CONSTRAINT MESA_pkey PRIMARY KEY (ID_MESA);

ALTER TABLE ITEM_ORDEM_DET_ADIC ADD CONSTRAINT ITEM_ORDEM_DET_ADIC_PKEY PRIMARY KEY (ID_ITEM_ORDEM_DET_ADIC);

ALTER TABLE SABOR ADD CONSTRAINT Sabor_pkey PRIMARY KEY (id_sabor);

ALTER TABLE CATEGORIA ADD CONSTRAINT Categoria_pkey PRIMARY KEY (id_categoria);

ALTER TABLE ITEM_ORDEM_DET ADD CONSTRAINT ITEM_ORDER_DET_PKEY PRIMARY KEY (ID_ITEM_ORDEM_DET);

ALTER TABLE PRODUTO_ADICIONAL ADD CONSTRAINT PRODUTO_ADICIONAL_PK PRIMARY KEY (ID_PRODUTO_ADICIONAL);

ALTER TABLE PEDIDO ADD CONSTRAINT PEDIDO_PKEY PRIMARY KEY (ID_PEDIDO);

ALTER TABLE item_ordem ADD CONSTRAINT ITEM_ORDEM_PKEY PRIMARY KEY (ID_ITEM_ORDEM);

ALTER TABLE PRODUTO_SABOR ADD CONSTRAINT Produto_Sabor_pkey PRIMARY KEY (id_produto, id_sabor);

ALTER TABLE TAMANHO ADD CONSTRAINT Tamanho_pkey PRIMARY KEY (id_tamanho);

ALTER TABLE MESA_HISTORICO ADD CONSTRAINT MESA_HISTORICO_PKEY PRIMARY KEY (ID_MESA, index);

ALTER TABLE PRODUTO_PRODUTOS_ADICIONAIS ADD CONSTRAINT PROD_PROD_ADIC_PKEY PRIMARY KEY (id_produto, ID_PRODUTO_ADICIONAL);

ALTER TABLE ORDEM ADD CONSTRAINT ORDEM_PKEY PRIMARY KEY (id_order);

ALTER TABLE PRODUTO_TAMANHO ADD CONSTRAINT Produto_Tamanho_tamanho_fkey FOREIGN KEY (id_tamanho)
	REFERENCES TAMANHO (id_tamanho);

ALTER TABLE PRODUTO_TAMANHO ADD CONSTRAINT Produto_Tamanho_prod_fkey FOREIGN KEY (id_produto)
	REFERENCES produto (id_produto);

ALTER TABLE PRODUTO_SABOR ADD CONSTRAINT Produto_Sabor_Sabor_fkey FOREIGN KEY (id_sabor)
	REFERENCES SABOR (id_sabor);

ALTER TABLE PRODUTO_SABOR ADD CONSTRAINT Produto_Sabor_Prod_fkey FOREIGN KEY (id_produto)
	REFERENCES produto (id_produto);

ALTER TABLE ITEM_ORDEM_DET_ADIC ADD CONSTRAINT ITEM_ORDEM_DET_ADIC_PRODADC FOREIGN KEY (ID_PRODUTO_ADIC)
	REFERENCES PRODUTO_ADICIONAL (ID_PRODUTO_ADICIONAL);

ALTER TABLE produto ADD CONSTRAINT Produto_Categoria_fkey FOREIGN KEY (id_categoria)
	REFERENCES CATEGORIA (id_categoria);

ALTER TABLE ITEM_ORDEM_DET ADD CONSTRAINT ITEM_ORDEM_DET_PRODUTO_FKEY FOREIGN KEY (id_produto)
	REFERENCES produto (id_produto);

ALTER TABLE item_ordem ADD CONSTRAINT ITEM_ORDEM_ORDEM_FKEY FOREIGN KEY (id_ordem)
	REFERENCES ORDEM (id_order);

ALTER TABLE PRODUTO_PRODUTOS_ADICIONAIS ADD CONSTRAINT PROD_PROD_ADIC_PROD_FK FOREIGN KEY (id_produto)
	REFERENCES produto (id_produto);

ALTER TABLE PRODUTO_PRODUTOS_ADICIONAIS ADD CONSTRAINT PROD_PROD_ADIC_PRODAD_FK FOREIGN KEY (ID_PRODUTO_ADICIONAL)
	REFERENCES PRODUTO_ADICIONAL (ID_PRODUTO_ADICIONAL);

