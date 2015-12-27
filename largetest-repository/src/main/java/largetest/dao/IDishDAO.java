package largetest.dao;

import largetest.domain.Dish;
import largetest.domain.dto.DishDTO;

public interface IDishDAO extends IRootDAO<Dish> {
    boolean isDishUnique(DishDTO dto,Long restaurantId);
}