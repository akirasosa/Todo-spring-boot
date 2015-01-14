package myapps.jwtapp.web;

import myapps.jwtapp.UserAuthentication;
import myapps.jwtapp.domain.User;
import myapps.jwtapp.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/api/users/current", method = RequestMethod.GET)
    public User getCurrent() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UserAuthentication) {
            return ((UserAuthentication) authentication).getDetails();
        }
        return new User(authentication.getName()); //anonymous user support
    }


}
