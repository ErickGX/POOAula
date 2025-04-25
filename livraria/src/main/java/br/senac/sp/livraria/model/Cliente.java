package br.senac.sp.livraria.model;

import java.util.Calendar;

import br.senac.sp.livraria.enumeration.Escolaridade;
import br.senac.sp.livraria.enumeration.EstadoCivil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente {
	private int id;
	private String nome;
	private String cpf;
	private Calendar nascimento;
	private String telefone;
	private String email;
	private String endereco;
	private EstadoCivil estadoCivil;
	private Escolaridade escolaridade;
}
