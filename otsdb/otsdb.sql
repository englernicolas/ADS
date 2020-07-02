#### OT 1
create database mercado;

use mercado;

create table categoria (
idcategoria int not null primary key auto_increment,
descricao VARCHAR(45)
);

insert into categoria(descricao) values('Carnes');

select * from categoria;

#### OT 2

create table produto (
idproduto int not null primary key auto_increment,
descricao VARCHAR(45),
preco float,
idcategoria int,
CONSTRAINT fkcategoria foreign key (idcategoria)
references categoria (idcategoria)
);

insert into produto(descricao,preco,idcategoria) values
('Escova dental', 3.50, 1),
('Creme dental', 2.90, 1),
('Presunto', 4.99, 4),
('Lençol 180 fios', 85.80, 3),
('Desinfetante', 6.99, 2);
 
select * from produto;

select p.*, c.descricao from produto as p join categoria as c on p.idcategoria = c.idcategoria and p.idcategoria = 1;
select p.preco, c.descricao from produto as p join categoria as c on p.idcategoria = c.idcategoria and p.idcategoria = 2;
select p.descricao, p.preco, c.descricao from produto as p join categoria as c on p.idcategoria = c.idcategoria;
select p.*, c.descricao from produto as p join categoria as c on p.idcategoria = c.idcategoria and p.idcategoria = 1 and p.descricao like '%dental';
select p.*, c.descricao from produto as p join categoria as c on p.idcategoria = c.idcategoria and (p.idcategoria = 1 or p.idcategoria = 2);
select * from produto where not idcategoria = 1;
select * from produto where not idcategoria = 2 and not idcategoria = 3;
select * from categoria left join produto on produto.idcategoria = categoria.idcategoria;

#### OT 3

create table vendas (
idvenda int not null primary key auto_increment,
data_venda date
);

insert into vendas(data_venda) values
('2017-01-23'),
('2017-11-04'),
('2017-07-07'),
('2017-07-08'),
('2017-07-09'),
('2017-07-10');

create table vendas_has_produto (
idvenda int,
idproduto int,
quantidade int,
CONSTRAINT fkvenda foreign key (idvenda)
references vendas (idvenda),
CONSTRAINT fkproduto foreign key (idproduto)
references produto (idproduto),
primary key (idvenda, idproduto)
);

insert into vendas_has_produto(idvenda, idproduto, quantidade) values
(1,1,5),
(1,2,2),
(1,4,6),
(2,1,2),
(3,4,1),
(3,1,4),
(4,2,2),
(4,1,3),
(5,4,4),
(6,2,1);

select * from vendas as v join vendas_has_produto as vhp on vhp.idvenda = v.idvenda and vhp.idproduto = 1;
select data_venda from vendas as v join vendas_has_produto as vhp on vhp.idvenda = v.idvenda and vhp.idproduto = 2;
select descricao from produto as p join vendas as v on v.data_venda like '%01-23';
select descricao from produto as p join vendas_has_produto as vhp on vhp.idproduto = p.idproduto;
select * from vendas as v join vendas_has_produto as vhp on vhp.idvenda = v.idvenda join produto as p on vhp.idproduto = p.idproduto and not (p.idcategoria = 1);

#### OT 4

select sum(vhp.quantidade) as total_venda_escovas from vendas_has_produto as vhp where vhp.idproduto = 1;
select quantidade * preco from vendas_has_produto as vhp join produto as p on p.idproduto = vhp.idproduto where vhp.idvenda = 2;
select sum(quantidade * preco) from vendas as v join vendas_has_produto as vhp on vhp.idvenda = v.idvenda join produto as p on vhp.idproduto = p.idproduto and v.data_venda like '%01-23';
select avg(preco) as media_produtos from vendas as v join vendas_has_produto as vhp on vhp.idvenda = v.idvenda join produto as p on vhp.idproduto = p.idproduto and v.data_venda like '%01-23';
select descricao, preco from produto where preco = (select max(preco) from produto);
select p.* from vendas as v join vendas_has_produto as vhp on vhp.idvenda = v.idvenda join produto as p on vhp.idproduto = p.idproduto and v.data_venda like '%07-07' and p.preco > 4;
select * from produto where idproduto = (select tabela_reduzida.idproduto from (select sum(quantidade) as numero_vendas, idproduto from vendas_has_produto group by idproduto order by numero_vendas desc LIMIT 1) as tabela_reduzida);
select * from produto where preco = (select min(preco) from produto);
select v.data_venda from vendas as v where v.idvenda = (select tabela_reduzida.idvenda from (select sum(quantidade) as numero_vendas, idvenda from vendas_has_produto group by idvenda order by numero_vendas asc limit 1) as tabela_reduzida);
select distinct c.descricao from categoria as c join produto as p on p.idcategoria = c.idcategoria join vendas_has_produto as vhp on vhp.idproduto = p.idproduto;
select sum(quantidade) as total_qtd_vendas from vendas_has_produto;
select sum(quantidade) as total_qtd_vendas from vendas_has_produto as vhp join vendas as v on v.idvenda = vhp.idvenda and v.data_venda between date('2017-07-08') and date('2017-07-10');
select sum(vhp.quantidade) as qtd_vendida, v.data_venda from vendas_has_produto as vhp inner join vendas as v on vhp.idvenda = v.idvenda group by vhp.idvenda order by v.data_venda desc;
select v.*, sum(vhp.quantidade) as total_vendido from vendas_has_produto as vhp inner join vendas as v on vhp.idvenda = v.idvenda inner join produto as p on p.idproduto = vhp.idproduto group by vhp.idvenda order by sum(p.preco*vhp.quantidade) desc;

#### OT 5

create table endereco (
idendereco int not null primary key auto_increment,
rua VARCHAR(45),
bairro VARCHAR(45),
numero int,
cidade VARCHAR(45)
);

create table vendedor (
idvendedor int not null primary key auto_increment,
nome VARCHAR(45),
salario float,
data_nasc DATE,
idendereco int,
CONSTRAINT fkendereco foreign key (idendereco)
references endereco (idendereco)
);

insert into endereco(rua, bairro, numero, cidade) values('Dr. João Colin', 'Centro', 2030, 'Joinville'),
('9 de Março', 'Centro', 140, 'Joinville'),
('Rio Branco', 'Centro', 389, 'Joinville');

insert into vendedor(nome, salario, data_nasc, idendereco) values
('Luciano Silva',3500.0, date('1994-03-02'),2),
('Sandro Pereira',15000.0, date('1978-07-08'),1);

select * from endereco;
select * from vendedor;
select v.nome, v.data_nasc, e.cidade from vendedor as v inner join endereco as e on e.idendereco = v.idendereco;

#### OT 6

create view dados_publicos_vendedor as select v.nome, v.data_nasc, e.cidade from vendedor as v inner join endereco as e on e.idendereco = v.idendereco;
select * from dados_publicos_vendedor;

create view dados_publicos_produto as select descricao, preco from produto;
select * from dados_publicos_produto;

#### OT 7

DELIMITER $$
create trigger tgr_salario_nao_pode_ser_menor_que_atual before update
on vendedor
for each row
begin
if (new.salario < old.salario) then
	set NEW.salario = OLD.salario;
end if;
end$$
DELIMITER ;

select * from vendedor;

update vendedor set salario = 1000 where idvendedor = 1;

DELIMITER $$
create trigger tgr_salario_inicial_novo_vendedor before insert
on vendedor
for each row
begin
	set NEW.salario = 998;
end$$
DELIMITER ;

select * from vendedor;

insert into vendedor(nome, salario, data_nasc, idendereco) values
('Luiz de Souza',1400, date('1990-01-05'),3);

DELIMITER $$
create trigger tgr_ao_deletar_vendedor after delete
on vendedor
for each row
begin
	insert into vendedor(salario) values (10);
end$$
DELIMITER ;

drop trigger tgr_ao_deletar_vendedor;

select * from vendedor;

delete from vendedor where idvendedor = 7;

#### OT 8

DELIMITER $$
CREATE PROCEDURE atualizar_preco_produto (in id_produto_selecionado int, in novo_preco float)
BEGIN
    update produto set preco = novo_preco where idproduto = id_produto_selecionado;
END $$
DELIMITER ;

select * from produto;

call atualizar_preco_produto(1,3);

DELIMITER $$
CREATE PROCEDURE aumentar_preco_dez_porcento ()
BEGIN
    update produto set preco = preco + (preco * 0.1);
END $$
DELIMITER ;

drop procedure aumentar_preco_dez_porcento;

select * from produto;

call aumentar_preco_dez_porcento();

DELIMITER $$
CREATE PROCEDURE aumentar_quantidade_vendida_como_porcentagem ()
BEGIN
    update produto as p 
    inner join (select sum(quantidade) as quantidade_total, idproduto from vendas_has_produto group by idproduto) as tabela_reduzida 
    on p.idproduto = tabela_reduzida.idproduto set preco = preco + (preco * (tabela_reduzida.quantidade_total/100)) where p.idproduto = tabela_reduzida.idproduto;
END $$
DELIMITER ;

drop procedure aumentar_quantidade_vendida_como_porcentagem;

select * from produto;

call aumentar_quantidade_vendida_como_porcentagem();

DELIMITER $$
CREATE PROCEDURE atualizar_descricao_produto (in id_produto_selecionado int, in nova_descricao varchar(45))
BEGIN
    update produto set descricao = nova_descricao where idproduto = id_produto_selecionado;
END $$
DELIMITER ;

select * from produto;

call atualizar_descricao_produto(3,'Apresuntado');