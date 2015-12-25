package largetest.service;

/**
 * User: aro
 * Date: 22.12.15
 * Time: 3:19
 */
public class JsonResponse {
    private String status = "Ok";
    private String errorMessage = "";

    public JsonResponse() {
    }

    public JsonResponse(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
