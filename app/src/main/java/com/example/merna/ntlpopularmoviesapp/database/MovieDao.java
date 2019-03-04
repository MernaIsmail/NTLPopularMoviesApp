package com.example.merna.ntlpopularmoviesapp.database;

import com.example.merna.ntlpopularmoviesapp.model.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MovieDao {

    @Query("select * from movie")
    LiveData<List<Movie>> getAllMovies();

    @Query("select* from movie where id=:id")
    LiveData<Movie> getMovieById(String id);

    @Insert
    void insertMovie(Movie movie);

    @Update
    void updateMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);
}
