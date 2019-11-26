package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.trello.trelloValidator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloValidatorTest {
    @Autowired
    TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoards() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1","name", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("2","test", new ArrayList<>()));
        //When
        List<TrelloBoard> filtered = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        Assert.assertEquals(1, filtered.size());
        //Clean
        for (TrelloBoard delete: trelloBoards) {
            trelloBoards.remove(delete);
        }
    }
}
