package br.com.connectfy.EurofarmaCliente.util;

import java.security.SecureRandom;

public class RandomStringGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int numberOfCharacters) {
        return generateRandomString(numberOfCharacters, CHARACTERS);
    }

    public static String generateRoomCode(int numberOfCharacters) {
        String specialCharacters = "!@^()_+[]{}|;,";
        String allCharacters = CHARACTERS + specialCharacters;
        return generateRandomString(numberOfCharacters, allCharacters);
    }

    private static String generateRandomString(int numberOfCharacters, String characters) {
        if (numberOfCharacters < 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(numberOfCharacters);
        for (int i = 0; i < numberOfCharacters; i++) {
            stringBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }
        return stringBuilder.toString();
    }
}
