package APA04;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import APA04.Kruskal.Edge;

/**
 *
 * @author Emerson
 */
public class TesteKruskal {

    // método auxiliar para printar a saída
    public static void printer(ArrayList<Edge> output) {
        System.out.println("- arestas -");
        for (Edge edge : output) {
            System.out.println("  " + edge.toString());
        }
        System.out.println("- - - - - -");
    }

    public static void teste1() {
        /**
         * entrada [src: 0, des: 0, wgh: 0] saida esperada: []
         */

        int input1_vertices[] = {0};
        Edge[] input1_edges = new Edge[1];
        input1_edges[0] = new Edge(0, 0, 0);
        ArrayList<Edge> output1 = Kruskal.execute(input1_vertices, input1_edges);
        printer(output1);
    }

    public static void teste2() {
        /**
         * entrada => { 
         * 0 - 1 = 12, 
         * 1 - 0 = 2, 
         * 0 - 2 = 3, 
         * 1 - 3 = 2, 
         * 2 - 3 = 4,
         * 1 - 4 = 200,
         * 3 - 1 = 4}
         *
         * saida esperada => { 
         *   [1, 0]
         *   [1, 3]
         *   [0, 2]
         *   [1, 4]
         */

        int input3_vertices[] = {0, 1, 2, 3, 4};
        Edge[] input3_edges = new Edge[7];
        input3_edges[0] = new Edge(0, 1, 12);
        input3_edges[1] = new Edge(1, 0, 2);
        input3_edges[2] = new Edge(0, 2, 3);
        input3_edges[3] = new Edge(1, 3, 2);
        input3_edges[4] = new Edge(2, 3, 4);
        input3_edges[5] = new Edge(1, 4, 200);
        input3_edges[6] = new Edge(3, 1, 4);
        ArrayList<Edge> output3 = Kruskal.execute(input3_vertices, input3_edges);
        printer(output3);
    }
    
    public static void teste3() {
        /**
         * entrada => { 
         * 0 - 1 = 2, 
         * 0 - 2 = 4, 
         * 0 - 3 = 8, 
         * 2 - 3 = 5, 
         * 1 - 3 = 2 }
         *
         * saida esperada => { 
         * 0 - 1 
         * 1 - 3 
         * 2 - 3 }
         */

        int input3_vertices[] = {0, 1, 2, 3};
        Edge[] input3_edges = new Edge[5];
        input3_edges[0] = new Edge(0, 1, 2);
        input3_edges[1] = new Edge(0, 2, 4);
        input3_edges[2] = new Edge(0, 3, 8);
        input3_edges[3] = new Edge(2, 3, 5);
        input3_edges[4] = new Edge(1, 3, 2);
        ArrayList<Edge> output3 = Kruskal.execute(input3_vertices, input3_edges);
        printer(output3);
    }

    public static void main(String[] args) {
        System.out.println("Teste válido: aresta que liga vértice a ele mesmo. O"
                + "algoritmo deve apenas não dar erro.");
        teste1();
        System.out.println("Teste válido: como não há outra opção de se atingir"
                + "a árvore inteira, o a MST utiliza a aresta 1-4 = 200.");
        teste2();
        System.out.println("Teste válido: a aresta 2-3 será desprezada.");
        teste3();
        

    }

}
