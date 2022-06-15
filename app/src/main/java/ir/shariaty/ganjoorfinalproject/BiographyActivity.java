package ir.shariaty.ganjoorfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import ir.shariaty.ganjoorfinalproject.adapter.BioAdapter;
import ir.shariaty.ganjoorfinalproject.api.RetrofitApiService;
import ir.shariaty.ganjoorfinalproject.models.Biography;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BiographyActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private BioAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biography);

        ImageView backImageView = findViewById(R.id.img_back);
        backImageView.setOnClickListener(this);
        getBiography();
    }

    private void getBiography() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ganjgah.ir/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApiService apiService = retrofit.create(RetrofitApiService.class);
        apiService.getBiographies().enqueue(new Callback<List<Biography>>() {
            @Override
            public void onResponse(Call<List<Biography>> call, Response<List<Biography>> response) {
                List<Biography> biographyList = response.body();

                recyclerView = findViewById(R.id.recycler_biography);
                recyclerView.setLayoutManager(new LinearLayoutManager(BiographyActivity.this, RecyclerView.VERTICAL, false));
                adapter = new BioAdapter(biographyList, BiographyActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Biography>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_back) {
            finish();
        }
    }
}