package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao mProjectDao;

    public ProjectDataRepository(ProjectDao projectDao) {
        mProjectDao = projectDao;
    }

    // --- GET ---

    public LiveData<List<Project>> getProjects() {
        return mProjectDao.getAllProjects();
    }
}