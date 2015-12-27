package largetest.web.controller.restaurant;

import largetest.domain.dto.LunchMenuDTO;
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
public class UpdateLunchMenuController {
    private final static Logger logger = LoggerFactory.getLogger(UpdateLunchMenuController.class);

    @Autowired
    private IRestaurantService restaurantService;


    /**
     * REST service /api/admin/restaurant/lunchmenu/update
     * Service which allows the administrator to change or create lunch menu for specified restaurant.
     * Only for admins.
     *
     * Curl example -
     * curl -H "Content-Type: application/json;charset=utf-8" -X POST -d
     * '{"restaurantId": 3, "date":"23.12.2015", "menuItems" :[{"dishId":1,"price":100},{"dishId":2,"price":200},{"dishId":3,"price":300}] }'
     * http://localhost:8080/api/admin/restaurant/lunchmenu/update
     *
     * @param dto - input data, passed as JSON, example - {"restaurantId": 3, "date":"23.12.2015", "menuItems" :[{"dishId":1,"price":100},{"dishId":2,"price":200},{"dishId":3,"price":300}] }
     * where 3 dishes with specified id and price will be added into menu of 23.12.2015 for restaurant with id=3
     * @return  JsonResponse
     */
    @RequestMapping(value = "/api/admin/restaurant/lunchmenu/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse handle(@RequestBody LunchMenuDTO dto) {
        logger.debug("Updating lunchmenu  for restaurant with id = {}", dto.getRestaurantId());
        return restaurantService.updateLunchMenu(dto);
    }

}



