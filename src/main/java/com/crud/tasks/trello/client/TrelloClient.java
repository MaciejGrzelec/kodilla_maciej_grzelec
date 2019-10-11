package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TrelloClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("trello.api.endpoint.prod")
    private String trelloApiEndpoint;

    @Value("trello.app.key")
    private String trelloAppKey;

    @Value("trello.app.token")
    private String trelloToken;

    //@Value("trello.username")
    //private String trelloUsername;

    public List<TrelloBoardDto> getTrelloBoards() {

        //TrelloBoardDto[] boardsResponse = restTemplate.getForObject(trelloApiEndpoint+"members/maciejgrzelec/boards"+"?key=" +trelloAppKey +"&token=" + trelloToken, TrelloBoardDto[].class);
        URI url = getUri();

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

        if (boardsResponse != null) {
            return Arrays.asList(boardsResponse);
        }
        return new ArrayList<>();
    }

    private URI getUri() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "members/maciejgrzelec/boards")
                    .queryParam("key", trelloAppKey)
                    .queryParam("token", trelloToken)
                    .queryParam("fields", "name,id").build().encode().toUri();
    }
}
