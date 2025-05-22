package com.example.weather_telegram_bot_0904.controller;

import com.example.weather_telegram_bot_0904.config.BotConfig;
import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandProcessor;
import com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.CallbackProcessor;
import com.example.weather_telegram_bot_0904.model.apidata.DataURLService;
import com.example.weather_telegram_bot_0904.model.database.service.UserCoordinatesService;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import com.example.weather_telegram_bot_0904.view.CommandMenuCr;
import com.example.weather_telegram_bot_0904.view.InlineKeyboard;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class MyTelegramBot extends TelegramLongPollingBot {

    private final InlineKeyboard inlineKeyboard;

    private final BotConfig botConfig;

    private final BotMessages botMessages;

    private final ArrayList<String> valuesWeather;

    private final CommandMenuCr commandMenuCr;

    private final CommandProcessor commandProcessor;

    private final UserStateService userStateService;

    private final UserCoordinatesService coordinatesService;

    private final CallbackProcessor callbackProcessor;

    private final DataURLService dataURLService;

    @Override
    public void onUpdateReceived(Update update) {
        //Если пользователь нажал на кнопку
        if (update.hasCallbackQuery()) {
            try {
                execute(callbackProcessor.process(update, botMessages, dataURLService, valuesWeather, coordinatesService));
                execute(inlineKeyboard.callback(update));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        //Если пользователь что-то ввёл
        else if (update.hasMessage() && update.getMessage().hasText()) {
            try {
                execute(commandProcessor.process(update, userStateService, botMessages));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PostConstruct
    public void init() {
        try {
            //Создаю кнопку "Меню"
            execute(commandMenuCr.setupCommandMenu());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
}