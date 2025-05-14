package com.example.weather_telegram_bot_0904.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

@Component
public class DotenvConfig implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(
            ConfigurableEnvironment env,
            SpringApplication application
    ) {
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry ->
                env.getSystemProperties().put(entry.getKey(), entry.getValue())
        );
    }
}