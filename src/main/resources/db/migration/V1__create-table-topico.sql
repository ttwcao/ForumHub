create table topico(

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensagem text not null,
    data datetime not null,
    status varchar(50) not null,
    primary key(id)

);