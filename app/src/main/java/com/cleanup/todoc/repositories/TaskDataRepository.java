package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao mTaskDao;

    public TaskDataRepository(TaskDao taskDao) {
        mTaskDao = taskDao;
    }

    // --- GET ---

    public LiveData<List<Task>> getTasks() {
        return mTaskDao.getTasks();
    }

    // --- CREATE ---

    public void createTask(Task task) {
        mTaskDao.saveTask(task);
    }

    // --- DELETE ---

    public void deleteTask(Task task) {
        mTaskDao.deleteTask(task);
    }
}
