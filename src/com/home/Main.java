package com.home;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        consoleGreeting(); //consoleGreeting
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private static void consoleGreeting() {
        System.out.println("                                                                            \n" +
                "                                                                            \n" +
                "`7MM\"\"\"Mq.                    mm                 `7MM\"\"\"Yp,           mm    \n" +
                "  MM   `MM.                   MM                   MM    Yb           MM    \n" +
                "  MM   ,M9 ,pW\"Wq.  ,pP\"Ybd mmMMmm .gP\"Ya `7Mb,od8 MM    dP  ,pW\"Wq.mmMMmm  \n" +
                "  MMmmdM9 6W'   `Wb 8I   `\"   MM  ,M'   Yb  MM' \"' MM\"\"\"bg. 6W'   `Wb MM    \n" +
                "  MM      8M     M8 `YMMMa.   MM  8M\"\"\"\"\"\"  MM     MM    `Y 8M     M8 MM    \n" +
                "  MM      YA.   ,A9 L.   I8   MM  YM.    ,  MM     MM    ,9 YA.   ,A9 MM    \n" +
                ".JMML.     `Ybmd9'  M9mmmP'   `Mbmo`Mbmmd'.JMML. .JMMmmmd9   `Ybmd9'  `Mbmo \n" +
                "                                                                            \n" +
                "                                                                            ");
        System.out.println("01010000 01101111 01110011 01110100 01100101 01110010 01000010 01101111 01110100 ");
        System.out.println("(c) 2023 Alexei A Danilov, Igor A Khitrov");

    }

    // https://habr.com/en/sandbox/165353/

    // BOT LINK https://t.me/posteremigrantsbot

    // BOT_NAME = posteremigrantsbot
    //Done! Congratulations on your new bot. You will find it at t.me/posteremigrantsbot.
    // You can now add a description, about section and profile picture for your bot,
    // see /help for a list of commands. By the way, when you've finished creating your cool bot,
    // ping our Bot Support if you want a better username for it. Just make sure the bot is fully
    // operational before you do this.
    //
    //Use this token to access the HTTP API:
    //6082955134:AAEciF6NRJUBxWwTpmP7-pC760AwC3Dit78
    //Keep your token secure and store it safely, it can be used by anyone to control your bot.
    //
    //For a description of the Bot API, see this page: https://core.telegram.org/bots/api
}
