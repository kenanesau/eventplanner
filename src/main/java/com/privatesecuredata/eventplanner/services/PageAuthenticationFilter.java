package com.privatesecuredata.eventplanner.services;

import java.io.IOException;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ComponentEventRequestParameters;
import org.apache.tapestry5.services.ComponentRequestFilter;
import org.apache.tapestry5.services.ComponentRequestHandler;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Response;

import com.privatesecuredata.eventplanner.pages.Login;

public class PageAuthenticationFilter implements ComponentRequestFilter {
	
	private final AuthenticationService authenticationService;
	private final PageRenderLinkSource pageRenderLinkSource;
	private final Response response;

	public PageAuthenticationFilter(AuthenticationService authenticationService,
			PageRenderLinkSource pageRenderLinkSource, Response response) {
		this.authenticationService = authenticationService;
		this.pageRenderLinkSource = pageRenderLinkSource;
		this.response = response;
	}
	
	@Override
	public void handleComponentEvent(ComponentEventRequestParameters para, ComponentRequestHandler handler)
			throws IOException {
		handler.handleComponentEvent(para);
	}

	@Override
	public void handlePageRender(PageRenderRequestParameters para, ComponentRequestHandler handler) throws IOException {
		
		if (para.getLogicalPageName().equals("Login") ||
				authenticationService.isAuthenticated())
			handler.handlePageRender(para);
		else {
			Link goBackToLogin = pageRenderLinkSource.createPageRenderLink(Login.class);
			response.sendRedirect(goBackToLogin);
		}

	}

}
