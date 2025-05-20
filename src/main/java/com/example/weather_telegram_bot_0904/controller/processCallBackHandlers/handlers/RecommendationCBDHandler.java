package com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.CallbackHandlerInterface;
import com.example.weather_telegram_bot_0904.model.apidata.URLInformation;
import com.example.weather_telegram_bot_0904.model.database.service.UserCoordinatesService;
import com.example.weather_telegram_bot_0904.model.database.service.UserWeatherRecommendationService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class RecommendationCBDHandler implements CallbackHandlerInterface {

    private final UserWeatherRecommendationService service;

    @Override
    public boolean canHandle(String call_data) {
        return call_data.equals("RecommendationCBD");
    }

    @Override
    public SendMessage handle(Update update, BotMessages botMessages, URLInformation urlInformation, ArrayList<String> valuesWeather, UserCoordinatesService coordinatesService) {
        return botMessages.sendMessage(update.getCallbackQuery().getMessage().getChatId(), service.getRecommendation(update.getCallbackQuery().getFrom().getId(), 160));
    }
}
