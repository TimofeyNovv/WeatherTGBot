package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers.textinput;

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
public class RangeInputHandler implements CommandHandlerInterface {

    private final UserWeatherRecommendationService recommendationService;

    @Override
    public boolean canHandle(String command, UserState userState) {
        return userState == UserState.AWAITING_INPUT_RANGE;
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        SendMessage sendMessage = new SendMessage();
        Long chatId = update.getMessage().getChatId();
        Long userId = update.getMessage().getFrom().getId();

        if (userStateService.getUserState(chatId) == UserState.AWAITING_INPUT_RANGE){
            String[] values = update.getMessage().getText().split(" ");
            System.out.println(values[0]);
            System.out.println(values[1]);
            if (recommendationService.setValues(userId, Integer.valueOf(values[0]), Integer.valueOf(values[1]))){
                sendMessage = botMessages.sendMessage(chatId, "Вы ввели диапазон значений, который пересекается с другим вашим диапазоном");
            } else {
                sendMessage = botMessages.sendMessage(chatId, "Успешно");
            }
            userStateService.setUserState(userId, UserState.DEFAULT);
        }
        return sendMessage;
    }
}
