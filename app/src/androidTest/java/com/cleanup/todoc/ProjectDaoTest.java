package com.cleanup.todoc;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Color;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.ProjectDao;
import com.cleanup.todoc.database.TodocDataBase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.utils.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ProjectDaoTest {


    private ProjectDao mProjectDao;
    private TodocDataBase database;


    @Before
    //  create Database
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        //  use Room
        database = Room.inMemoryDatabaseBuilder(context, TodocDataBase.class).allowMainThreadQueries().build();
        mProjectDao = database.projectDao();
    }

    @After
    public void closeDb() {
        database.close();
    }

    @Test
    public void getProjects() throws InterruptedException {
        //  insert
        List<Project> projects;
        //  define 1 project (example) 'en dur'
        Project example = new Project(1, "example", Color.RED);
        mProjectDao.saveProject(example);

        //  use projectDao and Google code
        projects = LiveDataTestUtil.getValue(this.database.projectDao().getAllProjects());
        //  check 'insert'
        assertEquals(example.getName(), projects.get(0).getName());
        assertEquals(example.getId(), projects.get(0).getId());
        assertEquals(example.getColor(), projects.get(0).getColor());

        //  check 'get' (by name)
        assertEquals("example", projects.get(0).getName());
    }

}
