package com.example.dog_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageViewId);
        button = findViewById(R.id.buttonId);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonId) {

            //RETROFIT er kaaj
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Dog_Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Dog_Api dog_api = retrofit.create(Dog_Api.class);

            Call<DogClass> call = dog_api.getDogImage();

            call.enqueue(new Callback<DogClass>() {
                @Override
                public void onResponse(Call<DogClass> call, Response<DogClass> response) {

                    DogClass dog = response.body();

                    Log.d("message",dog.getMessage());
                    Log.d("status",dog.getStatus());
                    //button.setText(dog.getMessage());
                    Picasso.with(MainActivity.this).load(dog.getMessage()).into(imageView);
                }

                @Override
                public void onFailure(Call<DogClass> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            //RETROFIT END

        }


    }

}