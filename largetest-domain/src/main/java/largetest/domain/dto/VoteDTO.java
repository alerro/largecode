package largetest.domain.dto;

/**
 * User: aro
 * Date: 22.12.15
 * Time: 3:05
 */
public class VoteDTO {
    private Long restaurantId;
    private Long votesNumber;
    private String date;

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getVotesNumber() {
        return votesNumber;
    }

    public void setVotesNumber(Long votesNumber) {
        this.votesNumber = votesNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
