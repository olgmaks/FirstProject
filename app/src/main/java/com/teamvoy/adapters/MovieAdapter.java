package com.teamvoy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamvoy.activities.R;
import com.teamvoy.bean.Movie;
import com.teamvoy.services.MovieService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OLEG on 06.02.2015.
 */
public class MovieAdapter extends BaseAdapter implements Filterable {

    private List<Movie> list;
    private LayoutInflater layoutInflater;

    public MovieAdapter(Context context, List<Movie> list) {
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public MovieAdapter setAdapterDataList(List<Movie> list) {
        this.list = list;
        return this;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_layout, parent, false);
        }
        Movie movie = getMovie(position);
        TextView itemTitle = (TextView) view.findViewById(R.id.item_title);
        TextView itemDate = (TextView) view.findViewById(R.id.item_date);
        ImageView itemImage = (ImageView) view.findViewById(R.id.item_image);
        TextView itemRate = (TextView) view.findViewById(R.id.item_rate);
        TextView itemDescription = (TextView) view.findViewById(R.id.item_description);
        TextView itemLikes = (TextView) view.findViewById(R.id.item_likes);
        itemTitle.setText(movie.getTitle());
        itemDate.setText(movie.getDate());
        itemRate.setText(String.valueOf("Rate : " + movie.getRate()));
        itemDescription.setText(movie.getDescription());
        try {
            itemImage.setImageResource(movie.getImageId());
        } catch (Exception e) {
        }

        itemLikes.setText("Reviews : " + movie.getPopularity());
        return view;
    }

    public Movie getMovie(int position) {
        return (Movie) getItem(position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                list = MovieService.serve().getCurrentMovieList();
                FilterResults results = new FilterResults();
                if (constraint.length() == 0) {
                    results.values = list;
                    results.count = list.size();
                    return results;
                }

                ArrayList<Movie> filteredResults = new ArrayList<>();
                String filterString = constraint.toString().toLowerCase();
                String filterableString;

                for (int i = 0; i < list.size(); i++) {
                    filterableString = list.get(i).getTitle();
                    if (filterableString.toLowerCase().contains(filterString)) {
                        try {
                            filteredResults.addAll(MovieService.serve().getMoviesByTitle
                                    (filterableString));
                        } catch (Exception e) {
                            System.out.println(e + "filter exception");
                        }
                    }
                }
                results.values = filteredResults;
                results.count = filteredResults.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List<Movie>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
