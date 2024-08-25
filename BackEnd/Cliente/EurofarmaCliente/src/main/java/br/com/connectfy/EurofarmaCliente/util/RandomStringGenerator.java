package br.com.connectfy.EurofarmaCliente.util;

import java.security.SecureRandom;

public class RandomStringGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int numberOfCharacters) {
        return generateRandomString(numberOfCharacters);
    }

    public static String generateRoomCode(int numberOfCharacters) {
        return generateRandomString(numberOfCharacters);
    }

    private static String generateRandomString(int numberOfCharacters) {
        if (numberOfCharacters < 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(numberOfCharacters);
        for (int i = 0; i < numberOfCharacters; i++) {
            stringBuilder.append(RandomStringGenerator.CHARACTERS.charAt(random.nextInt(RandomStringGenerator.CHARACTERS.length())));
        }
        return stringBuilder.toString();
    }
}
