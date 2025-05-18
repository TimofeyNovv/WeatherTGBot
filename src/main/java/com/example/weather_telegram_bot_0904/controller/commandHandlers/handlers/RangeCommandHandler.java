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

@Component
@RequiredArgsConstructor
public class RangeCommandHandler implements CommandHandlerInterface {

    private final UserWeatherRecommendationService recommendationService;

    @Override
    public boolean canHandle(String command, UserState userState) {
        return "/range".equals(command);
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        Long chatId = update.getMessage().getChatId();
        userStateService.setUserState(chatId, UserState.AWAITING_INPUT_RANGE);
        return botMessages.sendMessage(update.getMessage().getChatId(), "Пожалуйста введите два числа через, пробел в порядке возрастания. Первое число будет обозначать начало диапазона второе конец диапазона(можно вводить температуру от -91 до 56)");
    }
}


