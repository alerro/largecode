package largetest.domain.dto;

/**
 * User: aro
 * Date: 22.12.15
 * Time: 3:05
 */
public class DishDTO {
    private Long id;
    private String name;
    private Long restaurantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
