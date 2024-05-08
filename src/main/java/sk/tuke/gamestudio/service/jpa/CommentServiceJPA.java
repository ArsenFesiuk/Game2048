package sk.tuke.gamestudio.service.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommentService;

import java.util.List;

@Transactional
public class CommentServiceJPA implements CommentService {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addComment(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("DELETE FROM comment").executeUpdate();
    }

    @Override
    public List<Comment> getAllComments() {
        String query = "SELECT s FROM Comment s ORDER BY s.comment ASC";
        TypedQuery<Comment> typedQuery = entityManager.createQuery(query, Comment.class);
        return typedQuery.getResultList();
    }

    @Override
    public void update(Comment comment) {
        entityManager.merge(comment);
    }
}
