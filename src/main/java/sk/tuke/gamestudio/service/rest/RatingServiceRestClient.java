package sk.tuke.gamestudio.service.rest;

import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.RatingService;

import java.util.Arrays;
import java.util.List;

public class RatingServiceRestClient implements RatingService {
    private String url = "http://localhost:8080/api/rating";
    private RestTemplate restTemplate ;

    public RatingServiceRestClient(){}

    public RatingServiceRestClient(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }


    @Override
    public void addRating(Rating rating) {
        restTemplate.postForEntity(url, rating, Rating.class);
    }

    @Override
    public List<Rating> getTopRating() {
        return Arrays.asList(restTemplate.getForEntity(url + "/K", Rating[].class).getBody());
    }


    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported via web service");
    }

    @Override
    public List<Rating> findUserById(int id) {
        return Arrays.asList(restTemplate.getForEntity(url + "/" +id,Rating[].class).getBody());
    }

}
