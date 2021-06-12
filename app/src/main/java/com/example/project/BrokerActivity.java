package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.ui.main.RetrofitInterface;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrokerActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {

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
    private static final String EXTRA_BROKERID = "broker_id";
    private static final String EXTRA_ID="_id";

    private RecyclerView recyclerView3;
    private MyAdapter adapter3;
    private List<ListItem> listItems3;
    private RequestQueue mRequestQueue3;

    private Button addProperty;
    private Button broker_logout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broker);

        addProperty = (Button) findViewById(R.id.addproperty);
        addProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddProperty(v);
            }
        });
        broker_logout = (Button) findViewById((R.id.broker_logout));
        broker_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent blogout = new Intent(BrokerActivity.this, MainActivity_First.class);
                blogout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(blogout);
            }
        });

        recyclerView3 = (RecyclerView) findViewById(R.id.recyclerView3);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        listItems3 = new ArrayList<>();
        mRequestQueue3 = Volley.newRequestQueue(this);

        loadRecyclerViewData();



    }
    public void AddProperty(View view) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(BrokerActivity.this);
        View mview = getLayoutInflater().inflate(R.layout.add_apartment_dialog,null);

        final EditText flat_name =(EditText)mview.findViewById(R.id.flat_name);
        final EditText flat_description =(EditText)mview.findViewById(R.id.flat_description);
        final EditText flat_rent =(EditText)mview.findViewById(R.id.flat_rent);
        final EditText flat_brokerage =(EditText)mview.findViewById(R.id.flat_brokerage);
        final EditText flat_sqft =(EditText)mview.findViewById(R.id.flat_sqft);
        final EditText flat_secdep =(EditText)mview.findViewById(R.id.flat_secdep);
        final EditText flat_brokerno =(EditText)mview.findViewById(R.id.flat_brokerno);
        final EditText flat_startdate =(EditText)mview.findViewById(R.id.flat_startdate);
        final EditText flat_bhk =(EditText)mview.findViewById(R.id.flat_bhk);
        final EditText flat_availability =(EditText)mview.findViewById(R.id.flat_availability);
        final EditText flat_feature =(EditText)mview.findViewById(R.id.flat_feature);
        final EditText flat_image1 =(EditText)mview.findViewById(R.id.flat_image1);
        final EditText flat_image2 =(EditText)mview.findViewById(R.id.flat_image2);
        final EditText flat_brokerid =(EditText)mview.findViewById(R.id.flat_brokerid);
        final EditText flat_brokername =(EditText)mview.findViewById(R.id.flat_brokername);

        final Button add=(Button)mview.findViewById(R.id.addflat);
        final Button cancel=(Button)mview.findViewById(R.id.cancelflat);

        alert.setView(mview);
        final AlertDialog alertDialog=alert.create();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // url to post our data
                String url="http://10.0.2.2:3001/products/";
                //loadingPB.setVisibility(View.VISIBLE);

                // creating a new variable for our request queue
                RequestQueue queue = Volley.newRequestQueue(BrokerActivity.this);

                // on below line we are calling a string
                // request method to post the data to our API
                // in this we are calling a post method.
                StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(BrokerActivity.this,"Added Successfully", Toast.LENGTH_SHORT).show();

                        listItems3.clear();
                        alertDialog.dismiss();
                        loadRecyclerViewData();



                    }
                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // method to handle errors.
                        //Toast.makeText(BrokerActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "Communication Error!", Toast.LENGTH_SHORT).show();

                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(), "Authentication Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(), "Server Side Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(), "Parse Error!", Toast.LENGTH_SHORT).show();
                        }
                    }

                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        // below line we are creating a map for
                        // storing our values in key and value pair.
                        Map<String, String> params = new HashMap<String, String>();

                        // on below line we are passing our key
                        // and value pair to our parameters.
                        params.put("name",flat_name.getText().toString());
                        params.put("description", flat_description.getText().toString());
                        params.put("productImage", flat_image1.getText().toString());
                        params.put("productImage2", flat_image2.getText().toString());
                        params.put("broker_id", flat_brokerid.getText().toString());
                        params.put("brokerPhNo", flat_brokerno.getText().toString());
                        params.put("rent", flat_rent.getText().toString());
                        params.put("availability", flat_availability.getText().toString());
                        params.put("bhk", flat_bhk.getText().toString());
                        params.put("startdate",flat_startdate.getText().toString());
                        params.put("brokerage", flat_brokerage.getText().toString());
                        params.put("securitydep", flat_secdep.getText().toString());
                        params.put("sqft", flat_sqft.getText().toString());
                        params.put("feature", flat_feature.getText().toString());
                        params.put("broker_name", flat_brokername.getText().toString());


                        // at last we are
                        // returning our params.
                        return params;
                    }
                };
                // below line is to make
                // a json object request.
                queue.add(request);


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(true);
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
                                ListItem item3 = new ListItem(
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
                                if (item3.getBroker_id().equalsIgnoreCase(BrokerLogin.broker_id))
                                    listItems3.add(item3);
                            }
                            adapter3 = new MyAdapter(listItems3, BrokerActivity.this);
                            recyclerView3.setAdapter(adapter3);
                            adapter3.setOnItemClickListener(BrokerActivity.this);
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

        mRequestQueue3.add(stringRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent brokerIntent = new Intent(BrokerActivity.this, DetailActivity.class);
        ListItem clickedItem = listItems3.get(position);
        brokerIntent.putExtra("classFrom", BrokerActivity.class.toString());
        brokerIntent.putExtra(EXTRA_NAME, clickedItem.getName());
        brokerIntent.putExtra(EXTRA_DESC, clickedItem.getDescription());
        brokerIntent.putExtra(EXTRA_BHK, clickedItem.getBhk());
        brokerIntent.putExtra(EXTRA_SF, clickedItem.getSqft());
        brokerIntent.putExtra(EXTRA_BROKERAGE, clickedItem.getBrokerage());
        brokerIntent.putExtra(EXTRA_BROKERNO, clickedItem.getBroker_no());
        brokerIntent.putExtra(EXTRA_AVAILABILITY, clickedItem.getAvailable());
        brokerIntent.putExtra(EXTRA_RENT, clickedItem.getRent());
        brokerIntent.putExtra(EXTRA_SECURITYD, clickedItem.getSecurityd());
        brokerIntent.putExtra(EXTRA_SDATE, clickedItem.getSdate());
        brokerIntent.putExtra(EXTRA_BROKERNAME, clickedItem.getBrokername());
        brokerIntent.putExtra(EXTRA_FEATURE, clickedItem.getFeature());
        brokerIntent.putExtra(EXTRA_IMAGE, clickedItem.getImageUrl());
        brokerIntent.putExtra(EXTRA_IMAGE2, clickedItem.getImageUrl2());


        startActivity(brokerIntent);

    }


}
