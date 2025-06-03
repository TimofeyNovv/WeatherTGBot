package com.example.weather_telegram_bot_0904.controller.processCallBackHandlers;

import com.example.weather_telegram_bot_0904.model.apidata.DataURLService;
import com.example.weather_telegram_bot_0904.model.database.service.impl.UserCoordinatesServiceImpl;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CallbackHandlerInterface {
    boolean canHandle(String call_data);

    SendMessage handle(Update update, BotMessages botMessages, DataURLService dataURLService, UserCoordinatesServiceImpl coordinatesService );
}
