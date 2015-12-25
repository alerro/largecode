package largetest.domain.dto;

import org.springframework.security.core.GrantedAuthority;

/**
 * User: aro
 * Date: 22.12.15
 * Time: 3:05
 */
public class RoleDTO implements GrantedAuthority {
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
