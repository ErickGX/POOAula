package br.senac.sp.livraria.model;

import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Autor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 private String nome;
 private String sobrenome;
 private String publicacoes;
 @Column(unique = true)
 private String cpf;
 private Calendar dataNascimento;
}
