package com.reservja.controller.beans;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.Gson;
import com.reservja.model.entity.Cliente;
import com.reservja.model.entity.Endereco;
import com.reservja.model.repository.IClienteDAO;

@RequestScoped
@Named(value = "clienteBean")
public class ClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	
	private List<Cliente> listaClientes;

	@Inject
	transient private IClienteDAO iClienteDao;

	public ClienteBean() {
		cliente = new Cliente();
	}

	public String salvar() {
		iClienteDao.save(cliente);
		cliente = new Cliente();
		this.listaClientes = null;
		return "/paginas/cadastrarcliente.xhtml?faces-redirect=true";
	}
	
	public String limpar() {
		cliente = new Cliente();
		return "/paginas/cadastrarcliente.xhtml?faces-redirect=true";
	}

	public String remove() {
		iClienteDao.remove(Cliente.class, cliente.getIdCliente());
		return "/paginas/listaclientes.xhtml?faces-redirect=true";
	}

	public String preparaAlteracao() {
		this.cliente = iClienteDao.getById(Cliente.class, cliente.getIdCliente());
		return "/paginas/cadastrarcliente.xhtml";
	}

	public List<Cliente> getListaClientes() {
		if (this.listaClientes == null) {
			this.listaClientes = iClienteDao.getAll(Cliente.class);
		}
		return listaClientes;
	}
	
	public void pesquisaCep(AjaxBehaviorEvent event) {

		try {
			URL url = new URL("https://viacep.com.br/ws/" + cliente.getEndereco().getCep() + "/json/");
			System.out.println(url);
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			String cep = "";
			StringBuilder jsonCep = new StringBuilder();

			while ((cep = br.readLine()) != null) {
				jsonCep.append(cep);
				
			}
			
			Endereco gsonAux = new Gson().fromJson(jsonCep.toString(), Endereco.class); 
			
			System.out.println(gsonAux.getCep() + gsonAux.getLogradouro() + gsonAux.getLocalidade());
			
			cliente.getEndereco().setCep(gsonAux.getCep());
			cliente.getEndereco().setLogradouro(gsonAux.getLogradouro());
			cliente.getEndereco().setComplemento(gsonAux.getComplemento());
			cliente.getEndereco().setBairro(gsonAux.getBairro());
			cliente.getEndereco().setMunicipio(gsonAux.getLocalidade());
			cliente.getEndereco().setUf(gsonAux.getUf());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public IClienteDAO getiClienteDao() {
		return iClienteDao;
	}

	public void setiClienteDao(IClienteDAO iClienteDao) {
		this.iClienteDao = iClienteDao;
	}
	
}
