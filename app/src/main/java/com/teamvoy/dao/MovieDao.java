package com.teamvoy.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.teamvoy.bean.Movie;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by OLEG on 10.02.2015.
 */
public class MovieDao extends BaseDaoImpl<Movie,Integer>{

    public MovieDao(ConnectionSource connectionSource, Class<Movie> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }
    public List<Movie> getAllMovies() throws SQLException {
        return this.queryForAll();
    }

}
