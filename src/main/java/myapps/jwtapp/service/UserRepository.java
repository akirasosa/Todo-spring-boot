package myapps.jwtapp.service;

import myapps.jwtapp.domain.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    User findByUsername(String username);

}
