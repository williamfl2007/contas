package br.com.lyncas.contas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages={"br.com.lyncas.*"})
@EntityScan(basePackages ={"br.com.lyncas.domain.entity"})
@EnableJpaRepositories(basePackages ={"br.com.lyncas.repository.*"})
public class ContasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContasApplication.class, args);
	}

}
