package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.database.CoordinatesService;
import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class TextInputHandler implements CommandHandlerInterface {


    private final CoordinatesService coordinatesService;

    @Override
    public boolean canHandle(String command, UserState userState) {
        return userState == UserState.AWAITING_INPUT_LATITUDE || userState == UserState.AWAITING_INPUT_LONGITUDE;
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        UserState state = userStateService.getUserState(chatId);
        Long userId = update.getMessage().getFrom().getId();
        SendMessage sendMessage = new SendMessage();
        try {
            double value = Double.parseDouble(text);
            if (state == UserState.AWAITING_INPUT_LATITUDE) {
                if (value < -90.0 || value > 90.0) {
                    sendMessage = botMessages.sendMessage(chatId, "Ошибка, широта может быть в диапазоне от -90 до 90");
                } else {
                    userStateService.setUserState(userId, UserState.DEFAULT);
                    coordinatesService.saveLatitude(userId, value);
                    sendMessage = botMessages.sendMessage(userId, "Широта сохранена: " + coordinatesService.getLatitude(userId) + "°");
                }
            } else if (state == UserState.AWAITING_INPUT_LONGITUDE) {
                if (value < -180.0 || value > 180.0) {
                    sendMessage = botMessages.sendMessage(chatId, "Ошибка, долгота может быть в диапазоне от -180 до 180");
                } else {
                    userStateService.setUserState(userId, UserState.DEFAULT);
                    coordinatesService.saveLongitude(userId, value);
                    sendMessage = botMessages.sendMessage(userId, "Долгота сохранена: " + coordinatesService.getLongitude(userId) + "°");
                }
            }
        } catch (NumberFormatException e) {
            sendMessage = botMessages.sendMessage(userId, "Ошибка! Введите число.");
        }
        return sendMessage;
    }
}
