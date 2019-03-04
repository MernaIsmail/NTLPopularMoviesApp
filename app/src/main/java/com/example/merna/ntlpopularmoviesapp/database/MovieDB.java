package com.example.merna.ntlpopularmoviesapp.database;

import android.content.Context;
import android.util.Log;

import com.example.merna.ntlpopularmoviesapp.model.Movie;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDB extends RoomDatabase {

    private static final String TAG = MovieDB.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DB_NAME = "movieDB";
    private static volatile MovieDB movieDB;

    public static synchronized MovieDB getInstance(Context context) {
        if (movieDB == null) {
            synchronized (LOCK) {
                Log.d(TAG, "Creating new database instance");
                movieDB = Room.databaseBuilder(
                        context,
                        MovieDB.class,
                        DB_NAME).build();
            }
        }
        return movieDB;
    }

    public abstract MovieDao movieDao();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
