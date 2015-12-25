package largetest.web.controller.restaurant;

import largetest.domain.dto.RestaurantDTO;
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
public class AddRestaurantController {
    private final static Logger logger = LoggerFactory.getLogger(AddRestaurantController.class);

    @Autowired
    private IRestaurantService restaurantService;

    /**
     * REST service /api/admin/restaurant/add
     * Service which allows the administrator to create new restaurant.
     * Only for admins.
     *
     * Curl example -
     * curl -H "Content-Type: application/json;charset=utf-8" -X POST -d
     * '{"name": "Пикассо","address": "Казань Чистопольская 20"}'
     * http://localhost:8080/api/admin/restaurant/add
     *
     * @param dto - input data, passed as JSON, example - {"name": "Пикассо","address": "Казань Чистопольская 20"}
     * where 1 restaurant with name 'Пикассо' and address 'Казань Чистопольская 20' will be created in database
     * @return  JsonResponse
     */
    @RequestMapping(value = "/api/admin/restaurant/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse handle(@RequestBody RestaurantDTO dto) {
        logger.debug("Adding new restaurant with name = {}", dto.getName());
        return restaurantService.addRestaurant(dto);
    }

}



