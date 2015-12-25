package largetest.dao;

import largetest.domain.User;

public interface IUserDAO extends IRootDAO<User> {

    User getByEmail(String email);

}