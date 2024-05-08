package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Score;

import java.util.List;

public interface CommentService {
    void addComment(Comment comment) ;

    void reset();
    public List<Comment> getAllComments();

    void update(Comment comment);
}
