package largetest.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "lt_user")
public class User extends Root {
    private String name;
    private String email;
    private String password;
    private Date creationDate;
    private Collection<Role> authorities;

    public User() {
        this.creationDate = new Date();
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(
            targetEntity = Role.class,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "lt_role_user",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    public Collection<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Role> authorities) {
        this.authorities = authorities;
    }
}