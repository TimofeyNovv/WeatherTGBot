package com.example.weather_telegram_bot_0904.view.inlineKeyboards.startIK;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class LatitudeInlineKeyboard {
    public InlineKeyboardMarkup createKeyBoard(){
        InlineKeyboardMarkup returnKeyboard = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> returnRows = new ArrayList<>();
        returnRows.add(InlineKeyboardButton.builder()
                .text("Установить широту и долготу")
                .callbackData("LatitudeLongitudeCBD")
                .build());
        returnKeyboard.setKeyboard(List.of(returnRows));
        return  returnKeyboard;
    }
}
