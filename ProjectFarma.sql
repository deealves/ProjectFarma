-- Database: ProjectFarma

-- DROP DATABASE "ProjectFarma";

CREATE DATABASE "ProjectFarma"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
	
	CREATE TABLE usuario (
	
		id serial not null,
		nome varchar(50) not null,
		cpf varchar(50) not null,
		usuario varchar(50) not null,
		senha varchar(50) not null,
		primary key (id)
	
	);
	
	CREATE TABLE produto (
		
		id serial not null,
		codproduto int not null,
		nome varchar(50) not null,
		quant int not null,
		descricao varchar(50) not null,
		fabricante varchar(50) not null,
		preco float not null,
		id_venda int not null,
		id_cliente int not null,
		primary key (id),
		
		foreign key (id_venda) references venda (id),
		foreign key (id_cliente) references cliente (id)
		
	);
	
	CREATE TABLE fornecedor (
	
		id serial not null,
		nome varchar(50) not null,
		cnpj varchar(50) not null,
		email varchar(50) not null,
		telefone varchar(50) not null,
		rua varchar(50) not null,
		cidade varchar(50) not null,
		estado varchar(50) not null,
		cep varchar(50) not null,
		primary key (id)
		
	);
	
	CREATE TABLE cliente (
		
		id serial not null,
		nome varchar(50) not null,
		cpf varchar(50) not null,
		email varchar(50) not null,
		telefone varchar(50) not null,
		rua varchar(50) not null,
		cidade varchar(50) not null,
		estado varchar(50) not null,
		cep varchar(50) not null,
		primary key (id)
	
	);
	
	CREATE TABLE venda (
		
		id serial not null,
		quant int not null,
		id_usuario int not null,
		primary key (id),
		
		foreign key (id_usuario) references usuario (id)
	
	);
	
	CREATE TABLE cliente_venda (
		
		id_cliente int not null,
		id_venda int not null,
		primary key (id_cliente, id_venda),
		
		foreign key (id_cliente) references venda (id),
		foreign key (id_venda) references venda (id)
	
	);
	
	CREATE TABLE usuario_forn_prod_cliente (
		
		id_usuario int not null,
		id_fornecedor int not null,
		id_produto int not null,
		primary key (id_usuario, id_fornecedor, id_produto),
		
	
		foreign key (id_usuario) references usuario (id),
		foreign key (id_fornecedor) references fornecedor (id),
		foreign key (id_produto) references produto (id)
		
	);
	
	CREATE TABLE fornecedor_produto (
		
		id_fornecedor int not null,
		id_produto int not null,
		primary key (id_fornecedor, id_produto),
		
		foreign key (id_fornecedor) references fornecedor (id),
		foreign key (id_produto) references produto (id)
		
	);
	
	