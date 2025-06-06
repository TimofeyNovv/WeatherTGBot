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
public class LatitudeLongitudeInputHandler implements CommandHandlerInterface {

    private final UserCoordinatesServiceImpl coordinatesService;


    @Override
    public boolean canHandle(String command, UserState userState) {
        return userState == UserState.AWAITING_INPUT_LONGITUDE_AND_LATITUDE;
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        Long chatId = update.getMessage().getChatId();
        Long userId = update.getMessage().getFrom().getId();
        if (update.getMessage().getText().equals("/exit")) {
            userStateService.setUserState(userId, UserState.DEFAULT);
            return botMessages.sendMessage(chatId, "Успешный выход из состояния ввода");
        }
        try {
            String[] values = update.getMessage().getText().split(" ", 2);
            double latitude = Double.parseDouble(values[0]);
            double longitude = Double.parseDouble(values[1]);
            if (latitude < -90.00 || latitude > 90){
                return botMessages.sendMessage(chatId, "Ошибка, широта может быть в диапазоне от -90 до 90");
            }
            if (longitude < -180.0 || longitude > 180.0) {
                return botMessages.sendMessage(chatId, "Ошибка, долгота может быть в диапазоне от -180 до 180");
            }
            else {
                userStateService.setUserState(userId, UserState.DEFAULT);
                coordinatesService.saveLatitude(userId, latitude, chatId);

                userStateService.setUserState(userId, UserState.DEFAULT);
                coordinatesService.saveLongitude(userId, longitude, chatId);

                return botMessages.sendMessage(userId, "Долгота сохранена: " + coordinatesService.getLongitude(userId) + "°\n" + "Широта сохранена: " + coordinatesService.getLatitude(userId) + "°");
            }
        } catch (NumberFormatException e){
            return botMessages.sendMessage(chatId, "Ошибка! Введите числа.");
        } catch (ArrayIndexOutOfBoundsException e){
            return botMessages.sendMessage(chatId, "Ошибка! Введите два числа");
        }
    }

}
