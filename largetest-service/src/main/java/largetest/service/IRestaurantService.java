package largetest.service;

import largetest.domain.dto.*;

public interface IRestaurantService {
    JsonResponse addRestaurant(RestaurantDTO dto);
    JsonResponse addDishes(DishesDTO dto);
    JsonResponse updateLunchMenu(LunchMenuDTO dto);
    RestaurantsDTO getRestaurants();
    JsonResponse vote(SpringUser springUser, Long restaurantId);

}
