package myapps.jwtapp.service;

import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public interface TokenAuthenticationService {

    void addTokenToHeader(HttpServletResponse response, String name);

    UserDetails authenticate(HttpServletRequest request) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, IOException;

}
