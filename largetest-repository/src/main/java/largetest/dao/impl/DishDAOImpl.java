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
    public boolean isDishUnique(DishDTO dto) {
        Query query = getSession().createQuery("select count (*) from Dish where name = :name ");
        query.setString("name", dto.getName());
        return ((Long) query.uniqueResult()) == 0;
    }

}

