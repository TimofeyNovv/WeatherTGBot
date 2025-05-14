package com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.CallbackHandlerInterface;
import com.example.weather_telegram_bot_0904.model.apidata.URLInformation;
import com.example.weather_telegram_bot_0904.model.database.CoordinatesService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;

@Component
public class DefaultCBDHandler implements CallbackHandlerInterface {
    @Override
    public boolean canHandle(String call_data) {
        return true;
    }

    @Override
    public SendMessage handle(Update update, BotMessages botMessages, URLInformation urlInformation, ArrayList<String> valuesWeather,  CoordinatesService coordinatesService) {
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        return botMessages.sendMessage(chatId, "Нажата несуществующая кнопка");
    }
}
