package com.reservja.controller.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.reservja.model.entity.Funcionario;
import com.reservja.model.repository.IFuncionarioDAO;

@RequestScoped
@Named(value = "loginBean")
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String usuario;
	
	private String senha;
	
	@Inject
	private IFuncionarioDAO iFuncionarioDao;

	public LoginBean() {	
	}

	public String autentica() {
		
		try {
			Funcionario funcionarioUser = iFuncionarioDao.consultarUsuario(usuario, senha);
			
			if (funcionarioUser != null && usuario.contentEquals(funcionarioUser.getUsuario())
					&& senha.contentEquals(funcionarioUser.getSenha())) {

				// adicionar o usuário na sessão usuarioLogado
				FacesContext context = FacesContext.getCurrentInstance();
				ExternalContext externalContext = context.getExternalContext();
				externalContext.getSessionMap().put("usuarioLogado", funcionarioUser.getUsuario());
				System.out.println("Usuario logou.");
				return "/index.xhtml?faces-redirect=true";
			}
			return "/login.xhtml?faces-redirect=true";
			
		} catch (Exception e) {			
			e.printStackTrace();
			System.out.println("Usuario ou senha invalido.");
			return "";
		}
	}
	
	public String limpar() {
		return "/login.xhtml?faces-redirect=true";
	}
		

	public String logout() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().remove("usuarioLogado");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		httpServletRequest.getSession().invalidate();
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

	public IFuncionarioDAO getiFuncionarioDao() {
		return iFuncionarioDao;
	}

	public void setiFuncionarioDao(IFuncionarioDAO iFuncionarioDao) {
		this.iFuncionarioDao = iFuncionarioDao;
	}
	
}
