package com.sekolah.websekolah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

@SpringBootApplication
@Async
public class WebSekolahApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSekolahApplication.class, args);
	}

}
