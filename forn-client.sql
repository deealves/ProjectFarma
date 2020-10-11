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
	
	create table cliente(
	codigo serial not null primary key,
	nome varchar(60),
	cpf varchar(60),
	rua varchar(50),
	cidade varchar(50),
	estado varchar(20),
	cep varchar(15),
	telefone varchar(15),
	email varchar(75)
);

create table fornecedor
(
	codigo serial not null primary key,
	nome varchar(60),
	cnpj int,
	rua varchar(50),
	cidade varchar(50),
	estado varchar(20),
	cep varchar(15),
	telefone varchar(15),
	email varchar(75)
);