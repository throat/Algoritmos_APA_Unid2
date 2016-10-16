package APA04;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashSet;

/**
 *
 * @author Emerson
 */
public class TesteWordBreaker {

    public static void teste1() {
        /**
         * Entrada => { "Este é o primeiro teste", "éo" } Saída esperada: true
         */
        HashSet<String> input1_hs = new HashSet<>();
        input1_hs.add("Este");
        input1_hs.add("é");
        input1_hs.add("o");
        input1_hs.add("primeiro");
        input1_hs.add("teste");
        String input1_str = "éo";
        boolean encontrou = WordBreaker.execute(input1_str, input1_hs);
        System.out.println("Palavra encontrada: " + encontrou);
    }

    public static void teste2() {
        /**
         * Entrada => { "", "" } Resultado esperado: true
         */
        HashSet<String> input2_hs = new HashSet<>();
        input2_hs.add("");
        String input2_str = "";
        boolean encontrou = WordBreaker.execute(input2_str, input2_hs);
        System.out.println("Palavra encontrada: " + encontrou);
    }

    public static void teste3() {
        /**
         * Entrada => {"Não deve encontrar aqui", "No"} Saída esperada: false
         */
        HashSet<String> input3_hs = new HashSet<>();
        input3_hs.add("Não");
        input3_hs.add("vai");
        input3_hs.add("encontrar");
        input3_hs.add("aqui");
        String input3_str = "No";
        boolean encontrou = WordBreaker.execute(input3_str, input3_hs);
        System.out.println("Palavra encontrada: " + encontrou);
    }

    public static void teste4() {
        /**
         * Entrada => {"Não deve encontrar aqui", "No"} Saída esperada: false
         */
        HashSet<String> input4_hs = new HashSet<>();
        input4_hs.add("Não");
        input4_hs.add("se");
        input4_hs.add("reconhece");
        input4_hs.add("pedaços");
        String input4_str = "daços";
        boolean encontrou = WordBreaker.execute(input4_str, input4_hs);
        System.out.println("Palavra encontrada: " + encontrou);
    }

    public static void main(String[] args) {
        System.out.println("Teste válido: string 'éo' pode ser encontrada no "
                + "dicionário como as palavras 'é' e 'o'.");
        teste1();
        System.out.println("Teste válido: strong nula e dicionário vazio.");
        teste2();
        System.out.println("Teste válido: a palavra 'No' não pode ser encontrada"
                + "de forma alguma no dicionário.");
        teste3();
        System.out.println("Teste válido: o algoritmo não reconhece pedaços de"
                + "uma palavra.");
        teste4();
    }

}
