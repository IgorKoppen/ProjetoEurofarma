package br.com.connectfy.EurofarmaCliente.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {


    @Value("${account_sid}")
    private String accountSid;

    @Value("${auth_token}")
    private String authToken;

    @Value("${phoneNumberToUse}")
    private String phoneNumberToUse;

    public String getAccountSid() {
        return accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getPhoneNumberToUse() {
        return phoneNumberToUse;
    }
}
