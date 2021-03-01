import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    // For listing country names
    private static Retrofit retrofit;
    private static final String Base_Url="https://restcountries.eu";
    public static Retrofit getRetrofitInstance()
    {
        if(retrofit==null)
        {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    // For searching country
   private static final String base_url1="https://restcountries.eu";
    public static Retrofit getRetrofitInstance1()
    {
        if(retrofit==null)
        {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(base_url1)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
