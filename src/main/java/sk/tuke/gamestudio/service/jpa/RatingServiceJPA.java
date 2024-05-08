package sk.tuke.gamestudio.service.jpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.RatingService;

import java.util.Collections;
import java.util.List;

@Transactional
public class RatingServiceJPA implements RatingService {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addRating(Rating rating) {
        entityManager.persist(rating);
    }

    @Override
    public List<Rating> getTopRating() {
        return entityManager.createQuery("select s from Rating s order by s.rating desc", Rating.class)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("DELETE FROM rating").executeUpdate();
    }

    @Override
    public List<Rating> findUserById(int id) {
        return Collections.singletonList(entityManager.find(Rating.class,id));
    }

}
