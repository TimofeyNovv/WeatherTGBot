package com.example.weather_telegram_bot_0904.controller.processCallBackHandlers;

import com.example.weather_telegram_bot_0904.model.apidata.URLInformation;
import com.example.weather_telegram_bot_0904.model.database.service.UserCoordinatesService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;

public interface CallbackHandlerInterface {
    boolean canHandle(String call_data);

    SendMessage handle(Update update, BotMessages botMessages, URLInformation urlInformation, ArrayList<String> valuesWeather, UserCoordinatesService coordinatesService);
}
