//package sk.tuke.gamestudio.allMain;
//
//
//import sk.tuke.gamestudio.entity.Score;
//import sk.tuke.gamestudio.service.ScoreService;
//import sk.tuke.gamestudio.service.jdbc.ScoreServiceJDBC;
//
//import java.util.Date;
//
//
//public class TestJDBC {
//    public static void main(String[] args) throws Exception{
//        ScoreService scoreService = new ScoreServiceJDBC();
//        scoreService.reset();
//        scoreService.addScore(new Score("vasia","K",190,  new Date()));
//        scoreService.addScore(new Score("jaro","K",100,  new Date()));
//    }
//}
