package com.example.homework2;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    private List<Beer> beers;

    // pass this list into the constructor of the adapter
    public BeerAdapter(List<Beer> beers) {
        this.beers = beers;
    }

    private List<Beer> favoriteBeers = new ArrayList<>();;

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
                launchNextActivity(v, position);
            }
        });

        Picasso.get().load("file:///android_asset/star_empty.png").into(holder.imageView_favorite);

        /*
        // add onset click listener
        holder.imageView_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (beer.getFavorite() == true) {
                    beer.setFavorite(false);
                    Picasso.get().load("file:///android_asset/star_empty.png").into(holder.imageView_favorite);
                }
                else {
                    beer.setFavorite(true);
                    Picasso.get().load("file:///android_asset/star_filled.png").into(holder.imageView_favorite);
                }
            }
        });

         */

        if (favoriteBeers.contains(beer)) {
            Picasso.get().load("file:///android_asset/star_filled.png").into(holder.imageView_favorite);
        }
        else {
            Picasso.get().load("file:///android_asset/star_empty.png").into(holder.imageView_favorite);
        }
    }

    public void launchNextActivity(View view, int position) {
        Intent intent = new Intent(view.getContext(), FourthActivity.class);

        Beer beer = beers.get(position);
        intent.putExtra("name", beer.getName());
        intent.putExtra("imageUrl", beer.getImageUrl());
        intent.putExtra("abv", beer.getAbv());
        intent.putExtra("firstBrewed", beer.getFirstBrewed());
        intent.putExtra("description", beer.getDescription());
        intent.putExtra("foodPairings", beer.getFoodPairings());
        intent.putExtra("tips", beer.getTips());

        view.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView_name;
        TextView textView_description;
        ImageView imageView_beer;
        ImageView imageView_favorite;

        public ViewHolder(View itemView) {
            super(itemView);
            // look up views by id
            textView_name = itemView.findViewById(R.id.textView_item_name);
            textView_description = itemView.findViewById(R.id.textView_item_description);
            imageView_beer = itemView.findViewById(R.id.imageView_item_beer);
            imageView_favorite = itemView.findViewById(R.id.imageView_favorite);
            // set on click listener
            imageView_favorite.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // record the position of this row
            // grab the beer at the position
            // add it to the favorite ArrayList
            int selected = getAdapterPosition();
            Beer clicked = beers.get(selected);
            // if it already contains, remove it from the list
            // empty star image
            if (favoriteBeers.contains(clicked)) {
                clicked.setFavorite(false);
                favoriteBeers.remove(clicked);
            }
            else {
                clicked.setFavorite(true);
                favoriteBeers.add(clicked);
            }
            notifyDataSetChanged(); // you have a list of items instead of one
        }
    }
}
