create table resposta(

    id bigint not null auto_increment,
    mensagem text not null,
    topico bigint not null,
    data datetime not null,
    solucao tinyint not null,
    
    primary key(id)

);