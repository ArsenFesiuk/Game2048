package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;

import java.util.List;

public interface RatingService {
    void addRating(Rating rating) ;
    List<Rating> getTopRating();
    void reset();
    public List<Rating> findUserById(int id);

}
