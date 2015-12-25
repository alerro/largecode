package largetest.dao.impl;

import largetest.dao.IRestaurantDAO;
import largetest.domain.Restaurant;
import largetest.domain.dto.DishDTO;
import largetest.domain.dto.RestaurantDTO;
import largetest.domain.dto.RestaurantsDTO;
import largetest.rawconnection.Connections;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class RestaurantDAOImpl extends RootDAOImpl<Restaurant>  implements IRestaurantDAO {
    private final static Logger logger = LoggerFactory.getLogger(RestaurantDAOImpl.class);

    public RestaurantDAOImpl() {
        super("largetest.domain.Restaurant", Restaurant.class);
    }

    @Override
    public boolean isRestaurantUnique(RestaurantDTO dto) {
        Query query = getSession().createQuery("select count (*) from Restaurant where name = :name and address = :address");
        query.setString("name", dto.getName());
        query.setString("address", dto.getAddress());
        return ((Long) query.uniqueResult()) == 0;
    }

    @Override
    public RestaurantsDTO getRestaurants() {
        RestaurantsDTO result = null;

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection connection = Connections.getJNDIConnection();
        try {
            preparedStatement = connection.prepareStatement("" +
                    "select \n" +
                    "r.id as restaurantId,\n" +
                    "r.name as restaurantName,\n" +
                    "r.address as restaurantAddress,\n" +
                    "d.id as dishId,\n" +
                    "d.name as dishName\n" +
                    "from lt_restaurant r\n" +
                    "left join lt_dish d on d.restaurant_id=r.id");


            rs = preparedStatement.executeQuery();
            Map<Long,RestaurantDTO> restMap = null;

            if (rs != null) {
                restMap = new HashMap<Long, RestaurantDTO>();
                result = new RestaurantsDTO();
                while (rs.next()) {
                    Long restaurantId = rs.getLong("restaurantId");
                    RestaurantDTO dto = restMap.get(restaurantId);
                    if(dto == null){
                        dto = new RestaurantDTO();
                        dto.setId(restaurantId);
                        dto.setName(rs.getString("restaurantName"));
                        dto.setAddress(rs.getString("restaurantAddress"));
                        dto.setDishes(new HashSet<DishDTO>());
                        restMap.put(restaurantId,dto);
                    }
                    Long dishId = rs.getLong("dishId");
                    if(dishId == null || dishId == 0) continue;
                    DishDTO dishDTO = new DishDTO();
                    dishDTO.setId(dishId);
                    dishDTO.setName(rs.getString("dishName"));
                    dto.getDishes().add(dishDTO);
                }
                result.setRestaurants(restMap.values());
            }
            return result;
        } catch (SQLException e) {
            logger.error("Failed to execute a query", e);
            e.printStackTrace();
        } finally {
            closeAfterRaw(connection, preparedStatement, rs);
        }
        return null;
    }
}

