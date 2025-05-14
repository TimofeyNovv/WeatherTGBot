package com.example.weather_telegram_bot_0904.controller.commandHandlers;

import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandHandlerInterface {
    boolean canHandle(String command, UserState userState);  // Проверяет, может ли обработчик выполнить команду

    SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages);    // Выполняет действие команды
}