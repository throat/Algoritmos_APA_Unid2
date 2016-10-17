/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APA04;

/**
 *
 * @author Emerson
 */
/**
 * Este algoritmo decide a forma mais eficiente de se multiplicar matrizes.
 * Apesar de a multiplicação de matrizes ter sempre o mesmo resultado,
 * independente da ordem de multiplicação, o número de operações realizadas para
 * tanto pode ser otimizado de acordo com a sequência de multiplicação
 * escolhida.
 *
 * O algoritmo recebe um array D de tamanho T que representa T - 1 matrizes de
 * modo que o tamanho das mesmas são D[i-1]xD[i] e retorna o número mínimo de
 * operações para multiplicar essas matrizes
 * 
 * Para uma entrada dim = {10, 20, 30, 40}
 * É calculada a ordem com menor número de operações das matrizes
 * 10x20, 20x30 e 30x40
 * 
 */
public class MultiplicacaoDeMatrizes {

    public static int execute(int dim[], int i, int j) {
        if (i == j) {
            return 0;
        }

        int min = Integer.MAX_VALUE;

        // coloca parênteses recursivamente em diferentes posições, então compara
        // o resultado encontrado com o mínimo
        for (int counter = i; counter < j; counter++) {
            
            int t_operations = execute(dim, i, counter) + execute(dim, counter+1, j)
                    + dim[i - 1] * dim[counter] * dim[j];

            if (t_operations < min) {
                min = t_operations;
            }
        }
        return min;
    }

}
