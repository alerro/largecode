package largetest.domain.dto;

import java.math.BigDecimal;

/**
 * User: aro
 * Date: 22.12.15
 * Time: 3:05
 */
public class MenuItemDTO {
    private Long dishId;
    private String dishName;
    private BigDecimal price;

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
}
