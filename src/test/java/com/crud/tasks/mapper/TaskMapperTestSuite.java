package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {
    @InjectMocks
    TaskMapper taskMapper;

    @Test
    public void testMapToTask(){
        //Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        //When
        Task result = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertEquals(1L, taskDto.getId(), 0);
        Assert.assertEquals("title1", taskDto.getTitle());
        Assert.assertEquals("content1", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDto(){
        //Given
        Task task = new Task(1L, "title1", "content1");
        //When
        TaskDto result = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals(1L, task.getId(), 0);
        Assert.assertEquals("title1", task.getTitle());
        Assert.assertEquals("content1", task.getContent());
    }

    @Test
    public void testMapToTaskDtoList(){
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "title1", "content1")) ;
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        Assert.assertEquals(1, taskDtoList.size());
        Assert.assertEquals(1L, taskDtoList.get(0).getId(), 0);
        Assert.assertEquals("title1", taskDtoList.get(0).getTitle());
        Assert.assertEquals("content1", taskDtoList.get(0).getContent());
    }
}
