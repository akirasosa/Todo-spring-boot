package myapps.jwtapp;

import myapps.jwtapp.service.TokenAuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public class StatelessAuthenticationFilter extends GenericFilterBean {

    @SuppressWarnings("UnusedDeclaration")
    private static final Logger logger = LoggerFactory.getLogger(StatelessAuthenticationFilter.class);

    private TokenAuthenticationService tokenAuthenticationService;

    public StatelessAuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            UserDetails userDetails = tokenAuthenticationService.authenticate((HttpServletRequest) request);
            UserAuthentication authentication = new UserAuthentication(userDetails);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            tokenAuthenticationService.addTokenToHeader((HttpServletResponse) response, userDetails.getUsername());
        } catch (SignatureException | NoSuchAlgorithmException | InvalidKeyException e) {
            logger.debug(e.getMessage());
        }
        chain.doFilter(request, response);
    }

}
