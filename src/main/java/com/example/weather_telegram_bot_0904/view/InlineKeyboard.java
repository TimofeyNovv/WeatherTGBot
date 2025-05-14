package com.example.weather_telegram_bot_0904.view;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class InlineKeyboard {
    //--------------------Метод для отключения загрузки у кнопки на меню------------------------------------------
    public AnswerCallbackQuery callback(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(callbackQuery.getId());
        return answer;
    }

    //--------------------Метод для создания меню у сообщения и кнопок на этом меню--------------------------------
    public SendMessage hermitageInlineKeyboardAb(long chat_id, String messageText) {

        // создаю объект сообщения
        SendMessage message = new SendMessage();
        message.setChatId(chat_id);
        message.setText(messageText);

        // создаю объект встроенной клавиатуры
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // создаю список списков кнопок, который впоследствии объединит ряды кнопок
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();


        // создаю список с кнопками для первого ряда
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();

        // создаю первую кнопку для в ряду
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();

        // устанавливаю параметр текста на кнопке
        inlineKeyboardButton1.setText("\uD83C\uDF24 Weather \uD83C\uDF24");

        // устанавливаю параметр callback_data
        inlineKeyboardButton1.setCallbackData("WeatherCBD");


        // добавляю кнопку в первый ряд
        rowInline1.add(inlineKeyboardButton1);

        // создаю список с кнопками для второго ряда
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();

        // создаю кнопки для второго ряда
        InlineKeyboardButton inlineKeyboardButtonTemperature = new InlineKeyboardButton();
        inlineKeyboardButtonTemperature.setText("Temperature");
        inlineKeyboardButtonTemperature.setCallbackData("TemperatureCBD");

        InlineKeyboardButton inlineKeyboardButtonHumidity = new InlineKeyboardButton();
        inlineKeyboardButtonHumidity.setText("Humidity");
        inlineKeyboardButtonHumidity.setCallbackData("HumidityCBD");

        InlineKeyboardButton inlineKeyboardButtonApparentTemperature = new InlineKeyboardButton();
        inlineKeyboardButtonApparentTemperature.setText("ApparentTemp");
        inlineKeyboardButtonApparentTemperature.setCallbackData("ApparentTemperatureCBD");


        // добавляю кнопку во второй ряд
        rowInline2.add(inlineKeyboardButtonTemperature);
        rowInline2.add(inlineKeyboardButtonHumidity);
        rowInline2.add(inlineKeyboardButtonApparentTemperature);


        // создаю список с кнопками для третьего ряда
        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonWindSpeed = new InlineKeyboardButton();
        inlineKeyboardButtonWindSpeed.setText("WindSpeed");
        inlineKeyboardButtonWindSpeed.setCallbackData("WindSpeedCBD");

        InlineKeyboardButton inlineKeyboardButtonPrecipType = new InlineKeyboardButton();
        inlineKeyboardButtonPrecipType.setText("PrecipType");
        inlineKeyboardButtonPrecipType.setCallbackData("PrecipTypeCBD");


        // добавляю кнопку в третий ряд
        rowInline3.add(inlineKeyboardButtonPrecipType);
        rowInline3.add(inlineKeyboardButtonWindSpeed);


        // настраиваем разметку всей клавиатуры
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);
        // добавляем встроенную клавиатуру в сообщение
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        return message;
    }
}
