package interfaces;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public interface Base_Api {

    String BASE_URL = "https://ilcarro-backend.herokuapp.com";
    String REGISTRATION_URL = "/v1/user/registration/usernamepassword";
    String LOGIN_URL = "/v1/user/login/usernamepassword";
    String ADD_NEW_CAR_URL = "/v1/cars";

    Gson GSON = new Gson();
    MediaType JSON = MediaType.get("application/json");
    OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
}
