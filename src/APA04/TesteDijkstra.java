package APA04;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * @author Emerson
 *
 */
public class TesteDijkstra {

    // método auxiliar para mostrar as distâncias
    public static void printer(int[] m, int src) {
        System.out.println("- distância -");
        for (int i = 0; i < m.length; i++) {
            System.out.println("  " + src + " - " + i + " = " + m[i]);
        }
        System.out.println("- - - - - - -");
    }

    public static void teste1() {
        /**
         * entrada [0] saida esperada [0 - 0 = 0]
         */

        int[][] input1 = {{0}};
        int[] output1 = Dijkstra.execute(input1, 0);
        printer(output1, 0);
    }

    public static void teste2() {
        /**
         * entrada 
         * | 0   2   4   8 | 
         * | 2   0  MAX  2 | 
         * | 4  MAX  0   5 | 
         * | 8   2   5   0 |
         *
         * src = 0
         *
         * saída esperada 
         * [0 - 0 = 0] 
         * [0 - 1 = 2] 
         * [0 - 2 = 4] 
         * [0 - 3 = 4]
         */

        int[][] input2 = {
            {0, 2, 4, 8},
            {2, 0, Dijkstra.MAX, 2},
            {4, Dijkstra.MAX, 0, 5},
            {8, 2, 5, 0}
        };
        int[] output2 = Dijkstra.execute(input2, 0);
        printer(output2, 0);
    }

    public static void teste3() {
        /**
         * entrada | MAX MAX MAX MAX | | MAX MAX MAX MAX | | MAX MAX MAX MAX | |
         * MAX MAX MAX MAX |
         *
         * src = 2
         *
         * saída esperada [2 - 0 = MAX] [2 - 1 = MAX] [2 - 2 = MAX] [2 - 3 =
         * MAX] MAX = 9999
         */

        int[][] input3 = {
            {Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX},
            {Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX},
            {Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX},
            {Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX, Dijkstra.MAX}
        };
        int[] output3 = Dijkstra.execute(input3, 0);
        printer(output3, 0);

    }

    public static void main(String[] args) {
        System.out.println("Teste válido: distância de 0 a 0 = 0.");
        teste1();
        System.out.println("\nTeste válido: distância de 0 a 3 otimizada passando por 1 e 2.");
        teste2();
        System.out.println("\nTeste válido: não há caminho válido. ");
        teste3();
    }

}
