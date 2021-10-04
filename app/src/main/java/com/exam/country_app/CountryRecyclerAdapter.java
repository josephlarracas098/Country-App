package com.exam.country_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;

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
        new DownloadImageTask((ImageView) holder.flag).execute(countries[position].getFlag());
        holder.capital.setText(Html.fromHtml("<b>Capital:</b> " + countries[position].getCapital()));
        holder.region.setText(Html.fromHtml("<b>Region:</b> " + countries[position].getRegion()));
        holder.abbreviation.setText(Html.fromHtml("<b>Abbreviation:</b> " + countries[position].getAbbreviation()));
        holder.calling_codes.setText(Html.fromHtml("<b>Calling Codes:</b> " + countries[position].getCalling_codes().toString()));
        holder.population.setText(Html.fromHtml("<b>Population:</b> " + countries[position].getPopulation()));

        StringBuilder currencyString = new StringBuilder();
        for (int j = 0; j < countries[position].getCurrency().length; j++){
            currencyString.append(countries[position].getCurrency()[j].getCode() + "-" + countries[position].getCurrency()[j].getName() + "-" + countries[position].getCurrency()[j].getSymbol() + " ");
        }
        holder.currencies.setText(Html.fromHtml("<b>Currency:</b> <small>" + currencyString + " " + "" + " " + "" + "</small>"));

        holder.latitude.setText(Html.fromHtml("<b>Latitude:</b> <small>" + countries[position].getLatlng()[0]));
        holder.longitude.setText(Html.fromHtml("<b>Longitude:</b> <small>" + countries[position].getLatlng()[1]));

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
        return countries.length;
    }

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
