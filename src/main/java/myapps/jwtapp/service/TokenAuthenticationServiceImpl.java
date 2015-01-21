package myapps.jwtapp.service;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import myapps.jwtapp.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    @SuppressWarnings("UnusedDeclaration")
    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationServiceImpl.class);

    private static final Integer TEN_DAYS = 10*24*60*60;

    @Autowired
    UserDetailsService userDetailsService;

    private final JWTSigner jwtSigner;
    private final JWTVerifier jwtVerifier;

    @Autowired
    public TokenAuthenticationServiceImpl(@Value("${jwt.secret}") String secret) {
        this.jwtSigner = new JWTSigner(secret);

        // This behaviour will be fixed later.
        String encodedSecret = Base64.encodeBase64String(secret.getBytes());
        this.jwtVerifier = new JWTVerifier(encodedSecret);
    }

    @Override
    public void addTokenToHeader(HttpServletResponse response, String username) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", username);

        JWTSigner.Options options = new JWTSigner.Options();
        options.setExpirySeconds(TEN_DAYS);

        response.setHeader("X-Auth-Token", jwtSigner.sign(payload, options));
    }

    @Override
    public UserDetails authenticate(HttpServletRequest request) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        String token = request.getHeader("X-Auth-Token");
        if(token == null) return null;

        Map<String, Object> payload = jwtVerifier.verify(token);
        String username = payload.get("username").toString();
        return userDetailsService.loadUserByUsername(username);
    }

}
