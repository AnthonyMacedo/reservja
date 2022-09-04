package com.reservja.controller.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.reservja.controller.jpautil.JPAUtil;

@WebFilter(urlPatterns = {"/*"})
public class ControleAcesso implements Filter {
	
	@Inject
	private JPAUtil jpaUtil;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		jpaUtil.getEntityManager();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		
		HttpSession session = req.getSession();

		String usuarioLogado = (String) session.getAttribute("usuarioLogado");
		
		String url = req.getServletPath();
		
		if ( precisaAutenticar(url) && usuarioLogado == null
				|| (usuarioLogado != null && usuarioLogado.trim().isEmpty())) {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.xhtml");
			dispatcher.forward(request, response);
			return;
			
		} else {
			chain.doFilter(request, response);
		}	
		
		//Bloquear tela de login se tiver logado.
		if (!precisaAutenticar(url) && usuarioLogado != null){
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.xhtml");
			dispatcher.forward(request, response);
			return;
		}
	}

	@Override
	public void destroy() {

	}
	
	private boolean precisaAutenticar(String url) {
		return !url.contains("/login.xhtml") && !url.contains("javax.faces.resource");
	}


}
