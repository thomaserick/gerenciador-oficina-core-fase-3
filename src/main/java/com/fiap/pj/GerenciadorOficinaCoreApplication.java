package com.fiap.pj;

import com.fiap.pj.infra.jpa.ExtendedRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = ExtendedRepositoryImpl.class)
public class GerenciadorOficinaCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GerenciadorOficinaCoreApplication.class, args);
    }

}
