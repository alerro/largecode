package largetest.dao.impl;

import largetest.dao.IUserDAO;
import largetest.domain.User;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;


@Repository
public class UserDAOImpl extends RootDAOImpl<User> implements IUserDAO {
    private static final String GET_BY_EMAIL = "from User where email = ?";

    public UserDAOImpl() {
        super("largetest.domain.User", User.class);
    }

    @Override
    public User getByEmail(String email) {
        if(StringUtils.isEmpty(email)) return null;
        final Query query = getSession().createQuery(GET_BY_EMAIL).setString(0, email);
        List<User> list = query.list();
        if(CollectionUtils.isEmpty(list)){
            return null;
        }else{
            return list.get(0);
        }
    }


}