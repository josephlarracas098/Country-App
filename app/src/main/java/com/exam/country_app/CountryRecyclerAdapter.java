package com.exam.country_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class CountryRecyclerAdapter extends RecyclerView.Adapter<CountryRecyclerAdapter.ViewHolder> {
    Country[] countries;
    Context context;

    @NonNull
    @Override
    public CountryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.details_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public CountryRecyclerAdapter(Context context, Country[] countries) {
        this.countries = countries;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryRecyclerAdapter.ViewHolder holder, int position) {
        holder.name.setText(countries[position].getName());

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView flag;
        TextView name;
        TextView capital;
        TextView region;
        TextView abbreviation;
        TextView calling_codes;
        TextView population;
        TextView currencies;
        TextView longitude;
        TextView latitude;
        TextView languages;
        TextView borders;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.flag);
            name = itemView.findViewById(R.id.country_name);
            capital = itemView.findViewById(R.id.capital);
            region = itemView.findViewById(R.id.region);
            abbreviation = itemView.findViewById(R.id.abbreviation);
            calling_codes = itemView.findViewById(R.id.calling_codes);
            population = itemView.findViewById(R.id.population);
            currencies = itemView.findViewById(R.id.currencies);
            longitude = itemView.findViewById(R.id.longitude);
            latitude = itemView.findViewById(R.id.latitude);
            languages = itemView.findViewById(R.id.languages);
            borders = itemView.findViewById(R.id.borders);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
