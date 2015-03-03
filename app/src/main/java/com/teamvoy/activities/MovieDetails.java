package com.teamvoy.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamvoy.bean.Movie;
import com.teamvoy.dbhelper.HelperFactory;

import java.sql.SQLException;


public class MovieDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        try {
            Integer movieId = getIntent().getExtras().getInt("movieId");
            Movie movie = HelperFactory.getHelper().getMovieDao().queryForId(movieId);
            TextView textView = (TextView) findViewById(R.id.item_detail_text);
            ImageView imageView = (ImageView) findViewById(R.id.item_detail_image);
            imageView.setImageResource(movie.getImageId());
            textView.setText(movie.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_details, menu);
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
