package br.senac.sp.livraria.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.sql.Connection;

import br.senac.sp.livraria.enumeration.Escolaridade;
import br.senac.sp.livraria.enumeration.EstadoCivil;
import br.senac.sp.livraria.model.Cliente;

public class ClienteDAO implements InterfaceDAO<Cliente> {
	
	private Connection conexao;
	private String sql;
	private PreparedStatement stmt; //Objeto que faz a conexao da string com o banco de dados
	
	public ClienteDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
	}

	@Override
	public void inserir(Cliente objeto) throws SQLException {
		// TODO Auto-generated method stub
		
		sql = "insert into cliente(nome , cpf, nascimento , telefone, email, endereco , escolaridade , estadoCivil) values(?,?,?,?,?,?,?,?)";
		stmt = conexao.prepareStatement(sql);
		stmt.setString(1, objeto.getNome());
		stmt.setString(2, objeto.getCpf());
		stmt.setDate(3, new Date(objeto.getNascimento().getTimeInMillis()));
		stmt.setString(4, objeto.getTelefone());
		stmt.setString(5, objeto.getEmail());
		stmt.setString(6, objeto.getEndereco());
		stmt.setInt(7, objeto.getEscolaridade().ordinal());
		stmt.setInt(8, objeto.getEstadoCivil().ordinal());
		stmt.execute();
		stmt.close();
	
		
	}

	@Override
	public void alterar(Cliente objeto) throws SQLException {
		// TODO Auto-generated method stub
			
		
			sql = "UPDATE cliente SET nome = ? , cpf = ?, nascimento = ? , telefone=?, email=?, endereco=? , escolaridade=? , estadoCivil=? where id=?";
		stmt = conexao.prepareStatement(sql);
		stmt.setString(1, objeto.getNome());
		stmt.setString(2, objeto.getCpf());
		stmt.setDate(3, new Date(objeto.getNascimento().getTimeInMillis()));
		stmt.setString(4, objeto.getTelefone());
		stmt.setString(5, objeto.getEmail());
		stmt.setString(6, objeto.getEndereco());
		stmt.setInt(7, objeto.getEscolaridade().ordinal());
		stmt.setInt(8, objeto.getEstadoCivil().ordinal());
		stmt.setInt(9, objeto.getId());
		stmt.execute();
		stmt.close();
		
	}

	@Override
	public void excluir(int id) throws SQLException {
		// TODO Auto-generated method stub
		
		sql = "delete from cliente where id = ?";
		stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
	}

	@Override
	public List<Cliente> listar() throws SQLException {
		// TODO Auto-generated method stub
				
			sql = "select * from clientes order by nome";
			stmt =  conexao.prepareStatement(sql);
			List<Cliente> clientes = new ArrayList<Cliente>();
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome("nome");
				cliente.setCpf(rs.getString("cpf"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setEmail(rs.getString("email"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setEscolaridade(Escolaridade.values()[rs.getInt("escolaridade")]);
				cliente.setEstadoCivil(EstadoCivil.values()[rs.getInt("estadocivil")]);
				
				Calendar nasc = Calendar.getInstance();
				nasc.setTimeInMillis(rs.getDate("nascimento").getTime());
				cliente.setNascimento(nasc);
				
				clientes.add(cliente);
			}
			
			return clientes;
			
			
			
	}

}
