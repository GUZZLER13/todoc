package com.cleanup.todoc.Injections;

import android.content.Context;

import com.cleanup.todoc.database.TodocDataBase;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    private static ProjectDataRepository provideProjectDataSource(Context context) {
        TodocDataBase database = TodocDataBase.getDatabase(context);
        return new ProjectDataRepository(database.projectDao());
    }

    private static TaskDataRepository provideTaskDataSource(Context context) {
        TodocDataBase database = TodocDataBase.getDatabase(context);
        return new TaskDataRepository(database.taskDao());
    }

    private static Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        ProjectDataRepository projectDataSource = provideProjectDataSource(context);
        TaskDataRepository taskDataSource = provideTaskDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(projectDataSource, taskDataSource, executor);
    }
}