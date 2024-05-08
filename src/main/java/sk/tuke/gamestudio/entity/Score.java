package sk.tuke.gamestudio.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "score")
public class Score implements Serializable{
    @Id
    @SequenceGenerator(
            name = "score_seq",
            sequenceName = "score_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "score_seq"
    )
    private int id;

    private String player;

    private int points;

    private Date played_at;

    private String email;

    private String password;

    public Score() {}

    public String getPlayer() {
        return player;
    }

    public boolean setPlayer(String player) {
        this.player = player;
        return false;
    }



    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getPlayed_at() {
        return played_at;
    }

    public void setPlayed_at(Date played_at) {
        this.played_at = played_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Score(String player, int points, Date played_at) {
        this.player = player;
        this.points = points;
        this.played_at = played_at;
    }

    @Override
    public String toString() {
        return "Score{" +
                "player='" + player + '\'' +
                ", points=" + points +
                ", played_at=" + played_at +
                '}';
    }
}

