package com.crud.tasks.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService service;

    @Test
    public void fetchTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        Task task = new Task(1L, "title1", "content1");
        Optional<Task> task1 = Optional.of(task);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);
        when(service.getTask(any())).thenReturn(task1);

        //When & Then
        mockMvc.perform(get("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("taskId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("title1")))
                .andExpect(jsonPath("$.content", is("content1")));
    }

    @Test
    public void fetchTasks() throws Exception {
        //Given
        List<TaskDto> tasks = new ArrayList<>();
        TaskDto task2 = new TaskDto(1L, "title2", "content2");
        tasks.add(task2);
        when(taskMapper.mapToTaskDtoList(any())).thenReturn(tasks);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("title2")))
                .andExpect(jsonPath("$[0].content", is("content2")));
    }

    @Test
    public void createTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test title", "Test content");
        when(service.saveTask(any())).thenReturn(null);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(service, times(1)).saveTask(any());
    }

    @Test
    public void deleteTask() throws Exception {
        //Given
        doNothing().when(service).deleteTask(any());

        //When & Then
        mockMvc.perform(delete("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("taskId", "1"))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteTask(any());
    }

    @Test
    public void updateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);
        Gson gson = new Gson();
        String jsonString = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("title")))
                .andExpect(jsonPath("$.content", is("content")));
    }
}
