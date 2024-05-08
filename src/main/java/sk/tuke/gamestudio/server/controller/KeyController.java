package sk.tuke.gamestudio.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.Board;
import sk.tuke.gamestudio.game.Game1024;
import sk.tuke.gamestudio.game.Move;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.ScoreService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping("/K")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class KeyController {
    private Score score;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private RatingService ratingService;
    private RestTemplate restTemplate = new RestTemplate();
    private Game1024 game1024 = new Game1024();

    private Board board = new Board(game1024);

    private Move move = new Move(game1024);
    private String getColor(int value) {
        switch(value) {
            case 2: return "#EEE4DA";  // світло-коричневий колір для цифри 2
            case 4: return "#EDE0C8";  // бежевий колір для цифри 4
            case 8: return "#F2B179";  // помаранчевий колір для цифри 8
            case 16: return "#F59563"; // темно-помаранчевий колір для цифри 16
            case 32: return "#F67C5F"; // червоний колір для цифри 32
            case 64: return "#F65E3B"; // темно-червоний колір для цифри 64
            case 128: return "#EDCF72"; // жовтий колір для цифри 128
            case 256: return "#EDCC61"; // темно-жовтий колір для цифри 256
            case 512: return "#EDC53F"; // яскраво-жовтий колір для цифри 512
            case 1024: return "#EDC850"; // оранжевий колір для цифри 1024
            default: return "#000000"; // чорний колір для будь-яких інших значень
        }
    }

    @RequestMapping("/K")
    public String K(){
        if(score.getPlayer().equals(""))
        {
            score.setPlayer("guess");
        }
        return "K";
    }

    @GetMapping("K/guessPlayer")
    @ResponseBody
    public String guess(Model model)
    {
        return score.getPlayer();
    }

    public String getGame() {
        StringBuilder sb = new StringBuilder();
       // sb.append("<p>Score: " + game1024.score + "</p>");
        sb.append("<table style=\"border-collapse: collapse; \">\n");
        for (int i = 0; i < 4; i++) {
            sb.append("<tr>\n");
            for (int j = 0; j < 4; j++) {
                sb.append("<td style=\"border: 3px solid black; height : 92px; width: 92px; color: " + getColor(game1024.board[i][j]) + ";\">");
                if (game1024.board[i][j] != 0) {
                    sb.append(game1024.board[i][j]);
                } else if (game1024.board[i][j] == 0){
                    sb.append("&nbsp;");
                }
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>\n");

        return sb.toString();
    }


    @RequestMapping("/K/move-right")
    public String moveRight(Model model) {
        if(!game1024.wonOrOver.isGameWon()) {
            game1024.move.moveRight();
        }
        if (game1024.wonOrOver.isGameWon()) {
            return checkWon(model);
        }
        if (game1024.wonOrOver.isGameOver()) {
            return checkLose(model);
        }
        model.addAttribute("board", getGame());
        return "K :: #board";
    }

    @RequestMapping("/K/move-left")
    public String moveLeft(Model model) {
        if(!game1024.wonOrOver.isGameWon()) {
            game1024.move.moveLeft();
        }
        if (game1024.wonOrOver.isGameWon()) {
            return checkWon(model);
        }
        if (game1024.wonOrOver.isGameOver()) {
            return checkLose(model);
        }
        model.addAttribute("board", getGame());
        return "K :: #board";
    }

    @RequestMapping("/K/move-up")
    public String moveUp( Model model) {
        if(!game1024.wonOrOver.isGameWon()) {
            game1024.move.moveUp();
        }
        if (game1024.wonOrOver.isGameWon()) {
            return checkWon(model);
        }
        if (game1024.wonOrOver.isGameOver()) {
            return checkLose(model);
        }
        model.addAttribute("board", getGame());
        return "K :: #board";
    }

    @RequestMapping("/K/move-down")
    public String moveDown(Model model) {
        if(!game1024.wonOrOver.isGameWon()) {
            game1024.move.moveDown();
        }
        if (game1024.wonOrOver.isGameWon()) {
            return checkWon(model);
        }
        if (game1024.wonOrOver.isGameOver()) {
            return checkLose(model);
        }
        model.addAttribute("board", getGame());
        return "K :: #board";
    }

    public String checkWon( Model model){
        model.addAttribute("message", "You win!");
        List<Score> players = scoreService.getTopScores();
        for (Score p : players)
        {
            if (p.getPlayer().equals(score.getPlayer()))
            {
                if(p.getPoints() < game1024.getScore())
                {
                    p.setPoints(game1024.getScore());
                }
                p.setPlayed_at(new Timestamp(new Date().getTime()));
                restTemplate.put("http://localhost:8080/api/score/update", p, Score.class);
                break;
            }
        }

        return "K:: #gameWon";
    }

    @RequestMapping("/K/top-score")
    @ResponseBody
    public List<Score> getTopPlayers(){
        return scoreService.getTopScores();
    }

    @RequestMapping("/K/top-comment")
    @ResponseBody
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

    @RequestMapping("/K/middle-rating")
    @ResponseBody
    public List<Rating> getTopRating(){
        return ratingService.getTopRating();
    }

    public String checkLose(Model model){
        model.addAttribute("message", "Game over!");
        return "K:: #gameLose";
    }
    @RequestMapping("/K/score")
    @ResponseBody
    public int score(){
        return game1024.getScore();
    }


    @GetMapping("/login")
    public String login(Model model){
        Score score = new Score();
        model.addAttribute("score", score);
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute Score score,Model model){
        List<Score> players = scoreService.getTopScores();
        List<String> usernames = new ArrayList<>();
        List<String> passwords = new ArrayList<>();
        for(Score p : players) {
            usernames.add(p.getPlayer());
            passwords.add(p.getPassword());
        }

        if(!usernames.contains(score.getPlayer()) || !passwords.contains(score.getPassword()))
        {
            if(score.getPlayer().equals(""))
            {
                this.score = score;
                return "redirect:/K";
            }
            model.addAttribute("error","Incorrect");
            return "redirect:/login";
        }
        this.score = score;
        //        model.addAttribute("error","Incorrect");
        return "redirect:/K";
    }

    @GetMapping("/registration")
    public String register(Model model) {
        Score score = new Score();
        model.addAttribute("score", score);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerPost(@ModelAttribute Score score, Model model)
    {
        List<Score> players = scoreService.getTopScores();
        List<String> usernames = new ArrayList<>();
        for(Score p : players) {
            usernames.add(p.getPlayer());
        }

        if(usernames.contains(score.getPlayer()))
        {
            model.addAttribute("error","Incorrect");
            return "redirect:/registration";
        }

        this.score = score;
        this.score.setPlayer(this.score.getPlayer());
        this.score.setPassword(this.score.getPassword());
        restTemplate.put("http://localhost:8080/api/score/update", this.score);
        return "redirect:/login";
    }


    @GetMapping("/K/comment")
    public String showComments(Model model) {
        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        return "K:: container";
    }


    @PostMapping("/K/comment")
    public String handleCommentSubmission(@ModelAttribute Comment comment) {
        comment.setPlayer(score.getPlayer());
        comment.setComment_at(new Timestamp(new Date().getTime()));
        commentService.addComment(comment);
        return "redirect:/K";
    }

    @GetMapping("/K/rating")
    public String showRating(Model model) {
        List<Rating> rating = ratingService.getTopRating();
        model.addAttribute("rating", rating);
        return "K:: rating-container";
    }


    @PostMapping("/K/rating")
    public String handleRatingSubmission(@ModelAttribute Rating rating) {
        rating.setPlayer(score.getPlayer());
        rating.setPlayed_at(new Timestamp(new Date().getTime()));
        ratingService.addRating(rating);
        return "redirect:/K";
    }

    @RequestMapping("/new")
    public String newGame() {
        board = new Board(game1024);
        move = new Move(game1024);
        game1024 = new Game1024();

        return "redirect:/K";
    }
}