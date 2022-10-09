package com.reservja.controller.beans;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.Gson;
import com.reservja.model.entity.Cliente;
import com.reservja.model.entity.Endereco;
import com.reservja.model.repository.IClienteDAO;

import org.primefaces.event.SelectEvent;

@RequestScoped
@Named(value = "clienteBean")
public class ClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	
	private Cliente clienteSelecionado;

	private List<Cliente> listaClientes;

	@Inject
	transient private IClienteDAO iClienteDao;

	public ClienteBean() {
		cliente = new Cliente();
	}

	public String salvar() {

		try {
			iClienteDao.save(cliente);
			this.listaClientes = null;
			msg("Cliente cadastrado.");
			cliente = new Cliente();
			return "/paginas/cadastrarcliente.xhtml";

		} catch (Exception e) {
			e.getStackTrace();
			msg("Erro no cadastrado.");
			System.out.println("Erro no cadastro.");
			return "";
		}

	}

	public String limpar() {
		cliente = new Cliente();
		return "/paginas/cadastrarcliente.xhtml?faces-redirect=true";
	}

	public String remove() {
		try {
			iClienteDao.remove(Cliente.class, cliente.getIdCliente());
			 msg("Registro removido.");
			return "/paginas/listaclientes.xhtml?faces-redirect=true";
		} catch (Exception e) {
			e.getStackTrace();
			msg("Falha ao remover registro.");
			System.out.println("Erro ao remover cliente.");
			return "/paginas/listaclientes.xhtml?faces-redirect=true";
		}
		
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
		
	/*@PostConstruct
	public void carregarPessoas() {
		listaClientes = iClienteDao.getAll(Cliente.class);
	}*/
	
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
	
	public void onRowSelect(SelectEvent<Cliente> event) {
	    clienteSelecionado = event.getObject();
	    System.out.println(clienteSelecionado.getNome());
	}

	public void msg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
	}


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public IClienteDAO getiClienteDao() {
		return iClienteDao;
	}

	public void setiClienteDao(IClienteDAO iClienteDao) {
		this.iClienteDao = iClienteDao;
	}

}
