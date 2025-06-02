package com.example.weather_telegram_bot_0904.controller.processCalbackRefresh.handlers;

import com.example.weather_telegram_bot_0904.controller.processCalbackRefresh.CallbackRefreshInterface;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;


@Component
public class Button1CBDHandler implements CallbackRefreshInterface {


    @Override
    public boolean canHandle(String call_data) {
        return call_data.equals("button1");
    }

    @Override
    public EditMessageText handle(Update update) {
        InlineKeyboardMarkup newKeyboard = new InlineKeyboardMarkup();
        long messageId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        String newText = "Победа";

        List<InlineKeyboardButton> newRow = new ArrayList<>();
        newRow.add(InlineKeyboardButton.builder()
                .text("Новая кнопка")
                .callbackData("new_button")
                .build());
        newKeyboard.setKeyboard(List.of(newRow));
        return  EditMessageText.builder()
                .chatId(String.valueOf(chatId))
                .messageId((int) messageId)
                .text(newText)
                .replyMarkup(newKeyboard)
                .build();
    }
}
