package sk.tuke.gamestudio.service.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;

import java.util.Collections;
import java.util.List;

@Transactional
public class ScoreServiceJPA implements ScoreService {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addScore(Score score) {
        entityManager.persist(score);
    }

    @Override
    public List<Score> getTopScores() {
        return entityManager.createQuery("select s from Score s order by s.points desc", Score.class)
                .setMaxResults(10)
                .getResultList();
    }



    @Override
    public void reset() {
        entityManager.createNativeQuery("DELETE FROM score").executeUpdate();
    }
    @Override
    public List<Score> findUserById(int id){
        return Collections.singletonList(entityManager.find(Score.class,id));
    }

    @Override
    public void update(Score score) {
        entityManager.merge(score);
    }
}
