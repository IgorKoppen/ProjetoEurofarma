package br.com.connectfy.EurofarmaCliente.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {

    private static final Pattern E164_PATTERN = Pattern.compile("^\\+[1-9]\\d{1,14}$");

    public static boolean validatePhoneNumber(String cellphoneNumber) {
        if (cellphoneNumber == null || cellphoneNumber.trim().isEmpty()) {
           return false;
        }
        Matcher matcher = E164_PATTERN.matcher(cellphoneNumber);
        return matcher.matches();
    }
}

