//package sk.tuke.gamestudio.service.jdbc;
//
//
//import org.springframework.stereotype.Component;
//import sk.tuke.gamestudio.entity.Score;
//import sk.tuke.gamestudio.service.GamestudioException;
//import sk.tuke.gamestudio.service.ScoreService;
//
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class ScoreServiceJDBC implements ScoreService {
//
//    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/gamestudio";
//    public static final String JDBC_USER = "postgres";
//    public static final String JDBC_PASSWORD = "ytv3@3PN";
//    public static final String DELETE_STATEMENT = "DELETE FROM Score";
//    public static final String INSERT_STATEMENT = "INSERT INTO Score(player,game,points,played_at) VALUES (?,?,?,?)";
//    public static final String SELECT_STATEMENT = "SELECT player, game, points, played_at FROM score WHERE game = ? ORDER BY points DESC LIMIT 10";
//
//
//
//    @Override
//    public void addScore(Score score) {
//        try (var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//             var statement = connection.prepareStatement(INSERT_STATEMENT);
//
//        ){
//            statement.setString(1,score.getPlayer());
//            statement.setString(2, score.getGame());
//            statement.setInt(3,score.getPoints());
//            statement.setTimestamp(4,new Timestamp(score.getPlayed_at().getTime()));
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new GamestudioException("POIPO",e);
//        }
//    }
//
//
//
//
//    @Override
//    public List<Score> getTopScores(String game) {
//        try (var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//             var statement = connection.prepareStatement(SELECT_STATEMENT)
//        ) {
//            statement.setString(1, game);
//            try (var rs = statement.executeQuery()) {
//                var scores = new ArrayList<Score>();
//                while (rs.next())
//                    scores.add(new Score(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4)));
//                return scores;
//            }
//        } catch (SQLException e) {
//            throw new GamestudioException("orotr",e);
//        }
//    }
//
//    @Override
//    public void reset() {
//        try (var connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//             var statement = connection.createStatement();
//
//        ){
//            statement.executeUpdate(DELETE_STATEMENT);
//        } catch (SQLException e) {
//            throw new GamestudioException("fADAFASFFAS",e);
//        }
//    }
//
//    @Override
//    public List<Score> findUserById(int id) {
//        return null;
//    }
//
//}
//
//
