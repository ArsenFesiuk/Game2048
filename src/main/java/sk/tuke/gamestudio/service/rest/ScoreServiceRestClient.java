package sk.tuke.gamestudio.service.rest;

import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;

import java.util.Arrays;
import java.util.List;

public class ScoreServiceRestClient implements ScoreService {
    private String url = "http://localhost:8080/api/score";
    private RestTemplate restTemplate ;

    public ScoreServiceRestClient(){}

    public ScoreServiceRestClient(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public void addScore(Score score) {
        restTemplate.postForEntity(url, score, Score.class);
    }
    @Override
    public List<Score> getTopScores() {
        return Arrays.asList(restTemplate.getForEntity(url + "/K", Score[].class).getBody());
    }
    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported via web service");
    }

    @Override
    public List<Score> findUserById(int id) {
        return Arrays.asList(restTemplate.getForEntity(url + "/" +id,Score[].class).getBody());
    }

    @Override
    public void update(Score score) {
        restTemplate.put(url + "/update", score, Score.class);
    }
}

