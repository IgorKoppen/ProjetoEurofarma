package br.com.connectfy.EurofarmaCliente.services;

import br.com.connectfy.EurofarmaCliente.config.MessagerieConfig;
import br.com.connectfy.EurofarmaCliente.services.interfaces.SmsSender;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements SmsSender {

    private final MessagerieConfig messagerieConfig;


    @Autowired
    public MessageService(MessagerieConfig messagerieConfig) {
        this.messagerieConfig = messagerieConfig;
    }

    @Override
   public void send(String messageToUser,
                    String toPhone){
        try {
            Twilio.init(messagerieConfig.getAccountSid(), messagerieConfig.getAuthToken());
            Message.creator(
                            new PhoneNumber(toPhone),
                            new PhoneNumber(messagerieConfig.getPhoneNumberToUse()),
                            messageToUser
                    )
                    .create();
        } catch (final ApiException e){
            System.out.println(e.getMessage());
        }
    }
}
