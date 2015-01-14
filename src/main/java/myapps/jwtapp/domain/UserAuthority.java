package myapps.jwtapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "_user_authority")
@IdClass(UserAuthority.class)
public class UserAuthority implements GrantedAuthority {

    // TODO is this right?
    @NotNull
    @ManyToOne
    @JsonIgnore
    @Id
    private User user;

    @NotNull
    @Id
    private String authority;

}
