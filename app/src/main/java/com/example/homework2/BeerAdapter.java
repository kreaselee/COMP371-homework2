package com.example.homework2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    private List<Beer> beers;

    // pass this list into the constructor of the adapter
    public BeerAdapter(List<Beer> beerList) {
        this.beers = beers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // inflate the custom layout
        View beerView = inflater.inflate(R.layout.item_beer, parent, false);
        // return a new ViewHolder
        ViewHolder viewHolder = new ViewHolder(beerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // grab the data model based on the position
        Beer beer = beers.get(position);
        // set the view based on the data and the view names
        holder.textView_name.setText(beer.getName());
        holder.textView_description.setText(beer.getDescription());
        Picasso.get().load(beer.getImageUrl()).into(holder.imageView_beer);
        // add onset click listener
        holder.imageView_beer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // launch next activity
            }
        });
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_name;
        TextView textView_description;
        ImageView imageView_beer;

        public ViewHolder(View itemView) {
            super(itemView);
            // look up views by id
            textView_name = itemView.findViewById(R.id.textView_name);
            textView_description = itemView.findViewById(R.id.textView_description);
            imageView_beer = itemView.findViewById(R.id.imageView_beer);
            // set on click listener
            // imageView_beer.setOnClickListener(this);
        }

    }
}
