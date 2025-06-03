package com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.CallbackHandlerInterface;
import com.example.weather_telegram_bot_0904.model.apidata.DataURLService;
import com.example.weather_telegram_bot_0904.model.database.service.impl.UserCoordinatesServiceImpl;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TemperatureCBDHandler implements CallbackHandlerInterface {

    @Override
    public boolean canHandle(String call_data) {
        return call_data.equals("TemperatureCBD");
    }

    @Override
    public SendMessage handle(Update update, BotMessages botMessages, DataURLService dataURLService, UserCoordinatesServiceImpl coordinatesService) {
        Long userId = update.getCallbackQuery().getFrom().getId();
        Double latitude = coordinatesService.getLatitude(userId);
        Double longitude = coordinatesService.getLongitude(userId);
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        return botMessages.sendMessage(chatId, "Температура сейчас = " + dataURLService.getTemperature(latitude,longitude) + "°C");
    }
}
