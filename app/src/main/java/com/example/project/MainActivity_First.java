package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity_First extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__first);
        Button customer=(Button)findViewById(R.id.button3);
        customer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(MainActivity_First.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}