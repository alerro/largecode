package largetest.domain.dto;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * User: aro
 * Date: 22.12.15
 * Time: 3:05
 */
public class SpringUser implements UserDetails{
    private List<RoleDTO> authorities;
    private String password;
    private String username;
    private Boolean enabled;

    @Override
    public List<RoleDTO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<RoleDTO> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
