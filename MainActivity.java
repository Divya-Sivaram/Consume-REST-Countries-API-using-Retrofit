import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvmain;
    CountryRecyclerViewAdapter adapter;
    SearchAdapter adapter1;
       @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // For displaying country names from rest country api using retrofit
        rvmain = findViewById(R.id.recycler_view);
        rvmain.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvmain.setHasFixedSize(true);
        CountryInterface countryinterface = RetrofitClientInstance.getRetrofitInstance().create(CountryInterface.class);
        Call<List<Response_data>> listCall = countryinterface.getAllNames();
        listCall.enqueue(new Callback<List<Response_data>>() {
            @Override
            public void onResponse(Call<List<Response_data>> call, retrofit2.Response<List<Response_data>> response) {
                parseData(response.body());
            }

            @Override
            public void onFailure(Call<List<Response_data>> call, Throwable t) {

            }
        });
    }


    private void parseData(List<Response_data> body)
    {
        adapter=new CountryRecyclerViewAdapter(body,getApplicationContext());
        rvmain.setAdapter(adapter);
    }

    //This is for Searching country name
    public void Search_Country(View view) {

        EditText search = (EditText) findViewById(R.id.edittext);
        TextView textView=(TextView)findViewById(R.id.textview);

        if (TextUtils.isEmpty(search.getText().toString())) {
            Toast.makeText(getApplicationContext(),"What do you want to search?",Toast.LENGTH_SHORT).show();
        } else {
            String search_content = search.getText().toString();
            CountryInterface countryinterface = RetrofitClientInstance.getRetrofitInstance1().create(CountryInterface.class);
            Call<List<Response_data>> listCall1 = countryinterface.getName(search_content);

            listCall1.enqueue(new Callback<List<Response_data>>() {
                @Override
                public void onResponse(Call<List<Response_data>> call, Response<List<Response_data>> response) {
                    if (response.isSuccessful()) {
                        parseData1(response.body());
                    } else {
                        switch (response.code()) {
                            case 404:
                                rvmain.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "not found", Toast.LENGTH_SHORT).show();
                                break;
                            case 500:
                                rvmain.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "server broken", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                rvmain.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "unknown error", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<Response_data>> call, Throwable t) {
                    rvmain.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                }
            });
        }
        rvmain.setVisibility(View.VISIBLE);
    }
    private void parseData1(List<Response_data> body) {
        adapter1 = new SearchAdapter(getApplicationContext(), body);
        rvmain.setAdapter(adapter1);
    }
    }

