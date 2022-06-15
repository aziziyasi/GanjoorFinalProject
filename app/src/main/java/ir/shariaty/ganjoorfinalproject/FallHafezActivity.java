package ir.shariaty.ganjoorfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ir.shariaty.ganjoorfinalproject.api.RetrofitApiService;
import ir.shariaty.ganjoorfinalproject.models.Bit;
import ir.shariaty.ganjoorfinalproject.models.Faal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FallHafezActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView qazalNumber;
    private TextView qazal;
    private TextView tabir;
    private ImageView backImageView ;
    private ImageView refreshImageView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fallhafez);

        bindViews();

        backImageView.setOnClickListener(this);
        refreshImageView.setOnClickListener(this);

        getFaal();

    }


    private void getFaal() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ganjgah.ir/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApiService apiService = retrofit.create(RetrofitApiService.class);
        apiService.getFaal().enqueue(new Callback<Faal>() {
            @Override
            public void onResponse(Call<Faal> call, Response<Faal> response) {
                Faal faal = response.body();

                String poem = processVerses(faal.getVerses());

                qazalNumber.setText(faal.getTitle());
                qazal.setText(poem);

            }

            @Override
            public void onFailure(Call<Faal> call, Throwable t) {

            }
        });
    }

    private String processVerses(Bit[] bits) {
        StringBuilder sb = new StringBuilder();

        for (Bit bit : bits) {
            sb.append(bit.getText());
            sb.append("\n\n");
        }

        return sb.toString();
    }

    private void bindViews() {
        qazalNumber = findViewById(R.id.txt_shomareqazal);
        qazal = findViewById(R.id.txt_qazal);
        tabir = findViewById(R.id.txt_tabir);
        backImageView = findViewById(R.id.img_back);
        refreshImageView = findViewById(R.id.img_refresh);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_refresh:
                getFaal();
                break;
        }
    }
}