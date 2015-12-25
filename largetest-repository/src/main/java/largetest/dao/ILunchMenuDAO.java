package largetest.dao;

import largetest.domain.LunchMenu;

import java.util.Date;

public interface ILunchMenuDAO extends IRootDAO<LunchMenu> {
    LunchMenu findLunchMenu(Long restaurantId, Date date);
}