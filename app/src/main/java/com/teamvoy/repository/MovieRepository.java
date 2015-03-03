package com.teamvoy.repository;

import com.teamvoy.activities.R;
import com.teamvoy.bean.Movie;
import com.teamvoy.dbhelper.HelperFactory;

import java.sql.SQLException;

/**
 * Created by OLEG on 10.02.2015.
 */
public class MovieRepository {

    public static void fillDB() {

        try {

            HelperFactory.getHelper().getMovieDao().delete(HelperFactory.getHelper().getMovieDao
                    ().getAllMovies());
            Class imageClass = R.drawable.class;

            HelperFactory.getHelper().getMovieDao().create(new Movie("Die Hard", "26.10.1996",
                    "Thriller", 2, imageClass.getField("die_hard").getInt(null),30));
            HelperFactory.getHelper().getMovieDao().create(new Movie("Unthinkable", "02.02.1999",
                    "Thriller", 6, imageClass.getField("unthinkable").getInt(null),23));
            HelperFactory.getHelper().getMovieDao().create(new Movie("Iron Man 2", "01.06.2001",
                    "Action", 9, imageClass.getField("iron_man_2").getInt(null),15));
            HelperFactory.getHelper().getMovieDao().create(new Movie("Superman Return",
                    "03.09.2005", "Action", 7, imageClass.getField("superman_return").getInt
                    (null),21));
            HelperFactory.getHelper().getMovieDao().create(new Movie("Armageddon", "05.01.1996",
                    "Action", 10, imageClass.getField("armageddon").getInt(null),18));
            HelperFactory.getHelper().getMovieDao().create(new Movie("X-Men", "20.06.2012",
                    "Action", 9.9, imageClass.getField("x_men").getInt(null),27));
            HelperFactory.getHelper().getMovieDao().create(new Movie("Spider Man 3",
                    "26.02.2008", "Action", 5.5, imageClass.getField("spider_man_3").getInt(null),22));
            HelperFactory.getHelper().getMovieDao().create(new Movie("Hell driver", "22.03.2015",
                    "Thriller", 8.1, imageClass.getField("hell_driver").getInt(null),19));
            HelperFactory.getHelper().getMovieDao().create(new Movie("Hobbit", "01.01.2015", "Fantasy",
                    7.5, imageClass.getField("hobbit").getInt(null),25));
            HelperFactory.getHelper().getMovieDao().create(new Movie("Live, Die, Repeat",
                    "08.08.2014", "Fantastic", 6.8, imageClass.getField("live_die_repeat").getInt(null),24));
            HelperFactory.getHelper().getMovieDao().create(new Movie("Star wars", "12.12.2015",
                    "Fantastic", 8.4, imageClass.getField("star_wars_7").getInt(null),11));
//            HelperFactory.getHelper().getMovieDao().create(new Movie("Terminator", "06.01.2012",
//                    "Fantastic, Thriller", 7.9, imageClass.getField("terminator").getInt(null)));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static String[] getSortableVariants() {
        String[] data = {"All movies", "Top rated", "Upcoming", "Popular"};
        return data;
    }

}
