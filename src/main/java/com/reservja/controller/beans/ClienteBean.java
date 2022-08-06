package com.reservja.controller.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.reservja.model.entidades.Cliente;
import com.reservja.model.persistencia.ClienteDAOJPA;
import com.reservja.model.persistencia.dao.ClienteDAO;

@RequestScoped
@ManagedBean(name = "clienteBean")
public class ClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	List<Cliente> listaClientes;

	ClienteDAO dao = new ClienteDAOJPA();

	public ClienteBean() {
		cliente = new Cliente();
	}

	public String salvar() {
		dao.save(cliente);
		cliente = new Cliente();
		this.listaClientes = null;
		return "/paginas/cadastrarcliente.xhtml?faces-redirect=true";
	}

	public String remove() {
		dao.remove(Cliente.class, cliente.getIdCliente());
		return "/paginas/listaclientes.xhtml?faces-redirect=true";
	}

	public String preparaAlteracao() {
		this.cliente = dao.getById(Cliente.class, cliente.getIdCliente());
		return "/paginas/cadastrarcliente.xhtml";
	}

	public List<Cliente> getListaClientes() {
		if (this.listaClientes == null) {
			this.listaClientes = dao.getAll(Cliente.class);
		}
		return listaClientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
