package com.dlabs.acs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public abstract class AbstractFilter implements Filter
{
	@SuppressWarnings("unused")
	private FilterConfig filterConfig;

	public AbstractFilter()
	{

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		chain.doFilter(request, response);
	}

	/**
	 * Initialized the <code>Filter</code>.
	 * 
	 * @param filterConfig
	 *            The <code>FilterConfig</code>.
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, fConfig.getServletContext());
	}

	/**
	 * Destroy the <code>Filter</code> by setting the <code>FilterConfig</code>
	 * to null.
	 */
	public void destroy()
	{
		this.filterConfig = null;
	}

}
