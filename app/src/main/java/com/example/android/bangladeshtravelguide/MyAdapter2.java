package com.example.android.bangladeshtravelguide;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<Room>detail;
    private OnItemClickListener mListener;

    MyAdapter2(Context context, ArrayList<Room>detail) {
        this.context = context;
        this.detail= detail;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View row = layoutInflater.inflate(R.layout.custom_row, viewGroup, false);
        Item item = new Item(row,mListener);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        final Room temp = detail.get(i);
        ((Item) viewHolder).room.setText("Room Name :"+temp.getRn());
        ((Item) viewHolder).details.setText(temp.getRd());
        ((Item) viewHolder).price.setText( "Price : Tk."+String.valueOf(temp.getP()));

    }

    @Override
    public int getItemCount() {
        return detail.size();
    }

    public interface OnItemClickListener{
        void onItemClickListener(int pos);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener=listener;
    }
    public static class Item extends RecyclerView.ViewHolder {
        TextView room,details,price;

        public Item(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            room=itemView.findViewById(R.id.room);
            details=itemView.findViewById(R.id.details);
            price=itemView.findViewById(R.id.price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int pos=getAdapterPosition();
                        if(pos!= RecyclerView.NO_POSITION);
                        {
                            listener.onItemClickListener(pos);
                        }
                    }
                }
            });


        }
    }
}

