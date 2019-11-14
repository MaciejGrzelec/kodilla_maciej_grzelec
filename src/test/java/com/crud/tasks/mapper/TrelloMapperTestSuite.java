package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {
    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "name1", new ArrayList<>());
        trelloBoardDtoList.add(trelloBoardDto);
        //When
        List<TrelloBoard> result = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("name1", result.get(0).getName());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        TrelloBoard trelloBoard = new TrelloBoard("1", "name1", new ArrayList<>());
        trelloBoardList.add(trelloBoard);
        //When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("name1", result.get(0).getName());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "name1", true);
        trelloListDtoList.add(trelloListDto1);
        //When
        List<TrelloList> result = trelloMapper.mapToList(trelloListDtoList);
        //Then
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("name1", result.get(0).getName());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloListList = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("1", "name1", true);
        trelloListList.add(trelloList1);
        //When
        List<TrelloListDto> result = trelloMapper.mapToListDto(trelloListList);
        //Then
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("name1", result.get(0).getName());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "listId");
        //When
        TrelloCardDto result = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assert.assertEquals("name", result.getName());
        Assert.assertEquals("description", result.getDescription());
        Assert.assertEquals("pos", result.getPos());
        Assert.assertEquals("listId", result.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "listId");
        //When
        TrelloCard result = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assert.assertEquals("name", result.getName());
        Assert.assertEquals("description", result.getDescription());
        Assert.assertEquals("pos", result.getPos());
        Assert.assertEquals("listId", result.getListId());
    }
}
