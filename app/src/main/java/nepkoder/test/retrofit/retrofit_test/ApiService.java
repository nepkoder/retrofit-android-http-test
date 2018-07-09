package nepkoder.test.retrofit.retrofit_test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by newarbhai on 7/3/18.
 */

public interface ApiService {


    @Headers("Content-Type: application/json")
    @GET
    Call<User> getMyData();

    @POST("v1/sujanLogin")
    @FormUrlEncoded
    Call<ResponseBody> loginUser(@Field("email") String email,
                          @Field("password") String password);
//    Call<User> createUser(@Field("email") String email, @Field("password") String password);

    @POST("v1/sujanRegister")
    @FormUrlEncoded
    Call<ResponseBody> createUser(@Field("email") String email,
                          @Field("password") String password);
}
