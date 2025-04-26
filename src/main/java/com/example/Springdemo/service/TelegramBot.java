package com.example.Springdemo.service;

import com.example.Springdemo.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case  "/start":
                    try {
                        startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "/help":
                    sendMessage(chatId,"Доступные команды:\n/Default_players\nКоманда для подачи жалобы на игроков.\n/Leaders\nКоманда для подачи жалобы на лидеров фракции:\n(УМВД,ФСИН,ФСБ,ЦБ,МО,СМИ)\n/Admins\nКоманда подачи жалобы на администрацию\n/Appeal\nКоманда для обжалования наказания\n/About\nИнформация о боте\n/Default_players_info\nОбщая информация для подачи жалобы на игроков");
                    break;

                case "/Default_players":
                    sendMessage(chatId,"1. Ваш Nick_Name:\n" +
                            "2. Nick_Name игрока:\n" +
                            "3. Суть жалобы:\n" +
                            "4. Доказательство:");
                    break;

                case "/Leaders":
                    sendMessage(chatId,"1. Ваш Nick_Name:\n" +
                            "2. Nick_Name лидера:\n" +
                            "3. Суть жалобы (описать максимально подробно и раскрыто):\n" +
                            "4. Доказательство:");
                    break;

                case "/Admins":
                    sendMessage(chatId,"1. Ваш Nick_Name:\n" +
                            "2. Nick_Name администратора:\n" +
                            "3. Дата выдачи/получения наказания:\n" +
                            "4. Суть жалобы:\n" +
                            "5. Доказательство:");
                    break;

                case "/Appeal":
                    sendMessage(chatId, "1. Ваш Nick_Name:\n" +
                            "2. Nick_Name администратора:\n" +
                            "3. Дата выдачи/получения наказания:\n" +
                            "4. Суть заявки:\n" +
                            "5. Доказательство:");
                    break;

                case "/Default_players_info":
                    sendMessage(chatId,"Общие правила подачи заявки на обжалование наказания\n" +
                            "1.1. Термин «обжалование» — мера, применяемая по решению руководства сервера к игрокам, совершившим нарушения, сущность которых заключается в полном или частичном освобождении от наказания.\n" +
                            "1.2. Для подачи заявки на обжалование необходимо создать соответствующую тему.\n" +
                            "1.3. В названии темы необходимо указать никнейм администратора и причину наказания: \"Nick_Name | Массовый DM\".\n" +
                            "Пример: \"Bruce_Banner | Массовый DM\".\n" +
                            "1.4. Если название вашей темы не соответствует правилам подачи, будет выдан отказ с последующим закрытием заявки.\n" +
                            "1.5. Ваша заявка будет рассмотрена руководством сервера, если она соответствует всем правилам подачи.\n" +
                            "1.6. Каждая заявка на обжалование рассматривается индивидуально.\n" +
                            "1.7. Оформленная заявка на обжалование не означает гарантированного одобрения со стороны руководства сервера, она может быть отклонена без объяснения причин.1.8. Срок рассмотрения обжалования - 72 часа с момента публикации.\n" +
                            "\n" +
                            "В разделе заявок на обжалование запрещается:\u200B\n" +
                            "\n" +
                            "2.1. нецензурная брань;\n" +
                            "2.2. флуд;\n" +
                            "2.3. неадекватное поведение;\n" +
                            "2.4. обман администрации, клевета на администрацию;\n" +
                            "2.5. сообщение от 3-его лица;\n" +
                            "2.6. сообщения не по теме (Offtop).\n" +
                            "\n" +
                            "\n" +
                            "Обязательные условия для рассмотрения заявки на обжалование\u200B\n" +
                            "\n" +
                            "3.1. Заявка от третьего лица не принимается (заявка должна быть подана владельцем аккаунта).\n" +
                            "3.2. Игровой ник автора заявки, ник администратора, дата выдачи наказания должны быть указаны в соответствии с правилами подачи, даже если эта информация присутствует на доказательствах или в тексте заявки.\n" +
                            "3.3. Прикрепление доказательств обязательно.\n" +
                            "Примечание: загрузка доказательств в соц. сети (ВКонтакте, instagram) запрещается, доказательства должны быть загружены на фото/видео хостинги (YouTube, Япикс, imgur).\n" +
                            "3.4. Доказательства должны быть в первоначальном виде.\n" +
                            "Примечание: видеодоказательства, которые были отредактированы и на которых присутствует посторонняя музыка, неадекватная речь, нецензурные слова или выражения, могут быть не рассмотрены в качестве доказательств. Если видеодоказательство длится более 3 минут, Вы должны указать тайм-коды нарушений.\n" +
                            "\n" +
                            "Нарушения, по которым заявка на обжалование не рассматривается:\n" +
                            "\u200B\n" +
                            "4.1. различные формы \"слива\";\n" +
                            "4.2. продажа игровой валюты;\n" +
                            "4.3. махинации;\n" +
                            "4.4. целенаправленный багоюз;\n" +
                            "4.5. продажа, передача аккаунта;\n" +
                            "4.6. сокрытие ошибок, багов системы;\n" +
                            "4.7. использование стороннего программного обеспечения;\n" +
                            "4.8. распространение конфиденциальной информации;\n" +
                            "4.9. обман администрации");
                    break;

                case "/About":
                    sendMessage(chatId,"Данный телеграмм бот сделать для автоматизации  процессов Black Russia.\nРазработчик:-\nВерсия Бота:1.0.0");
                    break;
                default:
                    sendMessage(chatId, "Команда не распознана или не доступна\nВведите:/help что бы узнать все команды.");
                    break;
            }

            if (messageText.equals("/start")){
                sendMessage(chatId,"Надеюсь мой бот вам понравиться.\nВведите /help что бы узнать о командах и их описаниях.");
            }
        }

    }

    private void startCommandReceived(long chatId, String name) throws TelegramApiException {
        String answer = "Привет, " + name + ",рад тебя видеть!";
        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            // Тут можно логировать или игнорировать
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }
}
