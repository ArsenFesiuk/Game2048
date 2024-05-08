package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.game.Game1024;
import sk.tuke.gamestudio.service.*;
import sk.tuke.gamestudio.service.jpa.CommentServiceJPA;
import sk.tuke.gamestudio.service.jpa.RatingServiceJPA;
import sk.tuke.gamestudio.service.jpa.ScoreServiceJPA;
import sk.tuke.gamestudio.service.rest.CommentServiceRestClient;
import sk.tuke.gamestudio.service.rest.RatingServiceRestClient;
import sk.tuke.gamestudio.service.rest.ScoreServiceRestClient;

@SpringBootApplication
@Configuration
@EntityScan("sk.tuke.gamestudio.entity")
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
        pattern = "sk.tuke.gamestudio.server.*"))
public class SpringClient {
    public static void main(String[] args) {
            new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
    }
    @Bean
    public CommandLineRunner runner(Game1024 game1024)
    {
        return s-> game1024.play();
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
    @Bean
    public ScoreService scoreService(RestTemplate restTemplate){
        return new ScoreServiceRestClient(restTemplate);
        //return new ScoreServiceJPA();
    }

    @Bean
        public CommentService commentService(RestTemplate restTemplate){
            return new CommentServiceRestClient(restTemplate);
            //return new CommentServiceJPA();
    }
    @Bean
    public RatingService ratingService(RestTemplate restTemplate){
        return new RatingServiceRestClient(restTemplate);
        //return new RatingServiceJPA();
    }
}
