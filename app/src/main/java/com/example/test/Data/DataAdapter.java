package com.example.test.Data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.test.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {


    final private OnListItemClickListener onListItemClickListener;
    private ArrayList<Country> countries;


    public DataAdapter(ArrayList<Country> countries, OnListItemClickListener listener) {
        this.countries = countries;
        onListItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.country_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(countries.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void filterList(ArrayList<Country> filteredList) {
        countries = filteredList;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        ImageView icon;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }


    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Country> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(countries);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Country country : countries) {
                    if (country.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(country);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            countries.clear();
            countries.addAll((ArrayList) results.values);
            notify();
        }
    };

    public ArrayList<Country> getCountries() {
        return countries;
    }

}

