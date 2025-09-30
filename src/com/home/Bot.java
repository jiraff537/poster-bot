package com.home;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Bot extends TelegramLongPollingBot {
    private final String BOT_TOKEN;
    private final String BOT_NAME;
    Storage storage;

    Bot() {
        Properties props = loadProperties();
        this.BOT_TOKEN = props.getProperty("bot.token");
        this.BOT_NAME = props.getProperty("bot.name");
        storage = new Storage();
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream("application.properties")) {
            props.load(input);
        } catch (IOException e) {
            System.err.println("Error loading application.properties: " + e.getMessage());
            System.err.println("Please create application.properties file based on application.properties.example");
            throw new RuntimeException("Failed to load configuration", e);
        }
        return props;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    /*
     *  вызывается при каждой отправке сообщения пользователем
     */
    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                //Извлекаем из объекта сообщение пользователя
                Message inMess = update.getMessage();
                //Достаем из inMess id чата пользователя
                String chatId = inMess.getChatId().toString();
                //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
                String response = parseMessage(inMess.getText());
                //Создаем объект класса SendMessage - наш будущий ответ пользователю
                SendMessage outMess = new SendMessage();

                //Добавляем в наше сообщение id чата а также наш ответ
                outMess.setChatId(chatId);
                outMess.setText(response);

                //Отправка в чат
                execute(outMess);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String parseMessage(String textMsg) {
        String response = switch (textMsg) {
            case "/start" ->
                    "Приветствую, бот знает много цитат. Жми /get, чтобы получить случайную из них, либо жми /about и узнаешь разработчиков этого бота ";
            case "/about" -> "(c) 2023 Alexei A Danilov, Igor A Khitrov";
            case "/get" -> storage.getRandQuote();
            default ->
                //TODO наверное тут есть смысл выводить
                // сообщение что такой команды нет и правила
                    "echo= ".concat(textMsg).concat(" Сообщение не распознано попробуйте /get, либо жми /about и узнаешь разработчиков этого бота ");
        };

        //Сравниваем текст пользователя с нашими командами, на основе этого формируем ответ
        return response;
    }
}
