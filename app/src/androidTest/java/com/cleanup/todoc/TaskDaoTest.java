package com.cleanup.todoc;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Color;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.ProjectDao;
import com.cleanup.todoc.database.TaskDao;
import com.cleanup.todoc.database.TodocDataBase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    private TaskDao mTaskDao;
    private ProjectDao mProjectDao;
    private TodocDataBase database;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        database = Room.inMemoryDatabaseBuilder(context, TodocDataBase.class).allowMainThreadQueries().build(); //  use Room
        mTaskDao = database.taskDao();
        mProjectDao = database.projectDao();
    }

    @Before
    public void addProject() {
        //  insert 1 project
        Project example = new Project(1, "example", Color.RED);
        mProjectDao.saveProject(example);
    }

    @After
    public void closeDb() {
        database.close();
    }


    @Test
    public void insertAndGetTask() throws InterruptedException {    //  'GET TASK'
        //  create task
        Task task = new Task(1, "Task 0", 0000);
        //  insert task
        mTaskDao.saveTask(task);
        //  list all tasks
        List<Task> tasks = LiveDataTestUtil.getValue(mTaskDao.getTasks());
        //  check size is 1
        assertEquals(1, tasks.size());
        //  check name is same
        assertEquals(task.getName(), tasks.get(0).getName());
    }

    @Test
    public void deleteTask() throws InterruptedException {  //  'DELETE TASK'
        //  create task
        Task task = new Task(1, "Task 2", 2222);
        //  insert task
        mTaskDao.saveTask(task);
        //  list all tasks
        List<Task> tasks1 = LiveDataTestUtil.getValue(mTaskDao.getTasks());
        //   check name is same
        assertEquals("Task 2", tasks1.get(0).getName());
        //  check size is 1
        assertEquals(1, tasks1.size());

        //  set task
        task = tasks1.get(0);
        //  delete task
        mTaskDao.deleteTask(task);
        //  list all tasks
        List<Task> tasks2 = LiveDataTestUtil.getValue(mTaskDao.getTasks());
        //  check list size back to 0
        assertEquals(0, tasks2.size());
    }
}
