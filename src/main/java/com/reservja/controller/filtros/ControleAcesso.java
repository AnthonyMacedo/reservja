package com.reservja.controller.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.reservja.controller.jpautil.JPAUtil;

public class ControleAcesso implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		JPAUtil.getEntityManager();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		String usuarioLogado = (String) session.getAttribute("usuarioLogado");

		String url = req.getServletPath();
		System.out.println(url);
		
		if ( precisaAutenticar(url) && usuarioLogado == null
				|| (usuarioLogado != null && usuarioLogado.trim().isEmpty())) {
			System.out.println("Filtro Invocado.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.xhtml");
			dispatcher.forward(request, response);
			return;
		} else {
			// executa as ações do request e do response
			System.out.println("chain request, response");
			
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {

	}
	
	private boolean precisaAutenticar(String url) {
		return !url.contains("/login.xhtml") && !url.contains("javax.faces.resource");
	}


}
