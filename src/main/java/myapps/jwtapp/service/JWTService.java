package myapps.jwtapp.service;

import java.util.Map;

public interface JWTService {

    String encode(Map<String, Object> payload);

}
