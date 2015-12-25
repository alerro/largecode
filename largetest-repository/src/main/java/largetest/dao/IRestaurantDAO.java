package largetest.dao;

import largetest.domain.Restaurant;
import largetest.domain.dto.RestaurantDTO;
import largetest.domain.dto.RestaurantsDTO;

public interface IRestaurantDAO extends IRootDAO<Restaurant> {
    boolean isRestaurantUnique(RestaurantDTO dto);
    RestaurantsDTO getRestaurants();
}