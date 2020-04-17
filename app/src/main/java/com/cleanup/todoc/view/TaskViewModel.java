package com.cleanup.todoc.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

//Le rôle de ViewModel est de fournir des données à l'interface utilisateur et de survivre aux changements de configuration.
// Un ViewModel agit comme un centre de communication entre le repository et l'UI.
// Vous pouvez également utiliser un ViewModel pour partager des données entre des fragments. Le ViewModel fait partie de la bibliothèque du cycle de vie.
// Le repository et l'UI sont complètement séparés par le ViewModel.
//    Il n'y a aucun appel de base de données à partir du ViewModel (tout est géré dans le repository), ce qui rend le code plus testable.

public class TaskViewModel extends ViewModel {
    private final ProjectDataRepository mProjectDataSource;
    private final TaskDataRepository mTaskDataSource;
    private final Executor mExecutor;

    @Nullable
    private LiveData<List<Project>> mProjects;


    public TaskViewModel(ProjectDataRepository projectDataSource, TaskDataRepository taskDataSource, Executor executor) {
        mProjectDataSource = projectDataSource;
        mTaskDataSource = taskDataSource;
        mExecutor = executor;   //  used to execute by asynchrone way
    }

    public void init() {
        mProjects = mProjectDataSource.getProjects();
    }


    @Nullable

    // -------------
    // FOR PROJECT
    // -------------

    public LiveData<List<Project>> getProjects() {
        return mProjects;
    }

    // -------------
    // FOR TASK
    // -------------

    public LiveData<List<Task>> getTasks() {
        return mTaskDataSource.getTasks();
    }

    public void createTask(Task task) {
        mExecutor.execute(() -> mTaskDataSource.createTask(task));
    }

    public void deleteTask(Task task) {
        mExecutor.execute(() -> mTaskDataSource.deleteTask(task));
    }


}
