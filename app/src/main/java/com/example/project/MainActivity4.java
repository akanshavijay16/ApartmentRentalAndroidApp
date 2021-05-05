package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
                                    o.getString("productImage")
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

      detailIntent.putExtra(EXTRA_NAME,clickedItem.getHead());
      detailIntent.putExtra(EXTRA_DESC,clickedItem.getDesc());

      startActivity(detailIntent);

    }
}
