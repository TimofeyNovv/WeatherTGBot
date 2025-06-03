package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers.textinput;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.database.service.impl.UserCoordinatesServiceImpl;
import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class LatitudeInputHandler implements CommandHandlerInterface {
    private final UserCoordinatesServiceImpl coordinatesService;

    @Override
    public boolean canHandle(String command, UserState userState) {
        return userState == UserState.AWAITING_INPUT_LATITUDE;
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        Long chatId = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage();
        Long userId = update.getMessage().getFrom().getId();
        if (update.getMessage().getText().equals("/exit")) {
            userStateService.setUserState(userId, UserState.DEFAULT);
            sendMessage = botMessages.sendMessage(chatId, "Успешный выход из состояния ввода");
        } else {
            try {
                double valueLatitude = Double.parseDouble(update.getMessage().getText());
                if (valueLatitude < -90.0 || valueLatitude > 90.0) {
                    sendMessage = botMessages.sendMessage(chatId, "Ошибка, широта может быть в диапазоне от -90 до 90");
                } else {
                    userStateService.setUserState(userId, UserState.DEFAULT);
                    coordinatesService.saveLatitude(userId, valueLatitude, chatId);
                    sendMessage = botMessages.sendMessage(userId, "Широта сохранена: " + coordinatesService.getLatitude(userId) + "°");
                }
            } catch (NumberFormatException e) {
                sendMessage = botMessages.sendMessage(chatId, "Ошибка! Введите число.");
            }
        }
        return sendMessage;
    }
}
