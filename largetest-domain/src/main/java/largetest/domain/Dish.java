package largetest.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lt_dish")
public class Dish extends Root{
    private String name;
    private Restaurant restaurant;

    @ManyToOne
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        if(getId() == null){
            return 0;
        }
        return getId().intValue();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(this == obj){
            return true;
        }

        Dish dish = ((Dish)obj);
        if(getId()!=null && getId().equals(dish.getId())){
            return true;
        }

        if(restaurant != null && dish.getRestaurant()!= null
                && restaurant.getId()!= null && restaurant.getId().equals(dish.getRestaurant().getId())){
            if(name != null && name.equals(dish.getName())){
                return true;
            }
        }
        return false;
    }
}
