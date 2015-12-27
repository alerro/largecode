package largetest.web.controller.user;

import largetest.domain.dto.SpringUser;
import largetest.service.IRestaurantService;
import largetest.service.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VoteController {
    private final static Logger logger = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    private IRestaurantService restaurantService;


    /**
     * REST service /api/restaurant/{restaurantId}/vote
     * User can vote for restaurant using this service
     * Only for registered users. User must be signed on.
     * for example /api/restaurant/3/vote - Current user votes for restaurant with id = 3
     * Curl example -
     * curl -i -X GET  /api/restaurant/3/vote
     *
     * @param restaurantId - id of restaurant
     * @return  JsonResponse
     */
    @RequestMapping(value = "/api/restaurant/{restaurantId}/vote", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse handle(@PathVariable Long restaurantId,@AuthenticationPrincipal SpringUser springUser) {
        logger.debug("I want to have lunch at restaurant {}", restaurantId);

        return restaurantService.vote(springUser,restaurantId);
    }

}



