package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.database.service.UserWeatherRecommendationService;
import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


//Пока что написано для проверки работы с бд
@Component
@RequiredArgsConstructor
public class RecommendationCommandHandler implements CommandHandlerInterface {
    private final UserWeatherRecommendationService service;
    @Override
    public boolean canHandle(String command, UserState userState) {
        return "/recomm".equals(command);
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        //service.saveRecommendation(update.getMessage().getFrom().getId(), 4,9, "надеть кофту");
        service.getRecommendation(update.getMessage().getFrom().getId());
        return botMessages.sendMessage(update.getMessage().getChatId(), "Сохранение в бд...");
    }
}
