package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.database.service.impl.UserWeatherRecommendationServiceImpl;
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

    @Override
    public boolean canHandle(String command, UserState userState) {
        return "/recomm".equals(command) && userState == UserState.DEFAULT;
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        Long chatId = update.getMessage().getChatId();
        userStateService.setUserState(chatId, UserState.AWAITING_INPUT_RECOMMENDATION);
        return botMessages.sendMessage(chatId, "Введите число одно число из диапазона," +
                " для которого хотите установить рекомендацию по одежде и затем через пробел" +
                " саму рекомендацию");
    }
}
