package com.example.project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<ListItem> listItems;
    private Context context;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context)
                .inflate(R.layout.list_item,parent,false);
        ImageButton delete=v.findViewById(R.id.buttonDelete);
        if(this.context instanceof BrokerActivity){
            delete.setVisibility(View.VISIBLE);
        }
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem=listItems.get(position);
            holder.textViewName.setText(listItem.getName());
            holder.textViewBHK.setText(listItem.getBhk() + " BHK");
            holder.textViewAvailable.setText(listItem.getAvailable());
            holder.textViewRent.setText("₹" + listItem.getRent());
            holder.textViewDesc.setText(listItem.getDescription());

            if (listItem.getAvailable().equalsIgnoreCase("true"))
                holder.textViewAvailable.setText("AVAILABLE");
            else
                holder.textViewAvailable.setText("");
            if (listItem.getFeature().equalsIgnoreCase("true"))
                holder.textViewfeature.setText("FEATURED");

            Picasso.with(context)
                    .load(listItem.getImageUrl())
                    .fit()
                    .centerInside()
                    .placeholder(R.drawable.loading)
                    .into(holder.imageView);
            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context,"DELETE : "+position,Toast.LENGTH_LONG).show();
                    String url="http://10.0.2.2:3001/products/"+listItem.get_id();
                    AlertDialog.Builder alertDialogBuilder = new
                            AlertDialog.Builder(
                            context);
                    alertDialogBuilder.setTitle("Delete selected property");

                    alertDialogBuilder
                            .setMessage("Click yes to delete!")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new
                                    DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            RequestQueue queue = Volley.newRequestQueue(context);

                                            StringRequest request = new StringRequest(Request.Method.DELETE, url, new com.android.volley.Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {

                                                    Toast.makeText(context,"Property Deleted", Toast.LENGTH_SHORT).show();
                                                    listItems.remove(position);
                                                    notifyDataSetChanged();


                                                }
                                            }, new com.android.volley.Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    // method to handle errors.
                                                    //Toast.makeText(BrokerActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                                                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                                        Toast.makeText(context, "Communication Error!", Toast.LENGTH_SHORT).show();

                                                    } else if (error instanceof AuthFailureError) {
                                                        Toast.makeText(context, "Authentication Error!", Toast.LENGTH_SHORT).show();
                                                    } else if (error instanceof ServerError) {
                                                        Toast.makeText(context, "Server Side Error!", Toast.LENGTH_SHORT).show();
                                                    } else if (error instanceof NetworkError) {
                                                        Toast.makeText(context, "Network Error!", Toast.LENGTH_SHORT).show();
                                                    } else if (error instanceof ParseError) {
                                                        Toast.makeText(context, "Parse Error!", Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                            });
                                            queue.add(request);
                                        }

                                        }
                                    )
                            .setNegativeButton("No", new
                                    DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            dialog.cancel();
                                        }
                                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();

                    alertDialog.show();
                    alertDialog.getWindow().setBackgroundDrawableResource(R.color.darkpurple);
                }



                });
            }




    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public TextView textViewDesc;
        public TextView textViewBHK;
        public TextView textViewAvailable;
        public TextView textViewSF;
        public TextView textViewRent;
        public ImageView imageView;
        public TextView textViewfeature;
        public ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = (TextView)itemView.findViewById(R.id.textViewName);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            textViewAvailable=(TextView) itemView.findViewById(R.id.textViewAvailable);
            textViewRent=(TextView) itemView.findViewById(R.id.textViewRent);
            textViewBHK=(TextView) itemView.findViewById(R.id.textViewBHK);
            textViewDesc =(TextView)itemView.findViewById(R.id.textViewDesc);
            textViewfeature=(TextView)itemView.findViewById(R.id.textViewfeature);
            deleteButton=(ImageButton)itemView.findViewById(R.id.buttonDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }
}
