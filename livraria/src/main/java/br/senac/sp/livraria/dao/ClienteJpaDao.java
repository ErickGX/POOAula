package br.senac.sp.livraria.dao;

import java.sql.SQLException;
import java.util.List;

import br.senac.sp.livraria.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ClienteJpaDao implements InterfaceDAO<Cliente> {

	private EntityManager manager;
	
	public ClienteJpaDao(EntityManager manager) {
		this.manager = manager;
		
	}
	
	@Override
	public void inserir(Cliente objeto) throws SQLException {
		this.manager.persist(objeto);	
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();
	}

	@Override
	public void alterar(Cliente objeto) throws SQLException {
		this.manager.merge(objeto);
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();
		
	}

	@Override
	public void excluir(int id) throws SQLException {
		Cliente c = buscar(id);
		this.manager.remove(c);
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();
		
	}

	@Override
	public List<Cliente> listar() throws SQLException {
		List<Cliente> lista;
		TypedQuery<Cliente> query = manager.createQuery("select c from Cliente c", Cliente.class);
		return query.getResultList();
	}

	@Override
	public Cliente buscar(int id) throws SQLException {
	    return this.manager.find(Cliente.class, id);
	}
	
	public List<Cliente> buscar(String nome){
		TypedQuery<Cliente> query = 
				manager.createQuery("select c from Cliente c where c.nome like :nome", Cliente.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();		
	}

}
