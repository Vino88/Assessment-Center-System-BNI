package com.dlabs.acs.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.dlabs.acs.constants.SessionConstants;
import com.dlabs.acs.entity.assessement.Participant;
import com.dlabs.acs.entity.assessement.ParticipantLogDuring;
import com.dlabs.acs.entity.assessement.enumeration.ParticipantStatus;
import com.dlabs.acs.entity.participant.Batch;
import com.dlabs.acs.service.intf.assessement.IParticipantService;
import com.dlabs.acs.util.DateUtil;
import com.dlabs.acs.util.Validator;

@WebFilter(filterName = "loginfilter", urlPatterns = { "/controller/pages/view/*"})
public class LoginFilter extends AbstractFilter
{
	@Autowired
	private IParticipantService participantService;
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		StringBuffer url = httpRequest.getRequestURL();
		if (httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT) != null)
		{
			Participant participant = (Participant) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT);
			ParticipantLogDuring participantLogDuring = (ParticipantLogDuring) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_LOG_DURING);
			Batch batch = (Batch) httpRequest.getSession().getAttribute(SessionConstants.PARTICIPANT_BATCH);
			
			
			
			if(ParticipantStatus.SESSION_I.equals(participant.getParticipantStatus()))
			{
				if(url.indexOf("/pages/view/factsheet") > 0 || url.indexOf("/pages/view/cap") > 0 || url.indexOf("/pages/view/lcb") > 0 ||  url.indexOf("/pages/view/aspiration") > 0 ||  url.indexOf("/pages/view/home") > 0 || url.indexOf("/pages/view/logout") > 0)
				{
					chain.doFilter(request, response);
				}
				else if(! DateUtil.isPastDateTime(batch.getAssessementFinishTime()))
				{
					if(DateUtil.isPastDateTime(batch.getAssessementSecondHalfStartTime()))
					{
						participant.setParticipantStatus(ParticipantStatus.SESSION_II);
						participant.getCommonFields().setLastModifiedDate(new Date());
						participant.getCommonFields().setLastModifiedBy(participant.getNik());
						
						participantService.saveOrUpdate(participant);
						
						chain.doFilter(request, response);
					}
					else
					{
						httpRequest.getSession().setAttribute(SessionConstants.UNAUTHORIZED_MESSAGE, "message.simulation.validation.notinperiode.session1session2");
						httpResponse.sendRedirect(httpRequest.getContextPath() + "/controller/pages/view/logout");
					}
					
				}
				else
				{
					httpRequest.getSession().setAttribute(SessionConstants.UNAUTHORIZED_MESSAGE, "message.simulation.validation.notinperiode.session1");
					httpResponse.sendRedirect(httpRequest.getContextPath() + "/controller/pages/view/logout");
				}
				
			}
			
			else if(ParticipantStatus.SESSION_II.equals(participant.getParticipantStatus()))
			{
				if(url.indexOf("/pages/view/factsheet") > 0 || url.indexOf("/pages/view/cap") > 0 ||  url.indexOf("/pages/view/aspiration") > 0)
				{
					httpRequest.getSession().setAttribute(SessionConstants.UNAUTHORIZED_MESSAGE, "message.simulation.validation.notinperiode.session2session1");
					httpResponse.sendRedirect(httpRequest.getContextPath() + "/controller/pages/view/logout");
				}
				
				
				else
				{
					chain.doFilter(request, response);
				}
			}
			else if(url.indexOf("/pages/view/logout") > 0)
			{
				chain.doFilter(request, response);
			}
			else
			{
				httpRequest.getSession().setAttribute(SessionConstants.UNAUTHORIZED_MESSAGE, "message.simulation.validation.notinperiode.all");
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/controller/pages/view/logout");
			}
			
			
		}
		else if(url.indexOf("/pages/view/logout") > 0)
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
			
			httpRequest.getSession().invalidate();
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/controller/pages/open/auth/login");
		}
	}

}
