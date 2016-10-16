package APA04;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Emerson
 */
public class TesteFloydWarshall {

    // método auxiliar para mostrar as matrizes
    public static void printer(int[][] m) {
        System.out.println("- distância -");
        for (int i = 0; i < m[0].length; i++) {
            System.out.print("|  ");
            for (int j = 0; j < m[0].length; j++) {
                System.out.print("  " + m[i][j] + "  ");
            }
            System.out.println("  |");
        }
        System.out.println("- - - - - - -");
    }

    public static void teste1() {
        /**
         * entrada 
         *   [0] 
         * 
         * saida esperada 
         *  [0]
         */

        int[][] input1 = {{0}};
        int[][] output1 = FloydWarshall.execute(input1);
        printer(output1);
    }

    public static void teste2() {
        /**
         * entrada 
         * | 0   2   4   8 | 
         * | 2   0  MAX  2 | 
         * | 4  MAX  0   5 | 
         * | 8   2   5   0 |
         *
         * saída esperada
         * | 0    2    4    4 |
         * | 2    0    6    2 |
         * | 4    6    0    5 |
         * | 4    2    5    0 |
         */

        int[][] input2 = {
            {0, 2, 4, 8},
            {2, 0, Dijkstra.MAX, 2},
            {4, Dijkstra.MAX, 0, 5},
            {8, 2, 5, 0}
        };
        int[][] output2 = FloydWarshall.execute(input2);
        printer(output2);
    }

    public static void teste3() {
        /**
         * entrada 
         * | MAX MAX MAX MAX | 
         * | MAX MAX MAX MAX | 
         * | MAX MAX MAX MAX | 
         * | MAX MAX MAX MAX |
         *
         * saída esperada 
         * | MAX MAX MAX MAX | 
         * | MAX MAX MAX MAX | 
         * | MAX MAX MAX MAX | 
         * | MAX MAX MAX MAX | 
         */

        int[][] input3 = {
            {Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX},
            {Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX},
            {Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX},
            {Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX}
        };
        int[][] output3 = FloydWarshall.execute(input3);
        printer(output3);

    }

    public static void main(String[] args) {
        System.out.println("Teste válido: matriz de adjacência nula");
        teste1();
        System.out.println("Teste válido: caminhos de 0-3 e 1-2 são otimizados");
        teste2();
        System.out.println("Teste válido: não há caminhso entre quaisquer"
                + "vértices");
        teste3();
        
    }

}
