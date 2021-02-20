package com.example.homework2;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    private List<Beer> beerList;

    // pass this list into the constructor of the adapter
    public BeerAdapter(List<Beer> beerList) {
        this.beerList = beerList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        // FILL OUT ALL OF THIS 
    }
}
