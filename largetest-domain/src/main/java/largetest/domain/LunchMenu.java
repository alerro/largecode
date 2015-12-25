package largetest.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lt_lunch_menu")
public class LunchMenu extends Root{
    private Date date;
    private Restaurant restaurant;
    private List<MenuItem> menuItems;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "lunchMenu", orphanRemoval = true)
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
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
        LunchMenu lunchMenu = ((LunchMenu)obj);
        if(getId()!=null && getId().equals(lunchMenu.getId())){
            return true;
        }
        return false;
    }

    public void addMenuItem(MenuItem menuItem){
        if(menuItems == null){
            menuItems = new ArrayList<MenuItem>();
        }
        menuItems.add(menuItem);
    }


}
