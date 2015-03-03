package com.teamvoy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.teamvoy.adapters.MovieAdapter;
import com.teamvoy.bean.Movie;
import com.teamvoy.dbhelper.HelperFactory;
import com.teamvoy.repository.MovieRepository;
import com.teamvoy.services.MovieService;

import java.sql.SQLException;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private List<Movie> movies;
    private SearchView searchView;
    private MovieAdapter movieAdapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HelperFactory.setHelper(getApplicationContext());
        MovieRepository.fillDB();

        movies = MovieService.serve().getAllMovies();
        movieAdapter = new MovieAdapter(this, movies);
        searchView = (SearchView) findViewById(R.id.search_text_field);
        listView = (ListView) findViewById(R.id.listOfMovie);
        listView.setAdapter(movieAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = movieAdapter.getMovie(position);

                movieAdapter.setAdapterDataList(MovieService.serve()
                        .incrementPopularity(movie));

                Intent intent = new Intent(getBaseContext(), MovieDetails.class);
                intent.putExtra("movieId", movie.getId());
                startActivity(intent);

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                movieAdapter.getFilter().filter(newText);
                return true;
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, MovieRepository.getSortableVariants());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.sortable_list);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    listView.setAdapter(movieAdapter.setAdapterDataList(MovieService.serve()
                            .getAllMovies()));
                }
                if (position == 1) {
                    listView.setAdapter(movieAdapter.setAdapterDataList(MovieService.serve()
                            .getRateMovies()));
                }
                if (position == 2) {
                    listView.setAdapter(movieAdapter.setAdapterDataList(MovieService.serve()
                            .getUpcomingMovies()));
                }
                if (position == 3) {
                    listView.setAdapter(movieAdapter.setAdapterDataList(MovieService.serve()
                            .getPopularMovies()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
