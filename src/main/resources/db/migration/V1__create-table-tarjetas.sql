create table tarjetas(
    folio bigint not null auto_increment,
    tipo varchar(100) not null,
    monto double not null,
    activo tinyint,

    primary key(folio)
);