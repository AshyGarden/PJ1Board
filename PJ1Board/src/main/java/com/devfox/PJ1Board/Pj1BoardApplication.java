package com.devfox.PJ1Board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //created_at, updated_atを自動的にアップデートに必要
@SpringBootApplication
public class Pj1BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pj1BoardApplication.class, args);
	}

}
