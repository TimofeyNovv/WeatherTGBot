package com.example.weather_telegram_bot_0904.config;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandProcessor;
import com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers.*;
import com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers.textinput.*;
import com.example.weather_telegram_bot_0904.controller.processCalbackRefresh.CallbackRefreshProcessor;
import com.example.weather_telegram_bot_0904.controller.processCalbackRefresh.handlers.LatitudeCBDHandler;
import com.example.weather_telegram_bot_0904.controller.processCalbackRefresh.handlers.StartCBDHandler;
import com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.CallbackProcessor;
import com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.handlers.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {

    @Value("${bot.name}")
    String botName;

    @Value("${bot.token}")
    String token;

    @Bean
    public CommandProcessor commandProcessor(
            ButtonsCommandHandler buttonsCommandHandler,
            DefaultCommandHandler defaultHandler,
            LatitudeCommandHandler latitudeCommandHandler,
            LongitudeCommandHandler longitudeCommandHandler,
            SettingsHandler settingsHandler,
            RecommendationCommandHandler recommendationCommandHandler,
            LatitudeInputHandler latitudeInputHandler,
            LongitudeInputHandler longitudeInputHandler,
            RangeInputHandler rangeInputHandler,
            RangeCommandHandler rangeCommandHandler,
            StartCommandHandler startCommandHandler,
            LatitudeLongitudeInputHandler latitudeLongitudeInputHandler,
            RecommendationInputHandler recommendationInputHandler
    ) {
        return new CommandProcessor(List.of(
                buttonsCommandHandler,
                latitudeCommandHandler,
                longitudeCommandHandler,
                settingsHandler,
                recommendationCommandHandler,
                rangeCommandHandler,
                latitudeInputHandler,
                longitudeInputHandler,
                rangeInputHandler,
                recommendationInputHandler,
                latitudeLongitudeInputHandler,
                startCommandHandler,
                defaultHandler
        ));
    }


    @Bean
    CallbackProcessor callbackProcessor(
        WeatherCBDHandler weatherCBDHandler,
        TemperatureCBDHandler temperatureCBDHandler,
        HumidityCBDHandler humidityCBDHandler,
        ApparentTemperatureCBDHandler apparentTemperatureCBDHandler,
        WindSpeedCBDHandler windSpeedCBDHandler,
        PrecipTypeCBDHandler precipTypeCBDHandler,
        RecommendationCBDHandler recommendationCBDHandler,
        DefaultCBDHandler defaultCBDHandler
    ) {
        return new CallbackProcessor(List.of(
                weatherCBDHandler,
                temperatureCBDHandler,
                humidityCBDHandler,
                apparentTemperatureCBDHandler,
                windSpeedCBDHandler,
                precipTypeCBDHandler,
                recommendationCBDHandler,
                defaultCBDHandler
        ));
    }

    @Bean
    CallbackRefreshProcessor callbackRefreshProcessor(
            StartCBDHandler startCBDHandler,
            LatitudeCBDHandler latitudeCBDHandler
    ){
        return new CallbackRefreshProcessor(
                List.of(
                        startCBDHandler,
                        latitudeCBDHandler
                )
        );
    }
}
