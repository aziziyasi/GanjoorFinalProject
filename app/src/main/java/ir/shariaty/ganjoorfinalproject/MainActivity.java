package ir.shariaty.ganjoorfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView falTv;
    private TextView bioTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        falTv.setOnClickListener(this);
        bioTv.setOnClickListener(this);

    }

    private void bindViews() {
        falTv = findViewById(R.id.txt_falhafez);
        bioTv = findViewById(R.id.txt_biography);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.txt_falhafez:
                intent = new Intent(this, FallHafezActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_biography:
                intent = new Intent(this, BiographyActivity.class);
                startActivity(intent);
                break;
        }
    }
}