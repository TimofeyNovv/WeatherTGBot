package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers.textinput;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.database.service.UserCoordinatesService;
import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class LongitudeInputHandler implements CommandHandlerInterface {

    private final UserCoordinatesService coordinatesService;

    @Override
    public boolean canHandle(String command, UserState userState) {
        return userState == UserState.AWAITING_INPUT_LONGITUDE;
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        SendMessage sendMessage = new SendMessage();
        Long chatId = update.getMessage().getChatId();
        UserState state = userStateService.getUserState(chatId);
        Long userId = update.getMessage().getFrom().getId();
        if (update.getMessage().getText().equals("/exit")) {
            userStateService.setUserState(userId, UserState.DEFAULT);
            sendMessage = botMessages.sendMessage(chatId, "Успешный выход из состояния ввода");
        } else {
            try {
                double value = Double.parseDouble(update.getMessage().getText());
                if (value < -180.0 || value > 180.0) {
                    sendMessage = botMessages.sendMessage(chatId, "Ошибка, долгота может быть в диапазоне от -180 до 180");
                } else {
                    userStateService.setUserState(userId, UserState.DEFAULT);
                    coordinatesService.saveLongitude(userId, value, chatId);
                    sendMessage = botMessages.sendMessage(userId, "Долгота сохранена: " + coordinatesService.getLongitude(userId) + "°");
                }
            } catch (NumberFormatException e) {
                sendMessage = botMessages.sendMessage(chatId, "Ошибка! Введите число.");
            }
        }
        return sendMessage;
    }
}
