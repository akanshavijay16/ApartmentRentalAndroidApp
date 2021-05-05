package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button login=(Button)findViewById(R.id.button);
        Button signup=(Button)findViewById(R.id.button4);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v)
            {
                Intent intent1 = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent1);
            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v)
            {
                Intent intent2 = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent2);
            }
        });




    }

}
