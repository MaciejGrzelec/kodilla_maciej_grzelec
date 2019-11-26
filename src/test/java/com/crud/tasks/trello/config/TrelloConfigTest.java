package com.crud.tasks.trello.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloConfigTest {
    @Autowired
    TrelloConfig trelloConfig;

    @Test
    public void getAPIEndpoint() {
        //When
        String endpoint = trelloConfig.getTrelloApiEndpoint();
        //Then
        Assert.assertEquals("https://api.trello.com/1", endpoint);
    }

    @Test
    public void getKey() {
        //When
        String key = trelloConfig.getTrelloAppKey();
        //Then
        Assert.assertEquals("03c035a9ed2d37504c8db29319f8ae4f", key);
    }

    @Test
    public void getToken() {
        //When
        String token = trelloConfig.getTrelloToken();
        //Then
        Assert.assertEquals("1fb8add163819383f6b84a01d566608c366d51716532ab5acd874fbe5994abbe", token);
    }

    @Test
    public void getUsername() {
        //When
        String username = trelloConfig.getTrelloUsername();
        //Then
        Assert.assertEquals("maciejgrzelec", username);
    }
}
