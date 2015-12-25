package largetest.domain.dto;

import java.util.Set;

/**
 * User: aro
 * Date: 22.12.15
 * Time: 3:05
 */

public class RestaurantDTO {
    private Long id;

    private String name;

    private String address;

    private Set<DishDTO> dishes;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<DishDTO> getDishes() {
        return dishes;
    }

    public void setDishes(Set<DishDTO> dishes) {
        this.dishes = dishes;
    }
}
