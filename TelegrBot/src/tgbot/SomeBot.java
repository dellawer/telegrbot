package tgbot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by amifanick on 20.06.2017.
 */
public class SomeBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String user_first_name = update.getMessage().getChat().getFirstName();
        if (message != null && message.hasText()) {
            if (message.getText().equals("/help"))
                sendMsg(message, "Привет, я робот! \nПока могу только: " +
                        "\n /start - запуск теста (единственная фича бота, пока)." +
                        "\n Варианты ответов: Серый, Голубой, Никакой, Нет." +
                        "\n Команда окончания - /end");
            if (message.getText().equals("/start")) {
                sendMsg(message, "Привет, какой ты сегодня цвет?");
                if (message.getText().equals("Серый"))
                    sendMsg(message, "Как волк)))))");
                if (message.getText().equals("Голубой"))
                    sendMsg(message, "Ты что - море?");
                if (message.getText().equals("Нет"))
                    sendMsg(message, "Глубокоуважаемый(-ая) " + user_first_name + ", это неверный ответ!");
                if (message.getText().equals("Никакой"))
                    sendMsg(message, "И человек такой же. *sorry*");
                else {
                    sendMsg(message, "Попробуй другой вариант.");
                }
            } else {
                sendMsg(message, "Я не знаю что ответить на это");
            }
        }
    }


    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

//    private void log(String first_name, String last_name, String user_id, String txt, String bot_answer) {
//        System.out.println("\n ----------------------------");
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date date = new Date();
//        System.out.println(dateFormat.format(date));
//        System.out.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt);
//        System.out.println("Bot answer: \n Text - " + bot_answer);
//    }

    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return "SomeNewbie_bot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "400148210:AAEW705RnW__tWzjRsL3JAx8BhSa0Tf4KNs";
    }
}