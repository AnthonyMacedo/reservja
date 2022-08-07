package com.reservja.controller.beans;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.reservja.model.entidades.Endereco;
import com.reservja.model.entidades.Funcionario;
import com.reservja.model.persistencia.FuncionarioDAOJPA;
import com.reservja.model.persistencia.dao.FuncionarioDAO;

@ViewScoped
@ManagedBean(name = "funcionarioBean")
public class FuncionarioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	List<Funcionario> listaFuncionarios;

	private FuncionarioDAOJPA funcionarioDAOJPA = new FuncionarioDAOJPA();

	public FuncionarioBean() {
		funcionario = new Funcionario();
	}

	public String salvar() {
		FuncionarioDAO dao = new FuncionarioDAOJPA();
		dao.save(funcionario);
		funcionario = new Funcionario();
		this.listaFuncionarios = null;
		return "/paginas/cadastrarfuncionario.xhtml?faces-redirect=true";
	}

	public String limpar() {
		funcionario = new Funcionario();
		return "/paginas/cadastrarfuncionario.xhtml?faces-redirect=true";
	}

	public void remove() {
		FuncionarioDAO dao = new FuncionarioDAOJPA();
		dao.remove(Funcionario.class, funcionario.getIdFuncionario());
	}

	public String preparaAlteracao() {
		FuncionarioDAO dao = new FuncionarioDAOJPA();
		this.funcionario = dao.getById(Funcionario.class, funcionario.getIdFuncionario());
		return "/paginas/cadastrarfuncionario.xhtml?faces-redirect=true";
	}

	public List<Funcionario> getListaFuncionarios() {
		if (this.listaFuncionarios == null) {
			FuncionarioDAO dao = new FuncionarioDAOJPA();
			this.listaFuncionarios = dao.getAll(Funcionario.class);
		}
		return listaFuncionarios;
	}

	@SuppressWarnings("unused")
	public String logar() {

		Funcionario funcionarioUser = funcionarioDAOJPA.consultarUsuario(funcionario.getUsuario(),
				funcionario.getSenha());

		System.out.println(funcionarioUser.getUsuario());

		if (funcionarioUser != null) {// achou o usuário

			// adicionar o usuário na sessão usuarioLogado
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", funcionarioUser.getUsuario());

			return "index.xhtml?faces-redirect=true";
		}
		return "login.xhtml?faces-redirect=true";
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(false);
		session.removeAttribute("usuarioLogado");
		return "/login.xhtml?faces-redirect=true";
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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public FuncionarioDAOJPA getFuncionarioDAOJPA() {
		return funcionarioDAOJPA;
	}

	public void setFuncionarioDAOJPA(FuncionarioDAOJPA funcionarioDAOJPA) {
		this.funcionarioDAOJPA = funcionarioDAOJPA;
	}

}
