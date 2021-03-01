import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response_data {
    @SerializedName("name")
    @Expose
    String name;

    public Response_data(String name) {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
