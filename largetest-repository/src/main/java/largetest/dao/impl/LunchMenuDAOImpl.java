package largetest.dao.impl;

import largetest.dao.ILunchMenuDAO;
import largetest.domain.LunchMenu;
import largetest.domain.User;
import largetest.domain.dto.DishDTO;
import largetest.domain.dto.LunchMenuDTO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Repository
public class LunchMenuDAOImpl extends RootDAOImpl<LunchMenu>  implements ILunchMenuDAO {
    public LunchMenuDAOImpl() {
        super("largetest.domain.LunchMenu", LunchMenu.class);
    }

    @Override
    public LunchMenu findLunchMenu(Long restaurantId, Date date) {
        Query query = getSession().createQuery("from LunchMenu where restaurant.id = :restaurantId and date =:date ");
        query.setLong("restaurantId", restaurantId);
        query.setDate("date", date);
        List<LunchMenu> list = query.list();
        if(CollectionUtils.isEmpty(list)){
            return null;
        }else{
            return list.get(0);
        }
    }

}

