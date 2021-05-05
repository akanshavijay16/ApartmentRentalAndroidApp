package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.project.MainActivity4.EXTRA_DESC;
import static com.example.project.MainActivity4.EXTRA_NAME;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent i = getIntent();

        TextView textTitle =(TextView) findViewById(R.id.textView_head);
        TextView textRent =(TextView) findViewById(R.id.textView_rent);

        String heading= i.getStringExtra(EXTRA_NAME);
        String description=i.getStringExtra(EXTRA_DESC);
        textTitle.setText(heading);
        textRent.setText(description);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }
}