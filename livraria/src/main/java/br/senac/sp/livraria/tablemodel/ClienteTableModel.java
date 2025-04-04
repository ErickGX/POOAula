package br.senac.sp.livraria.tablemodel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senac.sp.livraria.model.Cliente;

public class ClienteTableModel extends AbstractTableModel{
	
	private List<Cliente> lista;
	private String[] cabecalho = {"ID", "Nome", "CPF", "Email"};
	
	public ClienteTableModel(List<Cliente> lista) {
		this.lista = lista;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cabecalho.length;
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// TODO Auto-generated method stub
		Cliente c = lista.get(linha);
			
		switch (coluna) {
		case 0:
			return c.getId();
			
		case 1:
			return c.getNome();
			
		case 2:
			return c.getCpf();	
		
		
		case 3:
			return c.getEmail();	
		}
			
		return null;
	}
	
	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}
	
	@Override
	public String getColumnName(int column) {
		return cabecalho[column];
	}
	

}
