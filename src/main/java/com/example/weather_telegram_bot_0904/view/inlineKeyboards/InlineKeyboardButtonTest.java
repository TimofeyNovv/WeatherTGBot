package com.example.weather_telegram_bot_0904.view.inlineKeyboards;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class InlineKeyboardButtonTest {
    public InlineKeyboardMarkup createKeyBoard(){
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(InlineKeyboardButton.builder()
                .text("Кнопка 1")
                .callbackData("button1")
                .build());
        keyboard.setKeyboard(List.of(row));
        return keyboard;
    }
}
