package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Score;

import java.util.List;

public interface ScoreService {
    void addScore(Score score) ;

    List<Score> getTopScores();

    void reset();

    public List<Score> findUserById(int id);

    void update(Score score);
}
