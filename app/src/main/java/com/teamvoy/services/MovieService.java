package com.teamvoy.services;

import com.j256.ormlite.stmt.QueryBuilder;
import com.teamvoy.bean.Movie;
import com.teamvoy.dao.MovieDao;
import com.teamvoy.dbhelper.HelperFactory;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by OLEG on 16.02.2015.
 */
public class MovieService {

    private static List<Movie> movieList;
    private MovieDao movieDao;

    private MovieService() {
        try {
            movieDao = HelperFactory.getHelper().getMovieDao();
            if (movieList == null) {
                movieList = movieDao.getAllMovies();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static MovieService serve() {
        return new MovieService();
    }

    public List<Movie> getCurrentMovieList() {
        return movieList;
    }

    public List<Movie> getAllMovies() {
        List<Movie> result = new ArrayList<>();
        try {
            result = movieDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Movie getMovieById(int id) {
        Movie result = new Movie();
        try {
            result = movieDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Movie> getMoviesByTitle(String title) {
        List<Movie> result = new ArrayList<>();
        try {
            result = movieDao.queryForEq("title", title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Movie> getRateMovies() {
        QueryBuilder<Movie, Integer> builder;
        try {
            builder= HelperFactory.getHelper().getMovieDao().queryBuilder();
            builder.orderBy("rate",false);
            movieList = builder.query();
            return movieList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Movie> getUpcomingMovies() {
        List<Movie> result = movieList;
        Collections.sort(result, new Comparator<Movie>() {
            @Override
            public int compare(Movie current, Movie next) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
                try {
                    Date currentDate = dateFormat.parse(current.getDate());
                    Date nextDate = dateFormat.parse(next.getDate());
                    if (currentDate.after(nextDate)) {
                        return -1;
                    }
                    if (currentDate.before(nextDate)) {
                        return 1;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        movieList = result;
        return result;
    }

    public List<Movie> getPopularMovies() {
        QueryBuilder<Movie, Integer> builder;
        try {
            builder= HelperFactory.getHelper().getMovieDao().queryBuilder();
            builder.orderBy("popularity",false);
            movieList = builder.query();
            return movieList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return null;
    }

    public List<Movie> incrementPopularity(Movie movie) {
        List<Movie> result = new ArrayList<>();
        int i = -1;

        for (int j = 0; j < movieList.size(); j++) {
            if (movieList.get(j).getId()==movie.getId()){
                i = j;
            }
        }
        System.out.println("++++"+i);
        movie.setPopularity(movie.getPopularity() + 1);
        movieList.set(i,movie);
        try {
            HelperFactory.getHelper().getMovieDao().createOrUpdate(movie);
            result = HelperFactory.getHelper().getMovieDao().getAllMovies();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
