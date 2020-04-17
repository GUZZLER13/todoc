package com.cleanup.todoc.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    /* sauvegarde projet */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveProject(Project project);  //  <<<<<<  for Testing / CREATE


    /* récupération projets */
    @Query("SELECT * FROM Projects")
    LiveData<List<Project>> getAllProjects();


}
