package APA04;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robertson
 * O teste compara o parentesco entre cada elemento de um array com o retorno 
 * do algoritmo. Por exemplo, o array retornado A[i], quando i for 0, o pai sempre será
 * -1, pois A[0] é o ponto inicial e raiz da árvore. Porem A[1] terá o valor do 
 * nó que é pai do nó 1 e assim por diante.
 */
public class TestePrimMST {

    public static void avalia(int[] a, int[] b) {
        boolean erro = false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                System.out.println("Erro: resultado encontrado = " + a[i] + " resultado esperado = " + b[i]);
                erro = true;
                break;
            }

        }
        if (!erro) {
            System.out.println("Ok.");
        }
    }

    public static void teste1() {
        System.out.println("Teste válido: Grafo comum");
        int graph[][] = new int[][]{{0, 2, 0, 6, 0},
        {2, 0, 3, 8, 5},
        {0, 3, 0, 0, 7},
        {6, 8, 0, 0, 9},
        {0, 5, 7, 9, 0},};
        int[] obtido = PrimMST.primMST(graph);
        int[] esperado = new int[]{-1, 0, 1, 0, 1};
        avalia(obtido, esperado);
    }

    public static void teste2() {
        System.out.println("Teste válido: Grafo comum com dois caminhos de peso igual");
        int graph[][] = new int[][]{{0, 2, 0, 6, 0},
        {2, 0, 3, 6, 5},
        {0, 3, 0, 0, 7},
        {6, 8, 0, 0, 9},
        {25, 5, 7, 15, 0},};
        int[] obtido = PrimMST.primMST(graph);
        int[] esperado = new int[]{-1, 0, 1, 0, 1};
        avalia(obtido, esperado);
    }
    
     public static void teste3() {
        System.out.println("Teste válido: Exemplo dos comentarios");
        int graph[][] = new int[][]{{0, 5, 0, 7},
        {5, 0, 4, 0},
        {0, 4, 0, 1},
        {7, 8, 1, 0}
        };
        int[] obtido = PrimMST.primMST(graph);
        int[] esperado = new int[]{-1, 0, 1, 2};
        avalia(obtido, esperado);
    }

    public static void main(String args[]) {
        teste1();
        teste2();
        teste3();
    }
}
