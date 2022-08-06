package com.reservja.controller.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.reservja.model.entidades.Funcionario;
import com.reservja.model.persistencia.FuncionarioDAOJPA;
import com.reservja.model.persistencia.dao.FuncionarioDAO;

@SessionScoped
@ManagedBean(name = "loginBean")
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String usuario;
	private String senha;

	private FuncionarioDAO funcionarioDAOJPA = new FuncionarioDAOJPA();

	public LoginBean() {
	}

	public String autentica() {

		Funcionario funcionarioUser = funcionarioDAOJPA.consultarUsuario(usuario, senha);

		System.out.println(funcionarioUser.getUsuario());

		if (funcionarioUser != null && usuario.contentEquals(funcionarioUser.getUsuario())
				&& senha.contentEquals(funcionarioUser.getSenha())) {// achou o usuário

			// adicionar o usuário na sessão usuarioLogado
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", funcionarioUser.getUsuario());

			return "/index.xhtml?faces-redirect=true";
		}
		return "/login.xhtml?faces-redirect=true";
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(false);
		session.removeAttribute("usuarioLogado");
		return "/login.xhtml?faces-redirect=true";
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
