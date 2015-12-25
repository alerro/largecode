package largetest.dao.impl;

import largetest.dao.IRoleDAO;
import largetest.domain.Role;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class RoleDAOImpl extends RootDAOImpl<Role> implements IRoleDAO {
    private static final String GET_BY_NAME = "from Role where authority = ?";

    public RoleDAOImpl() {
         super("largetest.domain.Role", Role.class);
    }

    @Override
    public Role getRole(String role) {
        final Query query = getSession().createQuery(GET_BY_NAME).setString(0, role);
        List<Role> list = query.list();
        if(CollectionUtils.isEmpty(list)){
            return null;
        }else{
            return list.get(0);
        }
    }

}
