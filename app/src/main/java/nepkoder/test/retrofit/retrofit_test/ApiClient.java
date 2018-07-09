package nepkoder.test.retrofit.retrofit_test;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by newarbhai on 7/3/18.
 */

public class ApiClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://www.fantasynepal.com/app/api/";

    /**
     * Create an instance of Retrofit object
     * */

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
