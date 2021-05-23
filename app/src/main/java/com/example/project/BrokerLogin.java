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

public class BrokerLogin extends AppCompatActivity {
    public static String broker_id;
    private Retrofit retrofit3;
    private RetrofitInterface retrofitInterface3;
    private String URL="http://10.0.2.2:3001/broker/";

    private static final String EXTRA_BROKERID = "broker_id";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broker_login);


        retrofit3 = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface3 = retrofit3.create(RetrofitInterface.class);

        EditText bemail = (EditText) findViewById(R.id.brokeremail);
        EditText bpassword = (EditText) findViewById(R.id.brokerpassword);
        EditText bid=(EditText)findViewById(R.id.brokerid);





        Button blogin = (Button) findViewById(R.id.brokerlogin);

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                broker_id=bid.getText().toString();
                HashMap<String,String> map=new HashMap<>();
                map.put("email",bemail.getText().toString());
                map.put("password",bpassword.getText().toString());

                Call<JSONObject> call=retrofitInterface3.executeBlogin(map);

                call.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {

                        if(response.message().equalsIgnoreCase("OK"))
                        {
                            Toast.makeText(BrokerLogin.this,"Login Successful", Toast.LENGTH_LONG).show();
                            Intent intentbrokeractivity= new Intent(BrokerLogin.this, BrokerActivity.class);
                            startActivity(intentbrokeractivity);
                            finish();
                        }
                        else if(response.message().equalsIgnoreCase("Unauthorized"))
                        {
                            Toast.makeText(BrokerLogin.this,"Invalid Credentials",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {
                        Toast.makeText(BrokerLogin.this,t.getMessage(), Toast.LENGTH_LONG).show();

                    }


                });
            }
        });

    }
}