package com.example.ghibliapi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {

    private TextView textFact;
    private Button randomFact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setListeners();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CatService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CatService catService = retrofit.create(CatService.class);

        Call<CatFact> catCall = catService.getFacts("cat", 1);

        catCall.enqueue(new Callback<CatFact>() {
            @Override
            public void onResponse(Call<CatFact> call, Response<CatFact> response) {
                CatFact foundCatFact = response.body();
                Log.d("CatFact", "onResponse: " + foundCatFact);
                if(foundCatFact != null) {
                    textFact.setText(foundCatFact.getText());
                }
            }

            @Override
            public void onFailure(Call<CatFact> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setListeners() {
        randomFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(CatService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                CatService catService = retrofit.create(CatService.class);

                Call<CatFact> catCall = catService.getFacts("cat", 1);

                catCall.enqueue(new Callback<CatFact>() {
                    @Override
                    public void onResponse(Call<CatFact> call, Response<CatFact> response) {
                        CatFact foundCatFact = response.body();
                        Log.d("CatFact", "onResponse: " + foundCatFact);
                        if(foundCatFact != null) {
                            textFact.setText(foundCatFact.getText());
                        }
                    }

                    @Override
                    public void onFailure(Call<CatFact> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    private void wireWidgets() {
        textFact = findViewById(R.id.textView_main_catfact);
        randomFact = findViewById(R.id.button_main_random);

    }

}
