package com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.CallbackHandlerInterface;
import com.example.weather_telegram_bot_0904.model.apidata.DataURLService;
import com.example.weather_telegram_bot_0904.model.database.service.UserCoordinatesService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;

@Component
public class TemperatureCBDHandler implements CallbackHandlerInterface {

    @Override
    public boolean canHandle(String call_data) {
        return call_data.equals("TemperatureCBD");
    }

    @Override
    public SendMessage handle(Update update, BotMessages botMessages, DataURLService dataURLService, ArrayList<String> valuesWeather, UserCoordinatesService coordinatesService) {
        Long userId = update.getCallbackQuery().getFrom().getId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        return botMessages.sendMessage(chatId, "Температура сейчас = " + dataURLService.getTemperature(50,30) + "°C");
    }
}
