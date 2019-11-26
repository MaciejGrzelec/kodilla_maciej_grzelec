package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DbServiceTest {

    @Before
    public void resetList() {
        dbService.deleteAllTasks();
    }

    @Autowired
    private DbService dbService;

    @Test
    public void getTask() {
        //Given
        Task task = new Task(null, "title", "content");
        Task task2 = dbService.saveTask(task);

        //When
        Optional<Task> task3 = dbService.getTask(task2.getId());

        //Then
        Assert.assertEquals("title", task3.orElse(null).getTitle());

        //Clean
        dbService.deleteTask(task2.getId());
    }

    @Test
    public void getAllTasks() {
        //Given
        Task task = new Task(null, "title", "content");
        Task task2 = dbService.saveTask(task);
        try {
        //When
        List<Task> taskList = dbService.getAllTasks();
        //Then
        Assert.assertEquals(1, taskList.size());
        } finally {
            //Clean
            dbService.deleteTask(task2.getId());
        }
    }

    @Test
    public void saveTask() {
        //Given
        Task task = new Task(null, "title", "content");
        Task task2 = null;
        try {
            //When
            task2 = dbService.saveTask(task);
            //Then
            Assert.assertEquals("title", task2.getTitle());
        } finally {
            dbService.deleteTask(task2.getId());
        }
    }

    @Test
    public void deleteTask() {
        //Given
        Task task = new Task(null, "title", "content");
        Task task2 = dbService.saveTask(task);
        //When
        dbService.deleteTask(task2.getId());
        int size = dbService.getAllTasks().size();
        //Then
        Assert.assertEquals(0, size);
    }
}
