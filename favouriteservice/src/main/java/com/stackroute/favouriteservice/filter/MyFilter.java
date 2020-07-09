package com.stackroute.favouriteservice.filter;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * @author Zaithoon M
 *
 */
public class MyFilter extends GenericFilterBean {

	private Log log = LogFactory.getLog(getClass());
	@CrossOrigin(origins = "*")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		log.info("Favservice Filter -- doFilter: STARTED");
		HttpServletRequest httpreq=(HttpServletRequest)request;
		HttpServletResponse httpres=(HttpServletResponse)response;
		
		if(httpreq.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name()))
		{
			httpres.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(httpreq, httpres);
		}
		else
		{
		String headerinfo=httpreq.getHeader("authorization");
		httpres.setHeader("Access-Control-Allow-Origin", httpreq.getHeader("Origin"));
		 httpres.setHeader("Access-Control-Allow-Crendentials", "true");
		httpres.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE");
     if((headerinfo==null )|| !headerinfo.startsWith("Bearer"))
    	 throw new ServletException("Token not found");
		
	String tokeninfo=headerinfo.substring(7);
	try
	{
 		JwtParser parser= Jwts.parser().setSigningKey("ustkey");
 		Jwt jwtobj=parser.parse(tokeninfo);
 		Claims claim=(Claims) jwtobj.getBody();
 		String userid=claim.getSubject();
 		Date dt=claim.getIssuedAt();
 		
 		//final Claims claim = Jwts.parser().setSigningKey("ustkey").parseClaimsJws(tokeninfo).getBody();
			//httpreq.setAttribute("claims", claim);
		httpreq.setAttribute("subject",userid);
		log.info("Set subject as userid on http req"+httpreq.getAttribute("subject"));
 		//System.out.println("User logged in" + claim);	 
 		log.info("favservice Filter -- doFilter: USER Logged(Subject set)"+userid);
	}
	catch(SignatureException se)
	{
		throw new ServletException("Token Expired");
	}

	catch(MalformedJwtException exc)
	{
		throw new ServletException("Token modified by unauthorized person");
	}
	
	
	chain.doFilter(httpreq, httpres);
	log.info("favservice Filter -- doFilter: ENDED");
		}
	}

	
	
}
