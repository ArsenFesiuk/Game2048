package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentServiceRest {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public void addScore(@RequestBody Comment comment) {
        commentService.addComment(comment);
    }
    @GetMapping("/all")
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }
    @PutMapping("/update")
    public void update(@RequestBody Comment comment){
        this.commentService.update(comment);
    }
}
