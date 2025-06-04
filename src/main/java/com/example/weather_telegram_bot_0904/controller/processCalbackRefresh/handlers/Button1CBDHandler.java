package com.example.weather_telegram_bot_0904.controller.processCalbackRefresh.handlers;

import com.example.weather_telegram_bot_0904.controller.processCalbackRefresh.CallbackRefreshInterface;
import com.example.weather_telegram_bot_0904.view.inlineKeyboards.InlineKeyboardButton1;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
public class Button1CBDHandler implements CallbackRefreshInterface {

    private final InlineKeyboardButton1 inlineKeyboardButton1 = new InlineKeyboardButton1();

    @Override
    public boolean canHandle(String call_data) {
        return call_data.equals("button1");
    }

    @Override
    public EditMessageText handle(Update update) {
        long messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        String newText = "Победа";

        return  EditMessageText.builder()
                .chatId(String.valueOf(chatId))
                .messageId((int) messageId)
                .text(newText)
                .replyMarkup(inlineKeyboardButton1.createKeyBoard())
                .build();
    }
}
