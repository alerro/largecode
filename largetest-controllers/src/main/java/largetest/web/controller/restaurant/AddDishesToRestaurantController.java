package largetest.web.controller.restaurant;

import largetest.domain.dto.DishesDTO;
import largetest.service.IRestaurantService;
import largetest.service.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddDishesToRestaurantController {
    private final static Logger logger = LoggerFactory.getLogger(AddDishesToRestaurantController.class);

    @Autowired
    private IRestaurantService restaurantService;


    /**
     * REST service /api/admin/restaurant/dish/add
     * Service which allows the administrator to add new dishes in restaurant.
     * Only for admins.
     *
     * Curl example -
     * curl -H "Content-Type: application/json;charset=utf-8" -X POST -d
     * '{"restaurantId": 3, "dishes" : [{"name":"Курица"},{"name":"Мясо"},{"name":"Рыба"}] }'
     * http://localhost:8080/api/admin/restaurant/dish/add
     *
     * @param dto - input data, passed as JSON, example - {"restaurantId": 3, "dishes" : [{"name":"Курица"},{"name":"Мясо"},{"name":"Рыба"}] }
     * where for restaurant with id=3 will be created 3 dishes with specified names in database
     * @return  JsonResponse
     */
    @RequestMapping(value = "/api/admin/restaurant/dish/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse handle(@RequestBody DishesDTO dto) {
        logger.debug("Adding new dishes to restaurant with id = {}", dto.getRestaurantId());
        return restaurantService.addDishes(dto);
    }

}



