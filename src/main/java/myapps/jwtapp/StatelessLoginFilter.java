package myapps.jwtapp;

import myapps.jwtapp.service.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

    @SuppressWarnings("UnusedDeclaration")
    private static final Logger logger = LoggerFactory.getLogger(StatelessLoginFilter.class);

    UserDetailsService userDetailsService;

    JWTService jwtService;

    public StatelessLoginFilter(String defaultFilterProcessesUrl,
                                UserDetailsService userDetailsService,
                                JWTService jwtService,
                                AuthenticationManager authenticationManager) {

        super(defaultFilterProcessesUrl);
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        final UsernamePasswordAuthenticationToken loginToken =
                new UsernamePasswordAuthenticationToken(username, password);

        return getAuthenticationManager().authenticate(loginToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        Map<String, Object> payload = new HashMap<>();
        payload.put("username", authResult.getName());

        response.setHeader("X-Auth-Token", jwtService.encode(payload));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
