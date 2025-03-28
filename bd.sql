create database livraria;
use livraria;

create table cliente(
	id integer auto_increment primary key,
    nome varchar(50),
    cpf varchar(11) unique,
    nascimento date,
    telefone varchar(20),
    email varchar(100),
    endereco varchar (255),
    escolaridade tinyint,
    estadocivil tinyint
    )
    