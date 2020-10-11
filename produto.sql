create table produto (
	id serial not null,
	codproduto int not null,
	nome varchar(30) not null,
	preco int not null,
	fabricante varchar(50) not null,
	quant int not null
);