package sk.tuke.gamestudio.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.ScoreService;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

@Component
public class Game1024
{
    @Autowired
    public ScoreService scoreService ;
    @Autowired
    public CommentService commentService ;

    @Autowired
    public RatingService ratingService;

    public int[][] board;
    public int score;


    public WonOrOver wonOrOver = new WonOrOver(this);
    private Board drawboard = new Board(this);
    public Move move = new Move(this);
    Random random;
    private Scanner scanner;



    public Game1024() {
        board = new int[4][4];
        random = new Random();
        scanner = new Scanner(System.in);
        addRandomTile();
        addRandomTile();
    }

    public void play() {
        //scoreService.addScore(new Score("jaro","K",150,new Date()));

        printTopScores();
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username =scanner1.nextLine();

        while (true) {
            drawboard.drawBoard();
            if (wonOrOver.isGameOver()) {
                savePlayer(username);
                System.out.println("Game over!");
                break;
            }
            if(wonOrOver.isGameWon()){
                savePlayer(username);
                System.out.println("Game won!");
                break;
            }

            System.out.print("\033[38;5;202mEnter move (w/s/a/d): \033[0m");
            String input = scanner.next();
            switch (input) {
                case "w":
                    move.moveUp();
                    break;
                case "s":
                    move.moveDown();
                    break;
                case "a":
                    move.moveLeft();
                    break;
                case "d":
                     move.moveRight();
                    break;
                case "r":
                    savePlayer(username);
                    break;
            }
        }
        scanner.close();
    }

    public void addRandomTile() {
        int value = random.nextInt(10) == 0 ? 512 : 256;
        int row, col;
        do {
            row = random.nextInt(4);
            col = random.nextInt(4);
        } while (board[row][col] != 0);
        board[row][col] = value;
    }


    public int getScore() {
        return score;
    }

    private void printTopScores(){
        var scores = scoreService.getTopScores();
        System.out.println("---------------------------------------------------------------");
        for (int i = 0;i < scores.size();i++){
            var score = scores.get(i);
            System.out.printf("%d. %s %d\n",i ,score.getPlayer(),score.getPoints());
        }
        System.out.println("---------------------------------------------------------------");
    }

//    private void saveScore() {
//        scoreService.addScore(new Score(username,"K",getScore(),new Date()));
//    }

    private void savePlayer(String username) {
        scoreService.addScore(new Score(username,getScore(),new Date()));
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Enter comment:");
        String comment =scanner2.nextLine();
        commentService.addComment(new Comment(username,comment,new Date()));
        Scanner scanner3 = new Scanner(System.in);
        System.out.println("Enter rating:");
        int rating = Integer.parseInt(scanner3.nextLine());
        ratingService.addRating(new Rating(username,rating,new Date()));
    }

}

