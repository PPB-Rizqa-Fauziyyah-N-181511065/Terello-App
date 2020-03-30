package com.example.terelloapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class}, version = 2, exportSchema = false)
public abstract class TaskDB extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static volatile TaskDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TaskDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDB.class, "tidy_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

