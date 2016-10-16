package APA04;

import java.util.HashSet;

/**
 * @author Emerson Braga - 11318735
 *
 * O algoritmo recebe uma string, um HashSet dicionário de palavras e verifica
 * se essa string pode ser encontrada como uma sequência de palavras segmentadas
 * no dicionário. Se a palavra existir, o método retorna true, c.c., false
 *
 * Exemplo:
 *
 * input => { Dicionário : {"João", "foi", "comprar", "pão"} Palavra :
 * "foicomprar" }
 *
 * output => true
 *
 */
public class WordBreaker {

    private static HashSet<String> memory = new HashSet<String>();

    public static boolean execute(String str, HashSet<String> dictionary) {
        if (str.length() == 0) {
            // retorna true se a palavra pode ser quebrada
            return true;
        } else if (memory.contains(str)) {
            return false;
        } else {
            int index = 0;
            // a palavra começa vazia e é composta caracter a caracter
            String word = "";
            while (index < str.length()) {
                // a cada iteração, é adicionado um caracter à palavra
                word += str.charAt(index);
                // checa existência da palavra com o caracter adicionado
                if (dictionary.contains(word)) {
                    if (execute(str.substring(index + 1), dictionary)) {
                        return true;
                    } else {
                        // adiciona mais um caracter à string
                        index++;
                    }
                } else {
                    index++;
                }
            }
            memory.add(str);
            return false;
        }
    }
}
