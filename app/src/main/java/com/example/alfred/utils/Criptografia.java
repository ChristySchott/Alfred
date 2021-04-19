package com.example.alfred.utils;

public class Criptografia {

    public static String encriptar(int chave, String texto) {
        // Variável que irá guardar o texto crifrado
        StringBuilder textoCifrado = new StringBuilder();
        // Variável com tamanho do texto a ser encriptado
        int tamanhoTexto = texto.length();

        // Criptografa cada caracter por vez
        for (int c = 0; c < tamanhoTexto; c++) {
            // Transforma o caracter em código ASCII e faz a criptografia
            int letraCifradaASCII = ((int) texto.charAt(c)) + chave;

            // Verifica se o código ASCII está no limite dos caracteres imprimiveis
            while (letraCifradaASCII > 126) {
                letraCifradaASCII -= 94;
            }

            // Transforma código ASCII criptografado em caracter ao novo texto
            textoCifrado.append((char) letraCifradaASCII);
        }

        // Por fim retorna a mensagem criptografada por completo
        return textoCifrado.toString();
    }

    public static String decriptar(int chave, String textoCifrado) {
        // Variável que irá guardar o texto decifrado
        StringBuilder texto = new StringBuilder();
        // Variável com tamanho do texto a ser decriptado
        int tamanhoTexto = textoCifrado.length();

        // Descriptografa cada caracter por vez
        for (int c = 0; c < tamanhoTexto; c++) {
            // Transforma o caracter em código ASCII e faz a descriptografia
            int letraDecifradaASCII = ((int) textoCifrado.charAt(c)) - chave;

            // Verifica se o código ASCII está no limite dos caracteres imprimíveis
            while (letraDecifradaASCII < 32) {
                letraDecifradaASCII += 94;
            }

            // Transforma código ASCII descriptografado em caracter ao novo texto
            texto.append((char) letraDecifradaASCII);
        }

        // Por fim retorna a mensagem descriptografada por completo
        return texto.toString();
    }

}
