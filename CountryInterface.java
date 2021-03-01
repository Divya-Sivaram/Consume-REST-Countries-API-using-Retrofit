import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CountryInterface {

    // This is for listing country names
    @GET("/rest/v2/all")
    Call<List<Response_data>>getAllNames();

    // This is for searching country names
    @GET("/rest/v2/name/{name}")
    Call<List<Response_data>>getName(@Path("name") String name);
    }

