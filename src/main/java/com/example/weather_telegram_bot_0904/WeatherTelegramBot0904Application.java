package com.example.weather_telegram_bot_0904;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherTelegramBot0904Application {

	public static void main(String[] args) {
		SpringApplication.run(WeatherTelegramBot0904Application.class, args);
	}

}
