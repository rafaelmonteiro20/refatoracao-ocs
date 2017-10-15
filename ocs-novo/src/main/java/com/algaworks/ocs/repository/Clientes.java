package com.algaworks.ocs.repository;

import javax.persistence.EntityManager;

import com.algaworks.ocs.model.Cliente;

public class Clientes {

	private EntityManager manager;
	
	public Clientes() {
		
	}
	
	public void salvar(Cliente cliente) {
		manager.getTransaction().begin();
		manager.merge(cliente);
		manager.getTransaction().commit();
	}
	
	public Cliente porNumero(String numero) {
		return manager.createQuery("from Cliente where numero = :numero", Cliente.class)
					  .setParameter("numero", numero)
					  .getSingleResult();
	}
	
}
