package com.football.api.demo;

import com.football.api.demo.model.Club;
import com.football.api.demo.payload.request.EnlistRequest;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FootballIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testRetrieveAllClub() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/v1/club/withplayers"), HttpMethod.GET, entity, String.class);

        String expected = "{\n" +
                "    \"content\": [\n" +
                "        {\n" +
                "            \"code\": \"LFC\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"code\": \"PSG\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testCreateClub(){
        Club club = new Club("JJK");

        HttpEntity<Club> entity = new HttpEntity<>(club, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/v1/club/add"),
                HttpMethod.POST, entity, String.class
        );

        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        Assert.assertTrue(actual.contains("/v1/club/add/JJK"));
    }

    @Test
    public void testRetrieveAllPlayers() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/v1/player/all"),
                HttpMethod.GET, entity, String.class);

        String exepected = "{\n" +
                "    \"content\": [\n" +
                "        {\n" +
                "            \"id\": 1\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 3\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 4\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 5\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        String expect = "{content:[{id:1},{id:2},{id:3},{id:4},{id:5}]}";
        JSONAssert.assertEquals(expect, response.getBody(), false);
    }

    @Test
    public void testEnlistPlayers() throws JSONException{
        EnlistRequest request = new EnlistRequest();
        request.setClubCode("PSG");
        request.setPlayerId(1);

        HttpEntity<EnlistRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/v1/player/enlist"),
                HttpMethod.POST, entity, String.class);
        String expected = "{id:1, clubCode:PSG}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
