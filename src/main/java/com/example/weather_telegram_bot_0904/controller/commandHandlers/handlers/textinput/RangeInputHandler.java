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
        String[] values = new String[0];
        try {

            values = update.getMessage().getText().split(" ");
            Integer value0 = Integer.parseInt(values[0]);
            Integer value1 = Integer.parseInt(values[1]);
            if (values.length != 2) {
                sendMessage = botMessages.sendMessage(chatId, "Введите ровно 2 числа");
            } else if (value0 > value1) {
                sendMessage = botMessages.sendMessage(chatId, "Пожалуйста вводите числа в порядке возрастания");
            } else if (value0 < -80 || value1 > 56) {
                sendMessage = botMessages.sendMessage(chatId, "Вводите числа в диапазоне от -80 до 56 градусов");
            } else if (recommendationService.setValues(userId, value0, value1)) {
                sendMessage = botMessages.sendMessage(chatId, "Вы ввели диапазон значений, который пересекается с другим вашим диапазоном");
            } else {
                userStateService.setUserState(userId, UserState.DEFAULT);
                sendMessage = botMessages.sendMessage(chatId, "Успешно");
            }

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            sendMessage = botMessages.sendMessage(chatId, "Введите пожалуйста два числа через пробел");
        } catch (Exception e) {
            System.out.println("Произошла непредвиденная ошибка в RangeInputHandler" + e.getMessage());
        }

        return sendMessage;
    }
}
