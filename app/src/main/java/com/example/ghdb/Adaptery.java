package com.example.ghdb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> implements Filterable {


    private Context mContext;
    private List<GameModelClass> mData;
    private List<GameModelClass> allData;

    public Adaptery(Context mContext, List<GameModelClass> mData) {
        this.mContext = mContext;
        this.mData = mData;
        allData = new ArrayList<>(mData);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.game_item,parent,false);

        return new MyViewHolder(v);
    }




    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.id.setText(mData.get(position).getId());
        holder.name.setText(mData.get(position).getName());

        //using glide library to display the image

        Glide.with(mContext)
                .load(mData.get(position).getImg())
                .into(holder.img);
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Bundle b = new Bundle();
               b.putSerializable("gameCards", (Serializable) mData.get(position));
                Intent i =  new Intent(mContext,Game.class);
                i.putExtras(b);
                v.getContext().startActivity(i);

           }
       });


    }

    @Override
    public int getItemCount() {

        return mData.size();

    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<GameModelClass> fliteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                fliteredList.addAll(allData);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(GameModelClass game : allData){
                    if(game.getName().toLowerCase().contains(filterPattern)){
                        fliteredList.add(game);
                    }else {
                        if(game.getGenre().toLowerCase().contains(filterPattern) || game.getGenre2().toLowerCase().contains(filterPattern)){
                            fliteredList.add(game);
                        }
                        else {
                            if(game.getId().toLowerCase().contains(filterPattern)){
                                fliteredList.add(game);
                            }
                        }
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = fliteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData.clear();
            mData.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };



    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id;
        TextView name;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id_Txt);
            name = itemView.findViewById(R.id.name_txt);
            img = itemView.findViewById(R.id.imageView);

        }
    }
}
