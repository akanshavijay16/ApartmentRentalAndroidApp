package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity implements MyAdapter.OnItemClickListener {
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_DESC = "description";
    public static final String EXTRA_IMAGE = "productImage";
    public static final String EXTRA_BHK = "bhk";
    public static final String EXTRA_SF = "sqft";
    public static final String EXTRA_BROKERAGE = "brokerage";
    public static final String EXTRA_AVAILABILITY = "availability";
    public static final String EXTRA_RENT = "rent";
    public static final String EXTRA_SDATE = "startdate";
    public static final String EXTRA_SECURITYD = "securitydep";
    public static final String EXTRA_BROKERNO = "brokerPhNo";
    public static final String EXTRA_IMAGE2 = "productImage2";
    public static final String EXTRA_BROKERNAME = "broker_name";
    public static final String EXTRA_FEATURE = "feature";
    public static final String EXTRA_ID = "_id";




    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<ListItem> listItems;
    private RequestQueue mRequestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);




        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems=new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        loadRecyclerViewData();
    }
    private void loadRecyclerViewData(){
        String DATA="http://10.0.2.2:3001/products/";

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, DATA,
                new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray array= jsonObject.getJSONArray("products");

                        for(int i=0;i<array.length();i++)
                        {
                            JSONObject o=array.getJSONObject(i);
                            ListItem item=new ListItem(
                                    o.getString("name"),
                                    o.getString("description"),
                                    o.getString("productImage"),
                                    o.getString("_id"),
                                    o.getString("productImage2"),
                                    o.getString("broker_id"),
                                    o.getString("brokerPhNo"),
                                    o.getString("rent"),
                                    o.getString("availability"),
                                    o.getString("bhk"),
                                    o.getString("startdate"),
                                    o.getString("brokerage"),
                                    o.getString("securitydep"),
                                    o.getString("sqft"),
                                    o.getString("feature"),
                                    o.getString("broker_name")

                            );
                            listItems.add(item);
                        }
                        adapter = new MyAdapter(listItems,MainActivity4.this);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(MainActivity4.this);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        mRequestQueue.add(stringRequest);
    }

    @Override
    public void onItemClick(int position) {
      Intent detailIntent = new Intent(MainActivity4.this,DetailActivity.class);
      ListItem clickedItem = listItems.get(position);

      detailIntent.putExtra(EXTRA_NAME,clickedItem.getName());
      detailIntent.putExtra(EXTRA_DESC,clickedItem.getDescription());
      detailIntent.putExtra(EXTRA_BHK,clickedItem.getBhk());
      detailIntent.putExtra(EXTRA_SF,clickedItem.getSqft());
      detailIntent.putExtra(EXTRA_BROKERAGE,clickedItem.getBrokerage());
      detailIntent.putExtra(EXTRA_BROKERNO,clickedItem.getBroker_no());
      detailIntent.putExtra(EXTRA_AVAILABILITY,clickedItem.getAvailable());
      detailIntent.putExtra(EXTRA_RENT,clickedItem.getRent());
      detailIntent.putExtra(EXTRA_SECURITYD,clickedItem.getSecurityd());
      detailIntent.putExtra(EXTRA_SDATE,clickedItem.getSdate());
      detailIntent.putExtra(EXTRA_BROKERNAME,clickedItem.getBrokername());
      detailIntent.putExtra(EXTRA_FEATURE,clickedItem.getFeature());
      detailIntent.putExtra(EXTRA_IMAGE,clickedItem.getImageUrl());
      detailIntent.putExtra(EXTRA_IMAGE2,clickedItem.getImageUrl2());




        startActivity(detailIntent);

    }
}
