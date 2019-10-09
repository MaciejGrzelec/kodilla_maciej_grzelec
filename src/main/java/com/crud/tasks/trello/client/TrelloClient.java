package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;


public class TrelloClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelLoToken;

    public void getTrelloBoards() {

        //TrelloBoardDto[] boardsResponse = restTemplate.getForObject(trelloApiEndpoint+"members/maciejgrzelec/boards"+"?key=" +trelloAppKey +"&token=" + trelLoToken, TrelloBoardDto[].class);
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "members/maciejgrzelec/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelLoToken)
                .queryParam("fields", "name,id").build().encode().toUri();

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
    }
}
