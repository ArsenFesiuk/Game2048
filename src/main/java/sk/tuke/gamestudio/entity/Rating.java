package sk.tuke.gamestudio.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "rating")
public class Rating implements Serializable {
    @Id
    @SequenceGenerator(
            name = "rating_seq",
            sequenceName = "rating_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rating_seq"
    )
    private int id;

    private String player;


    private int rating;

    private Date played_at;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getPlayed_at() {
        return played_at;
    }

    public void setPlayed_at(Date played_at) {
        this.played_at = played_at;
    }

    public Rating() {
    }

    public Rating(String player, int rating, Date played_at) {
        this.player = player;

        this.rating = rating;
        this.played_at = played_at;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", player='" + player + '\'' +
                ", rating=" + rating +
                ", played_at=" + played_at +
                '}';
    }
}
