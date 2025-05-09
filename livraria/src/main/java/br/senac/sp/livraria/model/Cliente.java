package br.senac.sp.livraria.model;

import java.util.Calendar;

import br.senac.sp.livraria.enumeration.Escolaridade;
import br.senac.sp.livraria.enumeration.EstadoCivil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	@Column(unique = true)
	private String cpf;
	private Calendar nascimento;
	private String telefone;
	private String email;
	private String endereco;
	@Column(columnDefinition = "TINYINT")
	private EstadoCivil estadoCivil;
	@Column(columnDefinition = "TINYINT")
	private Escolaridade escolaridade;
}
