package largetest.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "lt_menu_item")
public class MenuItem extends Root{
    private Dish dish;
    private BigDecimal price;
    private LunchMenu lunchMenu;

    @ManyToOne
    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne
    public LunchMenu getLunchMenu() {
        return lunchMenu;
    }

    public void setLunchMenu(LunchMenu lunchMenu) {
        this.lunchMenu = lunchMenu;
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

        MenuItem item = ((MenuItem)obj);
        if(getId()!=null && getId().equals(item.getId())){
            return true;
        }

        if(dish != null && dish.equals(item.getDish())){
            if(lunchMenu != null && lunchMenu.equals(item.getLunchMenu())){
                return true;
            }
        }
        return false;
    }

}
