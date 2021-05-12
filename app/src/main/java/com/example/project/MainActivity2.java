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


public class MainActivity2 extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String URL="http://10.0.2.2:3001/user/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        EditText email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        EditText password = (EditText) findViewById(R.id.editTextTextPassword);

        Button login = (Button) findViewById(R.id.button);
        Button signin = (Button) findViewById(R.id.button4);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> map=new HashMap<>();
                map.put("email",email.getText().toString());
                map.put("password",password.getText().toString());

                Call<JSONObject> call=retrofitInterface.executeLogin(map);

                call.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                        //Toast.makeText(MainActivity2.this,response.message(), Toast.LENGTH_LONG).show();
                        if(response.message().equalsIgnoreCase("OK"))
                        {
                           Toast.makeText(MainActivity2.this,"Login Successful", Toast.LENGTH_LONG).show();
                            Intent intentin = new Intent(MainActivity2.this, MainActivity.class);
                            startActivity(intentin);
                            finish();
                        }
                        else if(response.message().equalsIgnoreCase("Unauthorized"))
                        {
                            Toast.makeText(MainActivity2.this,"Wrong Credentials",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {
                        Toast.makeText(MainActivity2.this,t.getMessage(), Toast.LENGTH_LONG).show();

                    }


                });
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentsignup = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intentsignup);

            }
        });
    }
}



