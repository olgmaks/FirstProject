package com.teamvoy.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.teamvoy.bean.Movie;
import com.teamvoy.dao.MovieDao;

import java.sql.SQLException;

/**
 * Created by OLEG on 10.02.2015.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "movie_data_base.db";
    private static final int DATABASE_VERSION = 1;

    private MovieDao movieDao = null;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Movie.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Movie.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MovieDao getMovieDao() throws SQLException {
        if (movieDao == null) {
            movieDao = new MovieDao(getConnectionSource(), Movie.class);
        }
        return movieDao;
    }

    @Override
    public void close() {
        super.close();
        movieDao = null;
    }
}
