package com.example.weather_telegram_bot_0904.controller.processCalbackRefresh.handlers;

import com.example.weather_telegram_bot_0904.controller.processCalbackRefresh.CallbackRefreshInterface;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.inlineKeyboards.startIK.LatitudeInlineKeyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class StartCBDHandler implements CallbackRefreshInterface {

    private final LatitudeInlineKeyboard latitudeInlineKeyboard = new LatitudeInlineKeyboard();

    @Override
    public boolean canHandle(String call_data) {
        return call_data.equals("StartCBD");
    }

    @Override
    public EditMessageText handle(Update update, UserStateService userStateService) {

        long messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        String newText = "Нажмите на кнопку, чтобы установить широту и долготу";

        return  EditMessageText.builder()
                .chatId(String.valueOf(chatId))
                .messageId((int) messageId)
                .text(newText)
                .replyMarkup(latitudeInlineKeyboard.createKeyBoard())
                .build();
    }
}
