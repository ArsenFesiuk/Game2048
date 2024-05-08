package sk.tuke.gamestudio.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {
        @Id
        @SequenceGenerator(
                name = "comment_seq",
                sequenceName = "comment_seq",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "comment_seq"
        )
   private int id;

    private String player;

    private String comment;

    private Date comment_at;

    public Comment(String player, String comment, Date comment_at) {
        this.player = player;
        this.comment = comment;
        this.comment_at = comment_at;
    }

    public Comment() {
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getComment_at() {
        return comment_at;
    }

    public void setComment_at(Date comment_at) {
        this.comment_at = comment_at;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "player='" + player + '\'' +
                ", comment='" + comment + '\'' +
                ", comment_at=" + comment_at +
                '}';
    }
}
