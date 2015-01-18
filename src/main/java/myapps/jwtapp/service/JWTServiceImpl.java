package myapps.jwtapp.service;

import com.auth0.jwt.JWTSigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JWTServiceImpl implements JWTService {

    private static final Integer TEN_DAYS = 10*24*60*60;

    private final JWTSigner jwtSigner;

    @Autowired
    public JWTServiceImpl(@Value("${jwt.secret}") String secret) {
        this.jwtSigner = new JWTSigner(secret);
    }

    @Override
    public String encode(Map<String, Object> payload) {
        JWTSigner.Options options = new JWTSigner.Options();
        options.setExpirySeconds(TEN_DAYS);

        return jwtSigner.sign(payload, options);
    }

}
