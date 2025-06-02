package com.example.weather_telegram_bot_0904.view;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class InlineKeyboard {

    private final BotMessages botMessages = new BotMessages();

    //--------------------Метод для отключения загрузки у кнопки на меню------------------------------------------
    public AnswerCallbackQuery callback(Update update) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(update.getCallbackQuery().getId());
        return answer;
    }

    //--------------------Метод для создания меню у сообщения и кнопок на этом меню--------------------------------
    public SendMessage hermitageInlineKeyboardAb(long chat_id, String messageText) {

        // создаю объект сообщения
        SendMessage message = botMessages.sendMessage(chat_id, messageText);

        // создаю объект встроенной клавиатуры
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        // создаю список списков кнопок, который впоследствии объединит ряды кнопок
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();


        // создаю список с кнопками для первого ряда
        List<InlineKeyboardButton> rowInLine1 = new ArrayList<>();

        // создаю первую кнопку для в ряду
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();

        // устанавливаю параметр текста на кнопке
        inlineKeyboardButton1.setText("\uD83C\uDF24 Weather \uD83C\uDF24");

        // устанавливаю параметр callback_data
        inlineKeyboardButton1.setCallbackData("WeatherCBD");


        // добавляю кнопку в первый ряд
        rowInLine1.add(inlineKeyboardButton1);

        // создаю список с кнопками для второго ряда
        List<InlineKeyboardButton> rowInLine2 = new ArrayList<>();

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


        // добавляю кнопки во второй ряд
        rowInLine2.add(inlineKeyboardButtonTemperature);
        rowInLine2.add(inlineKeyboardButtonHumidity);
        rowInLine2.add(inlineKeyboardButtonApparentTemperature);


        // создаю список с кнопками для третьего ряда
        List<InlineKeyboardButton> rowInLine3 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonWindSpeed = new InlineKeyboardButton();
        inlineKeyboardButtonWindSpeed.setText("WindSpeed");
        inlineKeyboardButtonWindSpeed.setCallbackData("WindSpeedCBD");

        InlineKeyboardButton inlineKeyboardButtonPrecipType = new InlineKeyboardButton();
        inlineKeyboardButtonPrecipType.setText("PrecipType");
        inlineKeyboardButtonPrecipType.setCallbackData("PrecipTypeCBD");


        // добавляю кнопку в третий ряд
        rowInLine3.add(inlineKeyboardButtonPrecipType);
        rowInLine3.add(inlineKeyboardButtonWindSpeed);

        List<InlineKeyboardButton> rowInLine4 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButtonRecommendation = new InlineKeyboardButton();
        inlineKeyboardButtonRecommendation.setText("Recommendation");
        inlineKeyboardButtonRecommendation.setCallbackData("RecommendationCBD");

        rowInLine4.add(inlineKeyboardButtonRecommendation);


        // настраиваем разметку всей клавиатуры
        rowsInline.add(rowInLine1);
        rowsInline.add(rowInLine2);
        rowsInline.add(rowInLine3);
        rowsInline.add(rowInLine4);
        // добавляем встроенную клавиатуру в сообщение
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        return message;
    }
}
