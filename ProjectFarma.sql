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
		primary key (id)
	
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
		data date not null,
		valor float not null,
		id_usuario int not null,
		primary key (id),
		
		foreign key (id_usuario) references usuario (id)
	
	);
	
	CREATE TABLE cliente_venda (
		
		id_cliente int not null,
		id_venda int not null,
		primary key (id_cliente, id_venda),
		
		foreign key (id_cliente) references cliente (id),
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
	
	CREATE TABLE venda_produto(
		id_venda int not null,
		id_produto int not null,
		primary key (id_venda, id_produto),
		
		foreign key (id_venda) references venda (id),
		foreign key (id_produto) references produto (id)
	);
	
	alter table venda add column valor float;
	alter table produto drop column id_venda;
	alter table produto drop column id_cliente;
	alter table produto add column id_venda int;
	alter table produto add column id_cliente int;
	alter table venda add column id_produto int;
	alter table venda add foreign key (id_produto) references produto (id);
	alter table venda drop column id_produto;
	alter table venda drop foreign key id_produto;
	alter table venda add column id_cliente int;
	alter table venda add foreign key (id_cliente) references cliente (id);
	alter table venda drop column id_cliente;
	alter table venda drop foreign key id_cliente;
	
	alter table cliente_venda drop column id_cliente;
	alter table cliente_venda drop column id_venda;
	
	alter table cliente_venda add column id_cliente int;
	alter table cliente_venda add column id_venda int;
	
	alter table cliente_venda add foreign key (id_cliente) references cliente (id);
	alter table cliente_venda add foreign key (id_venda) references venda (id);
	
	select * from venda;
	select count(id), extract(year from data) as ano, extract(month from data) as mes from venda group by ano, mes order by ano, mes
	select sum(valor), extract(year from data) as ano, extract(month from data) as mes from venda group by ano, mes order by ano, mes
	
	select * from venda;
	select * from cliente_venda;
	
	select * from cliente_venda where id_cliente != null;
	
	select venda.*, cliente.nome from venda, cliente 
	join cliente_venda on venda.id = cliente_venda.id_venda where cliente_venda.id_cliente = cliente.id 
	order by venda.valor;
	
	select venda.id, venda.quant, venda.valor, venda.data, usuario.nome,cliente.nomeC 
	from venda, cliente, cliente_venda, usuario where venda.id = cliente_venda.id_venda
	and cliente_venda.id_cliente = cliente.id and venda.id_usuario = usuario.id order by venda.valor;
	
	select venda.quant, venda.valor, venda.data, usuario.nome, cliente.nomeC, STRING_AGG(produto.nome, ',') as produto
	from produto, venda, cliente, cliente_venda, usuario, venda_produto where venda.id = venda_produto.id_venda 
	and produto.id = venda_produto.id_produto and venda.id = cliente_venda.id_venda
	and cliente_venda.id_cliente = cliente.id and venda.id_usuario = usuario.id
	group by (venda.quant, venda.valor, venda.data, usuario.nome, cliente.nomeC) order by venda.data;
	
	select venda.quant, round(cast(venda.valor as numeric), 2), venda.data, usuario.nome, cliente.nomeC, STRING_AGG(produto.nome, ',') as produto
	from produto, venda, cliente, cliente_venda, usuario, venda_produto where venda.id = venda_produto.id_venda 
	and produto.id = venda_produto.id_produto and venda.id = cliente_venda.id_venda
	and cliente_venda.id_cliente = cliente.id and venda.id_usuario = usuario.id
	group by (venda.quant, venda.valor, venda.data, usuario.nome, cliente.nomeC) order by venda.data;
	
	select venda.id, string_agg(produto.nome, ', ') as produto from produto, venda, venda_produto where venda.id = venda_produto.id_venda
	group by (venda.id);
	
	select venda.quant, round(cast(venda.valor as numeric), 2), venda.data, usuario.nome, cliente.nomeC, STRING_AGG(produto.nome, ',') as produto
	from produto, venda, cliente, cliente_venda, usuario, venda_produto where venda.id = venda_produto.id_venda 
	and produto.id = venda_produto.id_produto and venda.id = cliente_venda.id_venda
	and cliente_venda.id_cliente = cliente.id and venda.id_usuario = usuario.id and venda.id in (SELECT MAX(id) FROM venda)
	group by (venda.quant, venda.valor, venda.data, usuario.nome, cliente.nomeC) order by venda.data;
	
	alter table cliente rename nome to nomeC
	
	insert into venda (id, quant, data, id_usuario, valor, id_produto) values ()
	
	select trunc(cast(float8 (venda.valor) as numeric), 2) from venda where venda.id = 21;
	insert into venda (id, quant, data, id_usuario, trunc(cast(float8 (valor)))) values ('45', 3, '2020-11-03', '2', 4.5788);
	SELECT to_char(float8 'venda.valor', 'FM999999999.00') from venda where venda.id = 21;
	
	delete from venda_produto where venda_produto.id_venda in (select venda.id from venda where venda.id_usuario = 5);
    delete from venda where venda.id_usuario = 5;
    DELETE FROM usuario WHERE id = 5;
	
	select * from usuario
	
	SELECT COUNT(*) from pg_stat_activity;
	select min_val, max_val from pg_settings where name='max_connections';