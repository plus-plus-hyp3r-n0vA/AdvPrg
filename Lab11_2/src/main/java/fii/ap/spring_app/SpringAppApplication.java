package fii.ap.spring_app;

import fii.ap.controller.PlayerController;
import fii.ap.entity.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "fii.ap.repo")
@EntityScan(basePackageClasses = {Player.class})
@ComponentScan(basePackageClasses = PlayerController.class)
public class SpringAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAppApplication.class, args);
    }

}
