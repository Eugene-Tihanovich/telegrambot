package com.tihanovich.touristictelegrambot.bot;

import com.tihanovich.touristictelegrambot.service.CityService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    private final CityService cityService;

    public Bot(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        try {
            execute(getResponseMessage(message));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public SendMessage getResponseMessage(Message message) {

        String userText = message.getText().toLowerCase();

        if ("/start".equals(userText)){
            return greetingMessage(message);
        }
        else if (userText.equals(cityService.findCity(message.getText()).getName().toLowerCase())){
            return adviceMessage(message);
        }
        else {
            return notFoundMessage(message);
        }
    }

    public SendMessage greetingMessage(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Hello, " + message.getFrom().getFirstName() + " " + EmojiParser.parseToUnicode(":blush:") + "\n"
                + "Let's get acquainted. I am Dora the Explorer. I visited many cities and countries, " +
                "saw all the sights of the world, tried all the national cuisines. I, as a professional in my field, " +
                "is ready to help you with my practical advice. To do this, you just need to enter the city " +
                "you are interested in. For example, write Minsk and see what happens " +
                EmojiParser.parseToUnicode(":stuck_out_tongue_winking_eye:"));
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        return sendMessage;
    }

    public SendMessage notFoundMessage(Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Oops! Something went wrong " + EmojiParser.parseToUnicode(":confused:") + "\n" +
                "Perhaps our bot does not yet know about such a city. Sorry " + EmojiParser.parseToUnicode(":cry:") + "\n" +
                "Also check, please. Did you enter the name of the city correctly? " + EmojiParser.parseToUnicode(":wink:"));
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        return sendMessage;
    }

    public SendMessage adviceMessage(Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(cityService.findCity(message.getText()).getInfo());
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        return sendMessage;
    }

    @Override
    public String getBotUsername() {
        return System.getenv("BOT_NAME");
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }
}
