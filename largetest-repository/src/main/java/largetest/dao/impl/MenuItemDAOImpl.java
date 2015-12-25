package largetest.dao.impl;

import largetest.dao.IMenuItemDAO;
import largetest.domain.MenuItem;
import org.springframework.stereotype.Repository;

@Repository
public class MenuItemDAOImpl extends RootDAOImpl<MenuItem>  implements IMenuItemDAO {
    public MenuItemDAOImpl() {
        super("largetest.domain.MenuItem", MenuItem.class);
    }

}

