package largetest.domain.dto;

import java.util.Collection;

/**
 * User: aro
 * Date: 22.12.15
 * Time: 3:05
 */
public class RestaurantsDTO {
    private Collection<RestaurantDTO> restaurants;

    public Collection<RestaurantDTO> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Collection<RestaurantDTO> restaurants) {
        this.restaurants = restaurants;
    }
}
