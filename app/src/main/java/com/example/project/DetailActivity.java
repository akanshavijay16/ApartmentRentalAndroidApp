package com.example.project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.project.MainActivity4.EXTRA_DESC;
import static com.example.project.MainActivity4.EXTRA_NAME;
import static com.example.project.MainActivity4.EXTRA_IMAGE;
import static com.example.project.MainActivity4.EXTRA_BHK;
import static com.example.project.MainActivity4.EXTRA_RENT;
import static com.example.project.MainActivity4.EXTRA_SDATE;
import static com.example.project.MainActivity4.EXTRA_SECURITYD;
import static com.example.project.MainActivity4.EXTRA_IMAGE2;
import static com.example.project.MainActivity4.EXTRA_BROKERAGE;
import static com.example.project.MainActivity4.EXTRA_SF;
import static com.example.project.MainActivity4.EXTRA_SDATE;
import static com.example.project.MainActivity4.EXTRA_BROKERNAME;
import static com.example.project.MainActivity4.EXTRA_BROKERNO;



public class DetailActivity extends AppCompatActivity {
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent i = getIntent();

        TextView Name =(TextView) findViewById(R.id.textView_name);
        TextView Rent =(TextView) findViewById(R.id.textView_rent);
        TextView Description =(TextView) findViewById(R.id.textView_desc);
        TextView BHK =(TextView) findViewById(R.id.textView_bhk);
        TextView Security =(TextView) findViewById(R.id.textView_security);
        TextView Brokerage =(TextView) findViewById(R.id.textView_brokerage);
        TextView SqFt =(TextView) findViewById(R.id.textView_sqft);
        TextView Sdate =(TextView) findViewById(R.id.textView_sdate);
        ImageView Image1 =(ImageView) findViewById(R.id.imageViewURL);
        ImageView Image2 =(ImageView) findViewById(R.id.imageViewURL2);


        StyleSpan bold=new StyleSpan(Typeface.BOLD);

        SpannableString rent=new SpannableString(i.getStringExtra(EXTRA_RENT));
        rent.setSpan(bold,0,rent.length()-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString bhk=new SpannableString(i.getStringExtra(EXTRA_BHK));
        bhk.setSpan(bold,0,bhk.length()-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        SpannableString secd=new SpannableString(i.getStringExtra(EXTRA_SECURITYD));
        secd.setSpan(bold,0,secd.length()-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString broke=new SpannableString(i.getStringExtra(EXTRA_BROKERAGE));
        broke.setSpan(bold,0,broke.length()-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString sf=new SpannableString(i.getStringExtra(EXTRA_SF));
        sf.setSpan(bold,0,sf.length()-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString sd=new SpannableString(i.getStringExtra(EXTRA_SDATE));
        sd.setSpan(bold,0,sd.length()-1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        Name.setText(i.getStringExtra(EXTRA_NAME));
        Rent.setText("\u2022 RENT : ₹"+rent+" pm");
        BHK.setText("\u2022 BHK : "+bhk);
        Security.setText("\u2022 SECURITY DEPOSIT : ₹"+secd);
        Brokerage.setText("\u2022 BROKERAGE : ₹"+broke);
        SqFt.setText("\u2022 SQ.FT : "+sf);
        Sdate.setText("\u2022 START DATE : "+sd);
        Description.setText(i.getStringExtra(EXTRA_DESC));

        Picasso.with(DetailActivity.this)
                .load(i.getStringExtra(EXTRA_IMAGE))
                .fit()
                .centerInside()
                .placeholder(R.drawable.loading)
                .into(Image1);

        Picasso.with(DetailActivity.this)
                .load(i.getStringExtra(EXTRA_IMAGE2))
                .fit()
                .centerInside()
                .placeholder(R.drawable.loading)
                .into(Image2);

        Button agent = (Button)findViewById(R.id.agent);

        agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                AlertDialog.Builder alertDialogBuilder = new
                        AlertDialog.Builder(
                        context);
                alertDialogBuilder.setTitle("AGENT DETAILS");

                alertDialogBuilder
                        .setMessage("Name : "+i.getStringExtra(EXTRA_BROKERNAME)+"\n"+"Contact : "+i.getStringExtra(EXTRA_BROKERNO))
                        .setCancelable(false)
                        .setPositiveButton("OK", new
                                DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });


                AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.show();
                alertDialog.getWindow().setBackgroundDrawableResource(R.color.blue);
            }
        });

    }
}