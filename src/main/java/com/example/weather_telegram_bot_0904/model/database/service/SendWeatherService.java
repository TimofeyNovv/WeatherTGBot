package com.example.weather_telegram_bot_0904.model.database.service;

import com.example.weather_telegram_bot_0904.controller.MyTelegramBot;
import com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers.StartCommandHandler;
import com.example.weather_telegram_bot_0904.model.database.service.impl.UserCoordinatesServiceImpl;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Service
@Getter
@Setter
public class SendWeatherService {
    private final MyTelegramBot myTelegramBot;
    private BotMessages botMessages = new BotMessages();
    private final UserCoordinatesServiceImpl userCoordinatesService;
    private StartCommandHandler startCommandHandler;

    public SendWeatherService(MyTelegramBot myTelegramBot, UserCoordinatesServiceImpl userCoordinatesService, StartCommandHandler startCommandHandler) {
        this.myTelegramBot = myTelegramBot;
        this.userCoordinatesService = userCoordinatesService;
        this.startCommandHandler = startCommandHandler;
    }

    @Scheduled(cron = "0 42 19 * * *")
    public void sendWeatherDaily(){
        try {
            myTelegramBot.execute(botMessages.sendMessage(startCommandHandler.getChatId(), "Работает" ));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


}
