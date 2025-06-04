package com.example.weather_telegram_bot_0904.view.inlineKeyboards;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class InlineKeyboardButton1 {

    public InlineKeyboardMarkup createKeyBoard(){
        InlineKeyboardMarkup newKeyboard = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> newRow = new ArrayList<>();
        newRow.add(InlineKeyboardButton.builder()
                .text("Новая кнопка")
                .callbackData("new_button")
                .build());
        newKeyboard.setKeyboard(List.of(newRow));
        return newKeyboard;
    }
}
