package largetest.dao;

import largetest.domain.Role;

public interface IRoleDAO extends IRootDAO<Role> {
    Role getRole(String role);
}