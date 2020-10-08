package com.dlabs.acs.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.util.Validator;

@WebFilter(filterName = "adminloginfilter", urlPatterns = {"/controller/pages/admin/*"})
public class AdminLoginFilter extends AbstractFilter
{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (httpRequest.getSession().getAttribute(	SessionConstants.USER_ADMIN) != null)
		{
			chain.doFilter(request, response);
		}
		else
		{
			String currentSessionId = httpRequest.getSession().getId();
			String requestedSessionId = httpRequest.getRequestedSessionId();

			if (Validator.isNotNull(requestedSessionId) && !currentSessionId.equals(requestedSessionId))
			{
				httpRequest.getSession().setAttribute(SessionConstants.UNAUTHORIZED_MESSAGE, "message.unauthorized.timeout");
			}
			else
			{
				httpRequest.getSession().setAttribute(SessionConstants.UNAUTHORIZED_MESSAGE, "message.unauthorized.admin");
			}
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/controller/pages/open/auth/admin/login");
		}
	}

}
