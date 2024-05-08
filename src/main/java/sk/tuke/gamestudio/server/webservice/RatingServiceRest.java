package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.RatingService;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingServiceRest {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public void addScore(@RequestBody Rating rating) {
        ratingService.addRating(rating);
    }
    @GetMapping("/K")
    public List<Rating> getTopRating() {
        return ratingService.getTopRating();
    }
    @GetMapping("/users/{id}")
    public List<Rating> findUserById(@PathVariable int id){
        return ratingService.findUserById(id);
    }
}
