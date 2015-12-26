package largetest.web.controller.restaurant;

import largetest.domain.dto.RestaurantsDTO;
import largetest.service.IRestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetAllRestaurantsController {
    private final static Logger logger = LoggerFactory.getLogger(GetAllRestaurantsController.class);

    @Autowired
    private IRestaurantService restaurantService;

    /**
     * REST service /api/restaurants
     * Returns all restaurants with their dishes
     * Only for registered users. User must be signed on.
     * Curl example -
     * curl -X GET  http://localhost:8080/api/restaurants
     *
     * @return  JsonResponse
     */
    @RequestMapping(value = "/api/restaurants")
    @ResponseBody
    public RestaurantsDTO handle() {
        logger.debug("Returns all restaurants");
        return restaurantService.getRestaurants();
    }

}



