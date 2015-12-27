package largetest.dao.impl;

import largetest.dao.IDishDAO;
import largetest.domain.Dish;
import largetest.domain.dto.DishDTO;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DishDAOImpl extends RootDAOImpl<Dish>  implements IDishDAO {
    public DishDAOImpl() {
        super("largetest.domain.Dish", Dish.class);
    }

    @Override
    public boolean isDishUnique(DishDTO dto,Long restaurantId) {
        Query query = getSession().createQuery("select count (*) from Dish where name = :name and restaurant.id = :restaurantId");
        query.setString("name", dto.getName());
        query.setLong("restaurantId", restaurantId);

        return ((Long) query.uniqueResult()) == 0;
    }

}

