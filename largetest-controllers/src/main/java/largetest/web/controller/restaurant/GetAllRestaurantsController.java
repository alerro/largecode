package largetest.web.controller.restaurant;

import largetest.domain.dto.RestaurantsDTO;
import largetest.service.IRestaurantService;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

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
     * @param restaurantId - id of restaurant
     * @return  JsonResponse
     */
    @RequestMapping(value = "/api/restaurants")
    @ResponseBody
    public String handle() {
        logger.debug("Returns all restaurants");
        RestaurantsDTO restaurantsDTO = restaurantService.getRestaurants();
        String jsonInString = null;
        if(restaurantsDTO!=null){
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
            try {
                jsonInString = mapper.writeValueAsString(restaurantsDTO);
            } catch (IOException e) {
                logger.error("cannot convert object to json.");
            }
        }
        return jsonInString;
    }

}



