package com.cleanup.todoc.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    /* sauvegarde tache */
    @Insert
    void saveTask(Task task); // CREATE

    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> getTasks();  // READ

    @Delete
    void deleteTask(Task task);// DELETE

}
