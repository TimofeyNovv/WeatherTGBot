package com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.CallbackHandlerInterface;
import com.example.weather_telegram_bot_0904.model.apidata.DataURLService;
import com.example.weather_telegram_bot_0904.model.database.service.impl.UserCoordinatesServiceImpl;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ApparentTemperatureCBDHandler implements CallbackHandlerInterface {

    @Override
    public boolean canHandle(String call_data) {
        return call_data.equals("ApparentTemperatureCBD");
    }

    @Override
    public SendMessage handle(Update update, BotMessages botMessages, DataURLService dataURLService, UserCoordinatesServiceImpl coordinatesService) {
        Long userId = update.getCallbackQuery().getFrom().getId();
        Double latitude = coordinatesService.getLatitude(userId);
        Double longitude = coordinatesService.getLongitude(userId);
        return botMessages.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Температура сейчас ощущается как "
                + dataURLService.getApparentTemperature(latitude,longitude) + "°C");
    }
}
