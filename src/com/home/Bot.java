package com.home;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    //создаем две константы, присваиваем им значения токена и имя бота соответсвтенно
    //вместо звездочек подставляйте свои данные
    final private String BOT_TOKEN = "6082955134:AAEciF6NRJUBxWwTpmP7-pC760AwC3Dit78";
    final private String BOT_NAME = "posteremigrantsbot";
    Storage storage;

    Bot() {
        storage = new Storage();
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
        String response;

        //Сравниваем текст пользователя с нашими командами, на основе этого формируем ответ
        if (textMsg.equals("/start"))
            response = "Hello, first of we start \n" +
                    "I mast to ask you: How would you prefer to name the situation has being happened since 22' February 24th in Ukraine? Is it a Special Military Operation (SMO) or a war? \n" +
                    "Сhoose one of two according to your opinion' \n" +
                    "/SMO \n" +
                    "or \n" +
                    "/war"; //Приветствую, бот знает много цитат. Жми /get, чтобы получить случайную из них, либо жми /about и узнаешь разработчиков этого бота
        else if (textMsg.equals("/SMO"))
            response = "You are banned forever! Stupid idiot!";
        //тут нужно реализовать забанивание через ID пользователя
        else if (textMsg.equals("/war"))
            response = "You are the one of that who can call your self like a Human! \n" +
                    "Now we glad to offer you to communicate with the bot. You can choose /get or /about ";
        else if (textMsg.equals("/about"))
            response = "(c) 2023 Alexei A Danilov, Igor A Khitrov";
        else if (textMsg.equals("/get"))
            response = storage.getRandQuote();
        else
            //TODO наверное тут есть смысл выводить
            // сообщение что такой команды нет и правила
            response = "Сообщение не распознано попробуйте /start или /get, либо жми /about и узнаешь разработчиков этого бота ";
        return response;
    }
}
