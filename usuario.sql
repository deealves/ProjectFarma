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
	
	create table usuario (
	id serial,
	nome varchar(30),
	email varchar(30),
	senha varchar(30)
	);