package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.ui.main.RetrofitInterface;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity3 extends AppCompatActivity {

    private Retrofit retrofit2;
    private RetrofitInterface retrofitInterface2;
    private String URL="http://10.0.2.2:3001/user/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        retrofit2 = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface2 = retrofit2.create(RetrofitInterface.class);

        EditText email2 = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        EditText password2 = (EditText) findViewById(R.id.editTextTextPassword2);
        Button signup = (Button) findViewById(R.id.button5);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                map.put("email", email2.getText().toString());
                map.put("password", password2.getText().toString());

                Call<JSONObject> call = retrofitInterface2.executeSignup(map);

                call.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                        //Toast.makeText(MainActivity3.this,response.message(), Toast.LENGTH_LONG).show();
                        if (response.message().equalsIgnoreCase("Created")) {
                            Toast.makeText(MainActivity3.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                            Intent intentout = new Intent(MainActivity3.this, MainActivity2.class);
                            startActivity(intentout);
                            finish();
                        } else if (response.message().equalsIgnoreCase("Conflict")) {
                            Toast.makeText(MainActivity3.this, "Email Exists", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity3.this, "Invalid", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {
                        Toast.makeText(MainActivity3.this, t.getMessage(), Toast.LENGTH_LONG).show();

                    }


                });
            }
        });

    }
}