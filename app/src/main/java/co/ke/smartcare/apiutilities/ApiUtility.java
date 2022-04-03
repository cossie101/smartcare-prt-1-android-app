package co.ke.smartcare.apiutilities;

public class ApiUtility {

    public ApiUtility() {}

    public static final String BASE_URL = "http://192.168.1.191:8088/smartcare/";

    public static ApiService getAPIService() {

        return RetrofitApi.getClient(BASE_URL).create(ApiService.class);
    }
}
