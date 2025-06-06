package com.example.weather_telegram_bot_0904.controller.processCalbackRefresh.handlers;

import com.example.weather_telegram_bot_0904.controller.processCalbackRefresh.CallbackRefreshInterface;
import com.example.weather_telegram_bot_0904.model.database.service.impl.UserCoordinatesServiceImpl;
import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class LatitudeCBDHandler implements CallbackRefreshInterface {

    private final UserCoordinatesServiceImpl coordinatesService;

    @Override
    public boolean canHandle(String call_data) {
        return call_data.equals("LatitudeLongitudeCBD");
    }

    @Override
    public EditMessageText handle(Update update, UserStateService userStateService) {

        long chatId = update.getCallbackQuery().getMessage().getChatId();
        long messageId = update.getCallbackQuery().getMessage().getMessageId();
        String newText = "Введите широту и долготу нужной точки. Если в вашем числе есть десятичная часть, вводите её через точку. Между широтой и долготой поставьте пробел(сначала вводите широту, затем долготу)";

        userStateService.setUserState(chatId, UserState.AWAITING_INPUT_LONGITUDE_AND_LATITUDE);

        return EditMessageText.builder()
                .chatId(chatId)
                .messageId((int) messageId)
                .text(newText)
                .build();
    }
}
