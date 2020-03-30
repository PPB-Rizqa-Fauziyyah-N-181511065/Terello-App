package com.example.terelloapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTasks;


    TaskRepository(Application application) {
        TaskDB db = TaskDB.getDatabase(application);
        mTaskDao = db.taskDao();
        mAllTasks = mTaskDao.getTasks();
    }

    LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    void insert(Task task) {
        TaskDB.databaseWriteExecutor.execute(() -> {
            mTaskDao.insert(task);
        });
    }
    void update(Task task) {
        TaskDB.databaseWriteExecutor.execute(() -> {
            mTaskDao.update(task);
        });
    }
}
