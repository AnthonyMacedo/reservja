package com.reservja.controller.beans;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.Gson;
import com.reservja.model.entity.Endereco;
import com.reservja.model.entity.Funcionario;
import com.reservja.model.repository.IFuncionarioDAO;

@ViewScoped
@Named(value = "funcionarioBean")
public class FuncionarioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;

	private List<Funcionario> listaFuncionarios;

	@Inject
	transient private IFuncionarioDAO iFuncionarioDao;

	public FuncionarioBean() {
		funcionario = new Funcionario();
	}

	public String salvar() {

		try {
			
			iFuncionarioDao.save(funcionario);
			funcionario = new Funcionario();
			this.listaFuncionarios = null;
			msg("Funcionário cadastrado.");
			return "/paginas/funncionario.xhtml?faces-redirect=true";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public String limpar() {
		funcionario = new Funcionario();
		return "/paginas/funncionario.xhtml?faces-redirect=true";
	}

	public void remove() {
		iFuncionarioDao.remove(Funcionario.class, funcionario.getIdFuncionario());
	}

	public String preparaAlteracao() {
		this.funcionario = iFuncionarioDao.getById(Funcionario.class, funcionario.getIdFuncionario());
		return "/paginas/funncionario.xhtml?faces-redirect=true";
	}

	public List<Funcionario> getListaFuncionarios() {
		if (this.listaFuncionarios == null) {
			this.listaFuncionarios = iFuncionarioDao.getAll(Funcionario.class);
		}
		return listaFuncionarios;
	}

	public void pesquisaCep(AjaxBehaviorEvent event) {

		try {
			System.out.println(funcionario.getEndereco().getCep());
			URL url = new URL("https://viacep.com.br/ws/" + funcionario.getEndereco().getCep() + "/json/");
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

			funcionario.getEndereco().setCep(gsonAux.getCep());
			funcionario.getEndereco().setLogradouro(gsonAux.getLogradouro());
			funcionario.getEndereco().setComplemento(gsonAux.getComplemento());
			funcionario.getEndereco().setBairro(gsonAux.getBairro());
			funcionario.getEndereco().setMunicipio(gsonAux.getLocalidade());
			funcionario.getEndereco().setUf(gsonAux.getUf());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void msg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public IFuncionarioDAO getiFuncionarioDao() {
		return iFuncionarioDao;
	}

	public void setiFuncionarioDao(IFuncionarioDAO iFuncionarioDao) {
		this.iFuncionarioDao = iFuncionarioDao;
	}

}
