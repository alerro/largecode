package largetest.domain.dto;

import java.util.List;

/**
 * User: aro
 * Date: 22.12.15
 * Time: 3:05
 */
public class LunchMenuDTO {
    private String dateString;
    private Long restaurantId;
    private List<MenuItemDTO> menuItems;

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<MenuItemDTO> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemDTO> menuItems) {
        this.menuItems = menuItems;
    }
}
