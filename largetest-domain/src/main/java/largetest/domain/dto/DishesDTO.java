package largetest.domain.dto;

import java.util.List;

/**
 * User: aro
 * Date: 22.12.15
 * Time: 3:05
 */
public class DishesDTO {
    private Long restaurantId;
    private List<DishDTO> dishes;

    public List<DishDTO> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishDTO> dishes) {
        this.dishes = dishes;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
