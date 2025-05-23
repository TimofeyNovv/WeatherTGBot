package com.example.weather_telegram_bot_0904.controller.commandHandlers.handlers.textinput;

import com.example.weather_telegram_bot_0904.controller.commandHandlers.CommandHandlerInterface;
import com.example.weather_telegram_bot_0904.model.database.service.UserWeatherRecommendationService;
import com.example.weather_telegram_bot_0904.model.state.UserState;
import com.example.weather_telegram_bot_0904.model.state.UserStateService;
import com.example.weather_telegram_bot_0904.view.BotMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class RecommendationInputHandler implements CommandHandlerInterface {

    private final UserWeatherRecommendationService recommendationService;
    @Override
    public boolean canHandle(String command, UserState userState) {
        return userState == UserState.AWAITING_INPUT_RECOMMENDATION;
    }

    @Override
    public SendMessage handle(Update update, UserStateService userStateService, BotMessages botMessages) {
        SendMessage sendMessage = new SendMessage();
        Long chatId = update.getMessage().getChatId();
        String[] partsInput = new String[0];
        Long userId = update.getMessage().getFrom().getId();
        if (update.getMessage().getText().equals("/exit")) {
            userStateService.setUserState(userId, UserState.DEFAULT);
            sendMessage = botMessages.sendMessage(chatId, "Успешный выход из состояния ввода");
        } else {
            try {
                partsInput = String.valueOf(update.getMessage().getText()).split(" ", 2);
                if (recommendationService.setRecommendation(userId, Integer.parseInt(partsInput[0]), partsInput[1])) {
                    sendMessage = botMessages.sendMessage(chatId, "Успешно сохранено");
                } else {
                    sendMessage = botMessages.sendMessage(chatId, "Введённое вами число не подходит в ваши диапазоны");
                }
            } catch (NullPointerException e) {
                sendMessage = botMessages.sendMessage(chatId, "Сообщение не содержит текста");
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                sendMessage = botMessages.sendMessage(chatId, "Пожалуйста, введите сначала число, а затем рекомендацию через пробел");
            } catch (Exception e) {
                System.out.println("Неизвестная ошибка в RecommendationInputHandler" + e.getMessage());
            }
        }
        return sendMessage;
    }
}
