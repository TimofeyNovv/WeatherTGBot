package com.example.weather_telegram_bot_0904.controller;

import com.example.weather_telegram_bot_0904.config.BotConfig;
import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandProcessor;
import com.example.weather_telegram_bot_0904.controller.processCalbackRefresh.CallbackRefreshProcessor;
import com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.CallbackProcessor;
import com.example.weather_telegram_bot_0904.model.apidata.DataURLService;
import com.example.weather_telegram_bot_0904.model.database.service.impl.UserCoordinatesServiceImpl;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import com.example.weather_telegram_bot_0904.view.CommandMenuCr;
import com.example.weather_telegram_bot_0904.view.inlineKeyboards.InlineKeyboardWeatherButtons;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@AllArgsConstructor
public class MyTelegramBot extends TelegramLongPollingBot {

    private final InlineKeyboardWeatherButtons inlineKeyboard;

    private final BotConfig botConfig;

    private final BotMessages botMessages;

    private final CommandMenuCr commandMenuCr;

    private final CommandProcessor commandProcessor;

    private final UserStateService userStateService;

    private final UserCoordinatesServiceImpl coordinatesService;

    private final CallbackProcessor callbackProcessor;

    private final DataURLService dataURLService;

    private final CallbackRefreshProcessor callbackRefreshProcessor;

    @Override
    public void onUpdateReceived(Update update) {
        //Если пользователь нажал на кнопку
        if (update.hasCallbackQuery()) {
            if (callbackRefreshProcessor.process(update) != null){
                try {
                    execute(callbackRefreshProcessor.process(update));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    execute(callbackProcessor.process(update, botMessages, dataURLService, coordinatesService));
                    execute(inlineKeyboard.callback(update));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
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