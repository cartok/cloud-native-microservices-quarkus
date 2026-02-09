-- TODO: Weis noch nicht genau wie ich eine normale DB initialisieren sollte.

create table smartbar.ArticleEntity
(
    id          bigint not null,
    description varchar(255),
    name        varchar(255),
    picture     varchar(255),
    price       numeric(38, 2),
    primary key (id)
);

create table smartbar.CategoryEntity
(
    id          bigint not null,
    description varchar(255),
    name        varchar(255),
    primary key (id)
);

create table smartbar.TableEntity
(
    id        bigint not null,
    active    boolean,
    name      varchar(255),
    seatCount integer,
    primary key (id)
);

create sequence smartbar.ArticleEntity_SEQ start with 1 increment by 50;

create sequence smartbar.CategoryEntity_SEQ start with 1 increment by 50;

create sequence smartbar.TableEntity_SEQ start with 1 increment by 50;
