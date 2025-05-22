package com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.processCallBackHandlers.CallbackHandlerInterface;
import com.example.weather_telegram_bot_0904.model.apidata.DataURLService;
import com.example.weather_telegram_bot_0904.model.database.service.UserCoordinatesService;
import com.example.weather_telegram_bot_0904.model.database.service.UserWeatherRecommendationService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class RecommendationCBDHandler implements CallbackHandlerInterface {

    private final UserWeatherRecommendationService service;

    @Override
    public boolean canHandle(String call_data) {
        return call_data.equals("RecommendationCBD");
    }

    @Override
    public SendMessage handle(Update update, BotMessages botMessages, DataURLService dataURLService, UserCoordinatesService coordinatesService) {
        String recommendation;
        Long userId = update.getCallbackQuery().getFrom().getId();
        Long idRecommendation = service.ifRecommendation(update.getCallbackQuery().getFrom().getId(), dataURLService.getTemperature(coordinatesService.getLatitude(userId), coordinatesService.getLongitude(userId)));
        if (idRecommendation == 0) {
            recommendation = "для текущей температуры нету рекомендаций";
        } else {
            recommendation = service.getRecommendation(idRecommendation);
        }
        return botMessages.sendMessage(update.getCallbackQuery().getMessage().getChatId(), recommendation);
    }
}
