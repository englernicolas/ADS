use bdcoldigo;

create table if not exists marcas (
	id int unsigned not null auto_increment,
    nome varchar(45) not null,
    primary key (id)
);

create table if not exists produtos (
	id int(5) unsigned zerofill not null auto_increment,
    categoria tinyint(1) unsigned not null,
    modelo varchar(45) not null,
    capacidade int(4) unsigned not null,
    valor decimal(7,2) unsigned not null,
    marcas_id int unsigned not null,
    primary key (id),
    index fk_produtos_marcas_idx (marcas_id asc),
    constraint fk_produtos_marcas
		foreign key (marcas_id)
        references marcas (id)
);    

select * from marcas;
select * from produtos;