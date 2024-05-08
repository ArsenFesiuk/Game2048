package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/score")
public class ScoreServiceRest{
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/K")
    public List<Score> getTopScores() {
        return scoreService.getTopScores();
    }
    @GetMapping("/users/{id}")
    public List<Score> findUserById(@PathVariable int id){
        return scoreService.findUserById(id);
    }

    @PostMapping("/login")
    public void addScore(@RequestBody Score score) {
        scoreService.addScore(score);
    }

    @PutMapping("/update")
    public void update(@RequestBody  Score score){
        this.scoreService.update(score);
    }

}


