package largetest.service.impl;

import largetest.dao.*;
import largetest.domain.*;
import largetest.domain.dto.*;
import largetest.service.IRestaurantService;
import largetest.service.JsonResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Service
public class RestaurantServiceImpl implements IRestaurantService {
    private final static Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    private static final DateFormat ddMMyyyy_dot = new SimpleDateFormat("dd.MM.yyyy");

    private static final String STATUS_EMPTY_DATA = "EMPTY_DATA";
    private static final String STATUS_ALREADY_EXISTS = "ALREADY_EXISTS";
    private static final String STATUS_RESTAURANT_NOT_SPECIFIED = "RESTAURANT_NOT_SPECIFIED";
    private static final String STATUS_DISHES_LIST_IS_EMPTY = "DISHES_EMPTY";
    private static final String STATUS_INCORRECT_DATE = "INCORRECT_DATE";
    private static final String STATUS_USER_NOT_SPECIFIED = "RESTAURANT_NOT_SPECIFIED";
    private static final String STATUS_VOTE_TOO_LATE = "VOTE_TOO_LATE";


    @Autowired
    private IRestaurantDAO restaurantDAO;

    @Autowired
    private IDishDAO dishDAO;

    @Autowired
    private ILunchMenuDAO lunchMenuDAO;

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IVoteDAO voteDAO;

    @Override
    @Transactional
    public JsonResponse addRestaurant(RestaurantDTO dto) {
        logger.debug("Adding new restaurant with name = {}", dto.getName());
        JsonResponse response = new JsonResponse();

        if(StringUtils.isEmpty(dto.getName()) || StringUtils.isEmpty(dto.getAddress())){
            logger.debug("Restaurant name={} or address={} is empty", dto.getName(),dto.getAddress());
            response.setStatus(STATUS_EMPTY_DATA);
            response.setErrorMessage("Restaurant cannot be created. Name and address of restaurant are not filled.");
            return response;
        }

        if(!restaurantDAO.isRestaurantUnique(dto)){
            logger.debug("Restaurant with name={} and address={} already exists.", dto.getName(),dto.getAddress());
            response.setStatus(STATUS_ALREADY_EXISTS);
            response.setErrorMessage("Restaurant cannot be created. Restaurant with such name and address already exists.");
            return response;
        }

        //создаю новый ресторан
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurantDAO.save(restaurant);

        return response;
    }

    @Override
    @Transactional
    public JsonResponse addDishes(DishesDTO dto) {
        logger.debug("Adding new dishes to restaurant with id = {}", dto.getRestaurantId());
        JsonResponse response = new JsonResponse();

        //нахожу ресторан
        Restaurant restaurant = null;
        if(dto.getRestaurantId() != null){
            restaurant = restaurantDAO.load(dto.getRestaurantId());
        }
        if(restaurant == null){
            logger.debug("Restaurant is specified incorrectly.");
            response.setStatus(STATUS_RESTAURANT_NOT_SPECIFIED);
            response.setErrorMessage("Restaurant is specified incorrectly.Cannot add dishes to unknown restaurant.");
            return response;
        }


        if(CollectionUtils.isEmpty(dto.getDishes())){
            logger.debug("No dishes to add.");
            response.setStatus(STATUS_DISHES_LIST_IS_EMPTY);
            response.setErrorMessage("No dishes to add to menu.");
            return response;
        }

        //добавляю новые блюда в ресторан
        for(DishDTO dishDTO : dto.getDishes()){
            addNewDish(restaurant,dishDTO);
        }
        restaurantDAO.persist(restaurant);

        return response;

    }

    private void addNewDish(Restaurant restaurant, DishDTO dishDTO){
        if(StringUtils.isEmpty(dishDTO.getName())){
            return;
        }
        if(dishDAO.isDishUnique(dishDTO)){
            Dish dish = new Dish();
            dish.setName(dishDTO.getName());
            dish.setRestaurant(restaurant);
            restaurant.addDish(dish);
        }

    }


    @Override
    @Transactional
    public JsonResponse updateLunchMenu(LunchMenuDTO dto) {
        logger.debug("Updating lunch menu for restaurant with id = {}", dto.getRestaurantId());
        JsonResponse response = new JsonResponse();

        //нахожу ресторан
        Restaurant restaurant = null;
        if(dto.getRestaurantId() != null){
            restaurant = restaurantDAO.load(dto.getRestaurantId());
        }
        if(restaurant == null){
            logger.debug("Restaurant is specified incorrectly.");
            response.setStatus(STATUS_RESTAURANT_NOT_SPECIFIED);
            response.setErrorMessage("Restaurant is specified incorrectly.");
            return response;
        }

        //проверяю дату
        Date date = null;
        if(dto.getDateString() != null){
            try {
                date = ddMMyyyy_dot.parse(dto.getDateString());
            } catch (ParseException e) {
                logger.debug("Date specified incorrectly {}", dto.getDateString());
            }
        }
        if(date==null){
            response.setStatus(STATUS_INCORRECT_DATE);
            response.setErrorMessage("Date is empty or format is not valid.");
            return response;
        }

        //нахожу меню ресторана на этот день, если его нет, создаю
        LunchMenu lunchMenu = lunchMenuDAO.findLunchMenu(dto.getRestaurantId(),date);
        if(lunchMenu == null){
            lunchMenu = new LunchMenu();
            lunchMenu.setDate(date);
            lunchMenu.setRestaurant(restaurant);
            lunchMenuDAO.save(lunchMenu);
        }


        //Пункты меню
        if(CollectionUtils.isEmpty(dto.getMenuItems())){
            logger.debug("No dishes to add.");
            response.setStatus(STATUS_DISHES_LIST_IS_EMPTY);
            response.setErrorMessage("No dishes to add to menu.");
            return response;
        }

        //добавляю новые блюда в ресторан
        if(lunchMenu.getMenuItems()==null){
            lunchMenu.setMenuItems(new ArrayList<MenuItem>());
        }
        for(MenuItemDTO menuItemDTO : dto.getMenuItems()){
            updateMenuItem(lunchMenu, menuItemDTO);
        }
        lunchMenuDAO.persist(lunchMenu);

         return response;

    }

    private void updateMenuItem(LunchMenu lunchMenu, MenuItemDTO menuItemDTO){
        if(menuItemDTO.getDishId() == null || menuItemDTO.getPrice() == null){
            return;
        }
        Dish dish = dishDAO.get(menuItemDTO.getDishId());
        if(dish == null){
            return;
        }
        //если блюдо по ошибке оказалось из другого ресторана
        if(!lunchMenu.getRestaurant().equals(dish.getRestaurant())){
            return;
        }
        MenuItem menuItem = new MenuItem();
        menuItem.setDish(dish);
        menuItem.setLunchMenu(lunchMenu);
        int index = lunchMenu.getMenuItems().indexOf(menuItem);
        if(index<0){
            lunchMenu.addMenuItem(menuItem);
        }else{
            menuItem = lunchMenu.getMenuItems().get(index);
        }
        menuItem.setPrice(menuItemDTO.getPrice());
    }

    @Override
    public RestaurantsDTO getRestaurants() {
        return restaurantDAO.getRestaurants();
    }


    @Override
    @Transactional
    public JsonResponse vote(SpringUser springUser, Long restaurantId) {
        logger.debug("Vote for restaurant with id = {}", restaurantId);
        JsonResponse response = new JsonResponse();
        User user = userDAO.getByEmail(springUser.getUsername());
        if(user == null){
            logger.debug("Unknown user {}",springUser.getUsername());
            response.setStatus(STATUS_USER_NOT_SPECIFIED);
            response.setErrorMessage("User is specified incorrectly.");
            return response;
        }
        Restaurant restaurant = restaurantDAO.get(restaurantId);
        if(restaurant == null){
            logger.debug("Unknown restaurant {}",restaurantId);
            response.setStatus(STATUS_RESTAURANT_NOT_SPECIFIED);
            response.setErrorMessage("Restaurant is specified incorrectly.");
            return response;
        }

        Date currentDate = new Date();
        Date truncatedDate = DateUtils.truncate(currentDate, Calendar.DATE);
        Vote vote = voteDAO.findVote(truncatedDate,user.getId());
        if(vote == null){
            vote = new Vote();
            vote.setDate(currentDate);
            vote.setRestaurant(restaurant);
            vote.setUser(user);
            voteDAO.save(vote);
        }else{
            if(currentDate.getTime()-truncatedDate.getTime()<11*60*60*1000){
                vote.setRestaurant(restaurant);
                vote.setDate(currentDate);
                voteDAO.merge(vote);
            }else {
                response.setStatus(STATUS_VOTE_TOO_LATE);
                response.setErrorMessage("Your vote is too late, you can vote again the same day only before 11.00 AM.");
            }

        }
        return response;
    }
}