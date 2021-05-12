package com.example.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityFeatured extends AppCompatActivity implements MyAdapter.OnItemClickListener {
    private static final String EXTRA_NAME = "name";
    private static final String EXTRA_DESC = "description";
    private static final String EXTRA_IMAGE = "productImage";
    private static final String EXTRA_BHK = "bhk";
    private static final String EXTRA_SF = "sqft";
    private static final String EXTRA_BROKERAGE = "brokerage";
    private static final String EXTRA_AVAILABILITY = "availability";
    private static final String EXTRA_RENT = "rent";
    private static final String EXTRA_SDATE = "startdate";
    private static final String EXTRA_SECURITYD = "securitydep";
    private static final String EXTRA_BROKERNO = "brokerPhNo";
    private static final String EXTRA_IMAGE2 = "productImage2";
    private static final String EXTRA_BROKERNAME = "broker_name";
    private static final String EXTRA_FEATURE = "feature";
    private static final String EXTRA_ID = "_id";


    private RecyclerView recyclerView2;
    private MyAdapter adapter2;
    private List<ListItem> listItems2;
    private RequestQueue mRequestQueue2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured);


        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        listItems2 = new ArrayList<>();
        mRequestQueue2 = Volley.newRequestQueue(this);
        loadRecyclerViewData();
    }

    private void loadRecyclerViewData() {
        String DATA = "http://10.0.2.2:3001/products/";

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
                            JSONArray array = jsonObject.getJSONArray("products");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                ListItem item2 = new ListItem(
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
                                if(item2.getFeature()=="true")
                                listItems2.add(item2);
                            }
                            adapter2 = new MyAdapter(listItems2, ActivityFeatured.this);
                            recyclerView2.setAdapter(adapter2);
                            adapter2.setOnItemClickListener(ActivityFeatured.this);
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

        mRequestQueue2.add(stringRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent featureIntent = new Intent(ActivityFeatured.this, DetailActivity.class);
        ListItem clickedItem = listItems2.get(position);

        featureIntent.putExtra(EXTRA_NAME, clickedItem.getName());
        featureIntent.putExtra(EXTRA_DESC, clickedItem.getDescription());
        featureIntent.putExtra(EXTRA_BHK, clickedItem.getBhk());
        featureIntent.putExtra(EXTRA_SF, clickedItem.getSqft());
        featureIntent.putExtra(EXTRA_BROKERAGE, clickedItem.getBrokerage());
        featureIntent.putExtra(EXTRA_BROKERNO, clickedItem.getBroker_no());
        featureIntent.putExtra(EXTRA_AVAILABILITY, clickedItem.getAvailable());
        featureIntent.putExtra(EXTRA_RENT, clickedItem.getRent());
        featureIntent.putExtra(EXTRA_SECURITYD, clickedItem.getSecurityd());
        featureIntent.putExtra(EXTRA_SDATE, clickedItem.getSdate());
        featureIntent.putExtra(EXTRA_BROKERNAME, clickedItem.getBrokername());
        featureIntent.putExtra(EXTRA_FEATURE, clickedItem.getFeature());
        featureIntent.putExtra(EXTRA_IMAGE, clickedItem.getImageUrl());
        featureIntent.putExtra(EXTRA_IMAGE2, clickedItem.getImageUrl2());



        startActivity(featureIntent);

    }
}