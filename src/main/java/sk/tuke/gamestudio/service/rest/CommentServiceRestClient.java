package sk.tuke.gamestudio.service.rest;

import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.CommentService;

import java.util.Arrays;
import java.util.List;

public class CommentServiceRestClient implements CommentService {

    private String url = "http://localhost:8080/api/comment";
    private RestTemplate restTemplate ;

    public CommentServiceRestClient(){}

    public CommentServiceRestClient(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }
    @Override
    public void addComment(Comment comment) {
        restTemplate.postForEntity(url, comment, Comment.class);
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported via web service");
    }

    @Override
    public List<Comment> getAllComments() {
        return Arrays.asList(restTemplate.getForEntity(url + "/all" , Comment[].class).getBody());
    }

    @Override
    public void update(Comment comment) {
        restTemplate.put(url + "/update", comment, Comment.class);
    }

}
